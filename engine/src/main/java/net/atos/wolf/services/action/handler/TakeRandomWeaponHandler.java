package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.SpecialItem;
import net.atos.wolf.services.character.Weapon;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.TAKE_RANDOM_WEAPON)
public class TakeRandomWeaponHandler  extends AbstractActionHandler {

    DiceService diceService = new DiceService();

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {
        int i = diceService.generate();

        switch (i) {
            case 0 -> character.setWeaponOne(Weapon.DAGGER);
            case 1 -> character.setWeaponOne(Weapon.MACE);
            case 2, 4 -> character.setWeaponOne(Weapon.SHORT_SWORD);
            case 3 -> character.setWeaponOne(Weapon.WARHAMMER);
            case 5, 7 -> character.setWeaponOne(Weapon.SWORD);
            case 6, 9 -> character.setWeaponOne(Weapon.AXE);
            case 8 -> character.setWeaponOne(Weapon.BROAD_SWORD);
        }

        answerOptions.remove(action);

        return ActionResult.representActions(answerOptions);
    }

}
