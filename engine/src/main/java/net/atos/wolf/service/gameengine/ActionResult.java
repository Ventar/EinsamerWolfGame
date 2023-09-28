package net.atos.wolf.service.gameengine;

import lombok.Getter;
import lombok.ToString;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.GameService;
import net.atos.wolf.data.Character;

import java.util.List;

/**
 * The result of an action execution through the  {@link IActionHandler#handleAction(Character, Action, List)} method call.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-19
 */
@Getter
@ToString
public class ActionResult {

    /**
     * The result type of an action that allows the {@link GameService} to decide who to proceed.
     */
    public enum ActionResultType {

        /**
         * Type for custom dialogs.
         */
        SECTION_FINISHED,

        /**
         * As a result of the action the character died, i.e. the game will end.
         */
        CHARACTER_DIED,

        /**
         * The {@link IActionHandler#handleAction(Character, Action, List)} requires that the current section is rendered again but with a changed set of answer options. This can
         * happen in sections where the character can select from multiple items to select or buy before changing the section for example.
         */
        REPRESENT_ACTIONS
    }

    /**
     * The type of the result that is evaluated by the {@link GameService}
     */
    private ActionResultType type;


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
    public static ActionResult sectionFinished() {
        ActionResult res = new ActionResult();
        res.type = ActionResultType.SECTION_FINISHED;
        return res;
    }

    /**
     * Creates a new action result of type REPRESENT_ACTIONS.
     *
     * @param actions the actions to represent
     * @return the new result
     */
    public static ActionResult representActions(List<Action> actions) {
        ActionResult res = new ActionResult();
        res.type = ActionResultType.REPRESENT_ACTIONS;
        res.actions = actions;
        return res;
    }

  //  @Override
   // public String toString() {
     //   return "ActionResult{" +
       //         "type=" + type +
         //       ", actions=" + actions +
           //     '}';
    }

