package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.TAKE_RANDOM_WEAPON)
public class TakeRandomWeaponHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        switch (generate()) {
            case 0 -> session.character().weaponOne(action.weapon().id("DAGGER"));
            case 1 -> session.character().weaponOne(action.weapon().id("MACE"));
            case 2, 4 -> session.character().weaponOne(action.weapon().id("SHORT_SWORD"));
            case 3 -> session.character().weaponOne(action.weapon().id("WARHAMMER"));
            case 5, 7 -> session.character().weaponOne(action.weapon().id("SWORD"));
            case 6, 9 -> session.character().weaponOne(action.weapon().id("AXE"));
            case 8 -> session.character().weaponOne(action.weapon().id("BROAD_SWORD"));
        }

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
