package net.atos.wolf.services.action;

import net.atos.wolf.services.ActionSelector;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

/**
 * Abstract base implementation for all action handler. Contains common logic to check if a handler {@link #isExecutable(Character, Action, boolean)} for a
 * certain character state and provides base implementation for the default {@link #handleAction(Character, Action, List)} method which usually leads to a
 * section change.
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
     * A service too generate a random number between 0 and 9
     */
    protected DiceService diceService = new DiceService();

    /**
     * Creates a new instance of the action handler and resolves the {@link ActionType} for which the handler is responsible from the name of the extending
     * class.
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


    protected boolean checkExecutable(Character character, Action action, List<Action> answerOptions) {
        return true;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    @Override
    public boolean isExecutable(Character character, Action action, List<Action> answerOptions) {
        return action.type() == type && checkExecutable(character, action, answerOptions);
    }

    public ActionResult handleAction(Character character, Action action, List<Action> answerOptions) {
        return handleAction(null, character, action, answerOptions);
    }

    @Override
    public ActionResult handleAction(ActionSelector selector, Character character, Action action, List<Action> answerOptions) {
        character.section(action.targetSection());
        return ActionResult.sectionFinished();
    }

}
