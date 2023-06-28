package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;

@ActionHandler(ActionType.TAKE_WEAPON)
public class TakeWeaponHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        if (action.hand() == 1) {
            session.character().setWeaponOne(action.weapon());
        } else {
            session.character().setWeaponTwo(action.weapon());
        }

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
