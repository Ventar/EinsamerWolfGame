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

    public List<Action> getPossibleActions(GameSession session) {

        //System.out.println(character);

        // load the section which is the current active one to calculate the possible actions based on the current character state
        Section section = sectionService.getSection(session.character().section());

        // this may happen if the section was not available in the JSON file that represents the book
        if (section == null) {
            throw new IllegalStateException("Section ::= [" + session.character().section() + "] is not available in the SectionService");
        }

        // filter these options based on the character, e.g. if a character does not have the skill HUNT and a CHANGE_SECTION action depends on that, the action is removed here.
        List<Action> filteredAnswerOptions = filterActions(session, section.actions());

        return filteredAnswerOptions;

    }

    public ActionResult executeAction(GameSession session, Action actionToExecute, List<Action> answerOptions) {
        return actionHandler.get(actionToExecute.type()).handleAction(session, actionToExecute, answerOptions);
    }


    public void handleSection(GameSession session, ActionSelector selector) {

        int currentSection = session.character().section();

        LOG.debug("------------------------------------------------------------------------------");
        LOG.debug("[{}] handle section for character ::= [{}]", currentSection, session);

        Section section = sectionService.getSection(currentSection);
        List<Action> filteredAnswerOptions = getPossibleActions(session);
        LOG.trace("[{}] Possible (unmodified) actions for character ::= [{}]", currentSection, filteredAnswerOptions);


        filteredAnswerOptions = executeMandatoryActions(session, filteredAnswerOptions);
        LOG.trace("[{}] Filtered actions for character ::= [{}]", currentSection, filteredAnswerOptions);


        ActionResult actionResult = null;

        do {
            String text = session.character().replaceVariablesInText(section.text());
            text = battleInfo(text, filteredAnswerOptions, session);
            //LOG.trace("[{}] Modified text ::= [{}]", currentSection, text);


            Action actionToExecute = selector.selectAction(text, String.valueOf(section.sectionNumber()), filteredAnswerOptions);
            LOG.trace("[{}] Selected action by player ::= [{}]", currentSection, actionToExecute);
            actionResult = executeAction(session, actionToExecute, filteredAnswerOptions);
            LOG.trace("[{}] Result of the action ::= [{}]", currentSection, actionResult);
            LOG.trace("[{}] Modified character ::= [{}]", currentSection, session.character());

            switch (actionResult.type()) {
                case CHARACTER_DIED -> throw new RuntimeException("Damned we died :(");
                case REPRESENT_ACTIONS -> {
                    LOG.debug("[{}] Represent actions...", currentSection);
                    filteredAnswerOptions = filterActions(session, actionResult.actions());
                }
            }

            System.out.println(session.character().createCharacterString(translationService));

        } while (actionResult.type() == ActionResult.ActionResultType.REPRESENT_ACTIONS);


    }

    private List<Action> executeMandatoryActions(GameSession session, List<Action> filteredAnswerOptions) {
        List<Action> resultList = new ArrayList<>();
        for (Action action : filteredAnswerOptions) {
            if (action.mandatory()) {
                actionHandler.get(action.type()).handleAction(session, action, null);
                //System.out.println("Execute MANDATORY Action: " + action);
            } else {
                resultList.add(action);
            }
        }
        return resultList;
    }

    private String battleInfo(String text, List<Action> filteredAnswerOptions, GameSession session) {
        for (Action action : filteredAnswerOptions) {
            if (action.type().equals(ActionType.BATTLE)) {
                ;
                for (Enemy enemy : action.battle().enemy()) {
                    text += "\n" + enemy.name() + "\n";
                    text += "Gegner Ausdauer   : " + enemy.endurance() + "\n";
                    text += "Gegner Kampfstärke: " + enemy.battleStrength() + "\n";
                    text += "Kampfrunde        : " + session.battleRounds() + "\n";
                }
            }
        }
        return text;

    }

    /**
     * Calls the {@link IActionHandler#(Character, Action, List)} method for every action in the passed list to check if the action can be
     * executed by the character of the engine in the current state
     *
     * @param actions the actions to filter
     * @return the filtered actions
     */
    private List<Action> filterActions(GameSession session, List<Action> actions) {

        List<Action> answerOptions = new LinkedList<>();

        for (Action action : actions) {
            if (actionHandler.get(action.type()).isExecutable(session, action, answerOptions)) {
                answerOptions.add(action);
            }
        }

        return answerOptions;
    }

    public static void main(String[] args) {
        LOG.trace("Start new instance of the game engine...");
        GameEngine engine = new GameEngine();
        ActionSelector actionSelector = new UIService();

        GameSession session = new GameSession();

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

        session.character(character);


        LOG.debug("Created character ::= [{}]", character);

        LOG.trace("Start to handle sections...");

        while (true) {
            engine.handleSection(session, actionSelector);
        }

        //System.out.println(character.createCharacterString(translationService));
    }

}
