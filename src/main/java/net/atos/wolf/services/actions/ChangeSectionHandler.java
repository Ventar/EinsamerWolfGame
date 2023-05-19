package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

public class ChangeSectionHandler extends AbstractActionHandler {

    public ChangeSectionHandler() {
        super(ActionType.CHANGE_SECTION);
    }

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {
        return true;
    }


}
