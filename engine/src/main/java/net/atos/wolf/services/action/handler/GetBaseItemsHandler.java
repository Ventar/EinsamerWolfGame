package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;

@ActionHandler(ActionType.TAKE_BASE_ITEM)
public class GetBaseItemsHandler extends AbstractActionHandler {
    @Override
    public void handleAction(GameSession session, Action action) {

        session.character().addBaseItemToBackpack(action.baseItems());

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }
}

