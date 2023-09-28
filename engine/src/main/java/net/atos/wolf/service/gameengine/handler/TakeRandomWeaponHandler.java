package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.data.Weapon;

@ActionHandler(ActionType.TAKE_RANDOM_WEAPON)
public class TakeRandomWeaponHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        switch (generate()) {
            case 0 -> session.character().weaponOne(Weapon.DAGGER);
            case 1 -> session.character().weaponOne(Weapon.MACE);
            case 2, 4 -> session.character().weaponOne(Weapon.SHORT_SWORD);
            case 3 -> session.character().weaponOne(Weapon.WARHAMMER);
            case 5, 7 -> session.character().weaponOne(Weapon.SWORD);
            case 6, 9 -> session.character().weaponOne(Weapon.AXE);
            case 8 -> session.character().weaponOne(Weapon.BROAD_SWORD);
        }

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
