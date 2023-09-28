package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;


@ActionHandler(ActionType.TAKE_ITEM)
public class GetItemHandler extends AbstractActionHandler {
    @Override
    public void handleAction(GameSession session, Action action) {

        session.character().items().add(action.item());

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }
}
