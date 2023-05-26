package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

/**
 * Abstract base implementation for all action handler. Contains common logic to check if a handler {@link #isExecutable(Character, Action, boolean)} for a certain character state
 * and provides base implementation for the default {@link #handleAction(Character, Action, List)} method which usually leads to a section change.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-19
 */
public abstract class AbstractActionHandler implements IActionHandler {

    /**
     * The action type that is handled by the implementation
     */
    private ActionType type;

    /**
     * Creates a new instance of the action handler and resolves the {@link ActionType} for which the handler is responsible from the name of the extending class.
     *
     * @throws IllegalStateException if the class name cannot be resolved to an action type
     */
    public AbstractActionHandler() {

        ActionHandler ahAnnotation = getClass().getAnnotation(ActionHandler.class);

        if (ahAnnotation == null) {
            throw new IllegalStateException("Cannot instantiate an action handler without proper @ActionHandler annotation");
        }

        this.type = ahAnnotation.value();
    }

    /**
     * Checks if the handler can be executed by the passed character or if it is applicable. This method is called by the {@link #isExecutable(Character, Action, boolean)} method
     * of the interface.
     *
     * @param character  the character
     * @param action     the action to check
     * @param onlyAction if the action is the only action so far
     * @return {@code true} if the action can be handled for the character, {@code false} otherwise
     */
    protected boolean checkExecutable(Character character, Action action, boolean onlyAction) {
        return true;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    @Override
    public boolean isExecutable(Character character, Action action, boolean onlyAction) {
        return action.type() == type && checkExecutable(character, action, onlyAction);
    }

    public ActionResult handleAction(Character character, Action action, List<Action> answerOptions) {
        return handleAction(null, character, action, answerOptions);
    }

    @Override
    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {
        return ActionResult.changeSection(action.targetSection());
    }

}
