package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

public interface ActionHandler {
    boolean canHandle(Action action);

    /**
     * Used to decide if an {@link net.atos.wolf.services.AnswerOption} is rendered by the {@link net.atos.wolf.services.UIService}. Inside of the
     * {@link net.atos.wolf.services.GameEngine} all possible {@link Action}s of a book {@link net.atos.wolf.services.Section} are passed to the available
     * action handlers and this method decides if the answer option is rendered.
     * <br>
     * The only action parameter is used to inform the handler that it is the only handler that is used for the specific section so far, i.e. in case of the
     * action type {@link net.atos.wolf.services.ActionType#CHANGE_SECTION_IF_NO_OTHER_OPTION} this indicates that the anser option should be rendered.
     *
     * @param character  the character
     * @param action     the action to check
     * @param onlyAction if the action is the only action so far
     * @return @{@code true} if the action can be handlerd, {@code false} otherwise
     */
    boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction);

    ActionResult handleAction(Character character, Action action, List<AnswerOption> answerOptions);

}
