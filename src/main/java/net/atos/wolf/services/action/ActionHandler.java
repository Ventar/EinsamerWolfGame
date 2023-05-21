package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.AnswerOption;
import net.atos.wolf.services.section.Section;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

public interface ActionHandler {

    /**
     * Checks if an action can be handled by this action handler. This depends on the {@link ActionType} that was defined for the handler and the action. The
     * method implementation is based on the implementing class, i.e. the class has to have a name that matches the action type followed by the word handler. If you want to create
     * an action handler for the type {@code TAKE_WEAPON} the class name has to be TakeWeaponHandler. The base implementation of all handler will parse the class name and check if
     * it is valid.
     *
     * @param action the action to check
     *
     * @return {@code true} if the action can be handled, {@code false} otherwise
     */
    boolean canHandle(Action action);

    /**
     * Used to decide if an {@link AnswerOption} is rendered by the {@link UIService}. Inside of the {@link
     * net.atos.wolf.services.GameEngine} all possible {@link Action}s of a book {@link Section} are passed to the available action handlers and this method
     * decides if the answer option is rendered.
     * <br>
     * The only action parameter is used to inform the handler that it is the only handler that is used for the specific section so far, i.e. in case of the action type {@link
     * ActionType#CHANGE_SECTION_IF_NO_OTHER_OPTION} this indicates that the anser option should be rendered.
     *
     * @param character  the character
     * @param action     the action to check
     * @param onlyAction if the action is the only action so far
     *
     * @return @{@code true} if the action can be handlerd, {@code false} otherwise
     */
    boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction);

    ActionResult handleAction(Character character, Action action, List<AnswerOption> answerOptions);

}
