package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

/**
 * Interface that is used by the {@link net.atos.wolf.services.GameEngine} to control the user interactions.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-19
 */
public interface IActionHandler {

    /**
     * Returns the action type for which this handler is responsible.
     *
     * @return the action type
     */
    ActionType getType();

    /**
     * Used to decide if an {@link Action} can be executed by the passed character.
     *
     * @param character  the character
     * @param action     the action to check
     * @param onlyAction if the action is the only action so far
     *
     * @return @{@code true} if the action can be handlerd, {@code false} otherwise
     */
    boolean isExecutable(Character character, Action action, boolean onlyAction);

    /**
     * Handles the passed action. Handling an action means that the character of the player is modified based on the information that is available in the handled action.
     *
     * @param character     the character that maybe modified based on the action
     * @param action        the action with additional information to modify the character
     * @param answerOptions a set of next step actions that could be modified by the action handler
     *
     * @return the result of the action execution
     *
     * @throws IllegalStateException in case a {@link UIService} is used in the implementation of the {@link #handleAction(UIService, Character, Action, List)} method and this
     *                               method is not overwritten
     */
    default ActionResult handleAction(Character character, Action action, List<Action> answerOptions) {
        return handleAction(null, character, action, answerOptions);
    }

    /**
     * Handles the passed action. Handling an action means that the character of the player is modified based on the information that is available in the handled action. If
     * additional interactions with the player are necessary the passed {@link UIService} can be used to perform these. The service MAYBE {@code null}, i.e. the implementing method
     * has to check and handle this edge case.
     *
     * @param ui            the ui that can be used for further interaction
     * @param character     the character that maybe modified based on the action
     * @param action        the action with additional information to modify the character
     * @param answerOptions a set of next step actions that could be modified by the action handler
     *
     * @return the result of the action execution
     */
    ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions);

}
