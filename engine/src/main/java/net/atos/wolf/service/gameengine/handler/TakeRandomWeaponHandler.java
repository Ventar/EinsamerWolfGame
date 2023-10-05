package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.data.Item;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

import java.util.List;

@ActionHandler(ActionType.TAKE_RANDOM_WEAPON)
public class TakeRandomWeaponHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        List<Item> weapon = registry().bookRepository().getWeapons();


        int rdm = registry().dice().generate(0, weapon.size() - 1);

        if (session.character().weaponOne() == null) {
            session.character().weaponOne(weapon.get(rdm));
        } else if (session.character().weaponTwo() == null) {
            session.character().weaponTwo(weapon.get(rdm));
        }


        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
