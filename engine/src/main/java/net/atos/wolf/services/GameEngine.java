package net.atos.wolf.services;


import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionResult;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.action.IActionHandler;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.CharacterService;
import net.atos.wolf.services.common.ServiceUtilities;
import net.atos.wolf.services.section.Section;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.ui.UIService;

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
     * A map with all available action handlers that can be used by the engine. Has to be initialized by calling the {@link ServiceUtilities#buildActionHandler()} method which
     * usually happens automatically during construction;
     */
    private Map<ActionType, IActionHandler> actionHandler = ServiceUtilities.buildActionHandler();

    /**
     * The currently active character.
     */
    private Character character;

    public void start() {

        LOG.debug("Created character ::= [{}]", character);

        int sectionToRender = 1000;

        while (true) {

            // load the section which is the current active one to offer the possible actions to the character with the UI service
            Section section = sectionService.getSection(sectionToRender);

            // this may happen if the section was not available in the JSON file that represents the book
            if (section == null) {
                throw new IllegalStateException("Section ::= [" + sectionToRender + "] is not available in the SectionService");
            }

            // Generate the first set of answer options directly from the actions that are stored in the section
            List<Action> answerOptions = filterActions(section.actions());

            Action actionToExecute = null;
            ActionResult actionResult = null;

            do {
                actionToExecute = ui.render(character.replaceVariablesInText(section.text()), String.valueOf(section.sectionNumber()), answerOptions);
                actionResult = actionHandler.get(actionToExecute.type()).handleAction(character, actionToExecute, answerOptions);

                switch (actionResult.type()) {
                    case CHANGE_SECTION -> sectionToRender = actionResult.targetSection();
                    case CHARACTER_DIED -> throw new RuntimeException("Damned we died :(");
                    case REPRESENT_ACTIONS -> answerOptions = filterActions(actionResult.actions());
                }

            } while (actionResult.type() == ActionResult.ActionResultType.REPRESENT_ACTIONS);

            System.out.println(character);

        }
    }

    /**
     * Calls the {@link IActionHandler#isExecutable(Character, Action, boolean)} method for every action in the passed list to check if the action can be executed by the character
     * of the engine in the current state
     *
     * @param actions the actions to filter
     *
     * @return the filtered actions
     */
    private List<Action> filterActions(List<Action> actions) {

        List<Action> answerOptions = new LinkedList<>();

        for (Action action : actions) {
            if (actionHandler.get(action.type()).isExecutable(character, action, answerOptions.isEmpty())) {
                answerOptions.add(action);
            }
        }

        return answerOptions;
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        Character character = new Character();
        // character.setWeaponOne(Weapon.AXE);
        // character.addSkill(KaiSkill.HEAL);
        // character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        // character.endurance().add(26);
        // character.combatStrength().add(14);
        // character.gold().add(20);
        // character.food().add(5);

        engine.character = character;
        engine.start();
    }

}