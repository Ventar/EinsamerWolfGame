package net.atos.wolf.services.action;

import lombok.Getter;
import net.atos.wolf.services.character.Character;

import java.util.List;

/**
 * The result of an action execution through the  {@link IActionHandler#handleAction(Character, Action, List)} method call.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-19
 */
@Getter
public class ActionResult {

    /**
     * The result type of an action that allows the {@link net.atos.wolf.services.GameEngine} to decide who to proceed.
     */
    public enum ActionResultType {

        /**
         * Type for custom dialogs.
         */
        CUSTOM,

        /**
         * As a result of the action the character died, i.e. the game will end.
         */
        CHARACTER_DIED,

        /**
         * All effects of the current action have been handled and the section can be changed to the next one.
         */
        CHANGE_SECTION,

        /**
         * The {@link IActionHandler#handleAction(Character, Action, List)} requires that the current section is rendered again but with a changed set of answer options. This can
         * happen in sections where the character can select from multiple items to select or buy before changing the section for example.
         */
        REPRESENT_ACTIONS
    }

    /**
     * The type of the result that is evaluated by the {@link net.atos.wolf.services.GameEngine}
     */
    private ActionResultType type;

    /**
     * The section to which the character is redirected if the type of the result is CHANGE_SECTION.
     */
    private int targetSection;

    /**
     * The list of actions which are represented if the type of the result is REPRESENT_ACTIONS.
     */
    private List<Action> actions;

    /**
     * Creates a new action result of type CHARACTER_DIED.
     *
     * @return the new result
     */
    public static ActionResult characterDied() {
        ActionResult res = new ActionResult();
        res.type = ActionResultType.CHARACTER_DIED;
        return res;
    }

    /**
     * Creates a new action result of type CUSTOM.
     *
     * @return the new result
     */
    public static ActionResult custom() {
        ActionResult res = new ActionResult();
        res.type = ActionResultType.CUSTOM;
        return res;
    }

    /**
     * Creates a new action result of type CHANGE_SECTION.
     *
     * @param targetSection the section to which the character is redirected
     *
     * @return the new result
     */
    public static ActionResult changeSection(int targetSection) {
        ActionResult res = new ActionResult();
        res.type = ActionResultType.CHANGE_SECTION;
        res.targetSection = targetSection;
        return res;
    }

    /**
     * Creates a new action result of type REPRESENT_ACTIONS.
     *
     * @param actions the actions to represent
     *
     * @return the new result
     */
    public static ActionResult representActions(List<Action> actions) {
        ActionResult res = new ActionResult();
        res.type = ActionResultType.REPRESENT_ACTIONS;
        res.actions = actions;
        return res;
    }

}
