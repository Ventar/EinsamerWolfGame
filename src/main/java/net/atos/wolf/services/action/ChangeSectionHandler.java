package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;

public class ChangeSectionHandler extends AbstractActionHandler {


    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {
        return true;
    }


}
