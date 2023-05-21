package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.Character;

@ActionHandler(ActionType.CHANGE_SECTION_IF_SPECIAL_ITEM)
public class ChangeSectionIfSpecialItemHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(Character character, Action action, boolean onlyAction) {
        return character.hasSpecialItem(action.getSpecialItem());
    }

}
