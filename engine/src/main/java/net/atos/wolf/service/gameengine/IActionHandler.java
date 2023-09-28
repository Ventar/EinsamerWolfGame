package net.atos.wolf.service.gameengine;

import net.atos.wolf.data.Action;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.GameService;

/**
 * Interface that is used by the {@link GameService} to control the user interactions.
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
     * @param action the action to check
     *
     * @return @{@code true} if the action can be handlerd, {@code false} otherwise
     */
    boolean isExecutable(GameSession session, Action action);
    
    /**
     * Handles the passed action. Handling an action means that the character of the player is modified based on the
     * information that is available in the handled action.
     *
     * @param action the action with additional information to modify the character
     *
     * @return the result of the action execution
     */
    void handleAction(GameSession session, Action action);
    
}
