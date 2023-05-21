package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;

public class ChangeSectionIfSpecialItemHandler extends AbstractActionHandler {


    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {

        if (character.hasSpecialItem(action.getSpecialItem())) {
            return true;
        }

        return false;

    }


}
