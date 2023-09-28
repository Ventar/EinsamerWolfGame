package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.TAKE_BACKPACK)
public class TakeBackpackHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        session.character().hasBackpack(true);

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }

}
