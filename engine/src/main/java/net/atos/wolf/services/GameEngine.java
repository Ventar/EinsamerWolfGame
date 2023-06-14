package net.atos.wolf.services;


import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.common.ServiceUtilities;
import net.atos.wolf.services.section.Section;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.ui.UIService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameEngine {

    /**
     * Service to render the UI.
     */
    private UIService ui = new UIService();

    /**
     * Service to manage the character
     */
    private CharacterService characterService = new CharacterService();

    /**
     * Service to load and provide book sections.
     */
    private SectionService sectionService = new SectionService("/ew1.json");

    /**
     * A map with all available action handlers that can be used by the engine. Has to be initialized by calling the
     * {@link ServiceUtilities#buildActionHandler()} method which usually happens automatically during construction;
     */
    private Map<ActionType, IActionHandler> actionHandler = ServiceUtilities.buildActionHandler();
    /**
     * a translater that filters with keywords words and translate them into german etc.
     */
    private TranslationService translationService = new TranslationService("/translation.json");

    private int sectionToRender = -1;

    /**
     * The currently active character.
     */
    private Character character;


    public void start() {

        LOG.debug("Created character ::= [{}]", character);

        if (sectionToRender == -1) {
            sectionToRender = character.section();
        }

        while (true) {

            // load the section which is the current active one to offer the possible actions to the character with the UI service
            Section section = sectionService.getSection(sectionToRender);

            // this may happen if the section was not available in the JSON file that represents the book
            if (section == null) {
                throw new IllegalStateException("Section ::= [" + sectionToRender + "] is not available in the SectionService");
            }

            // Generate the first set of answer options directly from the actions that are stored in the section
            List<Action> answerOptions = section.actions();
            List<Action> filteredAnswerOptions = filterActions(section.actions());

            filteredAnswerOptions = executeMandatoryActions(filteredAnswerOptions);

            Action actionToExecute = null;
            ActionResult actionResult = null;

            do {
                String text = character.replaceVariablesInText(section.text());

                text = battleInfo(text, filteredAnswerOptions);


                actionToExecute = ui.render(text, String.valueOf(section.sectionNumber()), filteredAnswerOptions);
                actionResult = actionHandler.get(actionToExecute.type()).handleAction(ui, character, actionToExecute, filteredAnswerOptions);

                switch (actionResult.type()) {
                    case CHANGE_SECTION -> sectionToRender = actionResult.targetSection();
                    case CHARACTER_DIED -> throw new RuntimeException("Damned we died :(");
                    case REPRESENT_ACTIONS -> {
                        answerOptions = actionResult.actions();
                        filteredAnswerOptions = filterActions(actionResult.actions());
                    }
                }

                System.out.println(character.createCharacterString(translationService));

            } while (actionResult.type() == ActionResult.ActionResultType.REPRESENT_ACTIONS);


        }
    }

    private List<Action> executeMandatoryActions(List<Action> filteredAnswerOptions) {
        List<Action> resultList = new ArrayList<>();
        for (Action action : filteredAnswerOptions) {
            if (action.mandatory()) {
                actionHandler.get(action.type()).handleAction(character, action, null);
                System.out.println("Execute MANDATORY Action: " + action);
            } else {
                resultList.add(action);
            }
        }
        return resultList;
    }

    private String battleInfo(String text, List<Action> filteredAnswerOptions) {
        for (Action action : filteredAnswerOptions) {
            if (action.type().equals(ActionType.BATTLE)) {
                ;
                for (Enemy enemy : action.battle().enemy()) {
                    text += "\n" + enemy.name() + "\n";
                    text += "Gegner Ausdauer   : " + enemy.endurance() + "\n";
                    text += "Gegner Kampfstärke: " + enemy.battleStrength() + "\n";
                    text += "Kampfrunde        : " + action.battleRounds() + "\n";
                }
            }
        }
        return text;

    }

    /**
     * Calls the {@link IActionHandler#isExecutable(Character, Action, List)} method for every action in the passed list to check if the action can be
     * executed by the character of the engine in the current state
     *
     * @param actions the actions to filter
     * @return the filtered actions
     */
    private List<Action> filterActions(List<Action> actions) {

        List<Action> answerOptions = new LinkedList<>();

        for (Action action : actions) {
            if (actionHandler.get(action.type()).isExecutable(character, action, answerOptions)) {
                answerOptions.add(action);
            }
        }

        return answerOptions;
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        Character character = new Character();
        character.setWeaponOne(Weapon.AXE);
        character.addSkill(KaiSkill.HEAL);
        character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        character.addSkill(KaiSkill.ARMORY_SWORD);
        character.addSkill(KaiSkill.THOUGHT_RAY);
        character.addSkill(KaiSkill.ARMORY_MACE);
        character.addItemToBackpack(Item.CINDER);
        character.addItemToBackpack(Item.FIREBOTTLE);
        character.addItemToBackpack(Item.GOLDENKEY);
        character.addItemToBackpack(Item.FEARWHEEL);
        character.addItemToBackpack(Item.GREEN_EMERALD);

        character.addSpecialItem(SpecialItem.CHAIN_MAIL);
        character.addSpecialItem(SpecialItem.BELT);
        character.addSpecialItem(SpecialItem.MAP);
        character.addSpecialItem(SpecialItem.HELMET);

        character.endurance().add(26);
        character.endurance().maxValue(26);
        character.battleStrength().add(14);
        character.gold().add(20);
        character.food().add(1);


        character.section(119);
//
        engine.character = character;
        engine.start();
        //System.out.println(character.createCharacterString(translationService));
    }

}
