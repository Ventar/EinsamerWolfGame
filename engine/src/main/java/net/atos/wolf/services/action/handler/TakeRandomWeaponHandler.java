package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.Weapon;

@ActionHandler(ActionType.TAKE_RANDOM_WEAPON)
public class TakeRandomWeaponHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        switch (generate()) {
            case 0 -> session.character().setWeaponOne(Weapon.DAGGER);
            case 1 -> session.character().setWeaponOne(Weapon.MACE);
            case 2, 4 -> session.character().setWeaponOne(Weapon.SHORT_SWORD);
            case 3 -> session.character().setWeaponOne(Weapon.WARHAMMER);
            case 5, 7 -> session.character().setWeaponOne(Weapon.SWORD);
            case 6, 9 -> session.character().setWeaponOne(Weapon.AXE);
            case 8 -> session.character().setWeaponOne(Weapon.BROAD_SWORD);
        }

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
