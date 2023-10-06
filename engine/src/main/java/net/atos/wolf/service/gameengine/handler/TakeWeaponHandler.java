package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.TAKE_WEAPON)
public class TakeWeaponHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {
        return session.character().weaponOne() == null || session.character().weaponTwo() == null;
    }

    @Override
    public void handleAction(GameSession session, Action action) {

        if (session.character().weaponOne() == null) {
            session.character().weaponOne(action.weapon());
        } else
            session.character().weaponTwo(action.weapon());

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
