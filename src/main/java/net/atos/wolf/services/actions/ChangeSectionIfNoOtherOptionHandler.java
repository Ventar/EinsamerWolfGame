package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

public class ChangeSectionIfNoOtherOptionHandler extends AbstractActionHandler {

    public ChangeSectionIfNoOtherOptionHandler() {
        super(ActionType.CHANGE_SECTION_IF_NO_OTHER_OPTION);
    }

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {
        return onlyAction;
    }


}
