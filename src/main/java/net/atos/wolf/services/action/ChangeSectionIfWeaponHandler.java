package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;

public class ChangeSectionIfWeaponHandler extends AbstractActionHandler {

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {

        if (action.getWeapon() == character.getWeaponOne() || action.getWeapon() == character.getWeaponTwo()) {
            return true;
        }

        return false;

    }

}
