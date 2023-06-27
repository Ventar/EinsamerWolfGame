package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Weapon;
import net.atos.wolf.services.GameSession;

import java.util.List;

@ActionHandler(ActionType.TAKE_RANDOM_WEAPON)
public class TakeRandomWeaponHandler extends AbstractActionHandler {

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {
        int i = diceService.generate();

        switch (i) {
            case 0 -> session.character().setWeaponOne(Weapon.DAGGER);
            case 1 -> session.character().setWeaponOne(Weapon.MACE);
            case 2, 4 -> session.character().setWeaponOne(Weapon.SHORT_SWORD);
            case 3 -> session.character().setWeaponOne(Weapon.WARHAMMER);
            case 5, 7 -> session.character().setWeaponOne(Weapon.SWORD);
            case 6, 9 -> session.character().setWeaponOne(Weapon.AXE);
            case 8 -> session.character().setWeaponOne(Weapon.BROAD_SWORD);
        }

        answerOptions.remove(action);

        return ActionResult.representActions(answerOptions);
    }

}
