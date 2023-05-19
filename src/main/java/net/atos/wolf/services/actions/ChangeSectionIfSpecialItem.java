package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

public class ChangeSectionIfSpecialItem extends AbstractActionHandler {


    public ChangeSectionIfSpecialItem() {
        super(ActionType.CHANGE_SECTION_IF_SPECIAL_ITEM);
    }


    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {

        if (character.hasSpecialItem(action.getSpecialItem())) {
            return true;
        }

        return false;

    }


}
