package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.AnswerOption;

import java.util.List;

/**
 * Abstract base implementation for all action handler. Contains common logic to check if a handler {@link #canHandle(Action)} a specific action and base implementation for the
 * default {@link #handleAction(Character, Action, List)} method which usually leads to a section change.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-19
 */
public abstract class AbstractActionHandler implements ActionHandler {

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

        String name = getClass().getSimpleName(); // get the name of the class

        if (!name.endsWith("Handler")) {
            throw new IllegalStateException("To be used as action handler the name of class " + name + " has to end with the extension Handler");
        }

        name = name.substring(0, name.length() - 7); // remove the ending Handler so that the name can be converted to an enum

        // convert the class name to an enum constant of type ActionType
        StringBuffer enumName = new StringBuffer(64);

        for (int i = 0; i < name.length(); i++) {
            if (i != 0 && java.lang.Character.isUpperCase(name.charAt(i))) {
                enumName.append("_");
            }
            enumName.append(java.lang.Character.toUpperCase(name.charAt(i)));
        }

        try {
            this.type = ActionType.valueOf(enumName.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("The name of the class " + name + " cannot be resolved to an enum value of type ActionType", e);
        }
    }

    @Override
    public boolean canHandle(Action action) {
        return action.getType() == type;
    }

    @Override
    public ActionResult handleAction(Character character, Action action, List<AnswerOption> answerOptions) {
        return ActionResult.changeSection(action.getTargetSection());
    }

}
