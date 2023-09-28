package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.TAKE_WEAPON)
public class TakeWeaponHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        if (action.hand() == 1) {
            session.character().weaponOne(action.weapon());
        } else {
            session.character().weaponTwo(action.weapon());
        }

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
