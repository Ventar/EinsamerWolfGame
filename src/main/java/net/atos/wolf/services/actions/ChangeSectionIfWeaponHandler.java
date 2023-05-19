package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;

public class ChangeSectionIfWeaponHandler extends AbstractActionHandler {

    public ChangeSectionIfWeaponHandler() {
        super(ActionType.CHANGE_SECTION_IF_WEAPON);
    }

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {

        if (action.getWeapon() == character.getWeaponOne() || action.getWeapon() == character.getWeaponTwo()) {
            return true;
        }

        return false;

    }

}
