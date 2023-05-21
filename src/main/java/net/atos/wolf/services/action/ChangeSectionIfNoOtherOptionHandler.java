package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;

public class ChangeSectionIfNoOtherOptionHandler extends AbstractActionHandler {

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {
        return onlyAction;
    }


}
