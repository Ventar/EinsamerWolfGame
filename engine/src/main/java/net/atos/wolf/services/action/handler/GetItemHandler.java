package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;


@ActionHandler(ActionType.TAKE_ITEM)
public class GetItemHandler extends AbstractActionHandler {
    @Override
    public void handleAction(GameSession session, Action action) {

        session.character().addItemToBackpack(action.item());

        session.modifiedAnswerOptions(session.section().actions());
        session.modifiedAnswerOptions().remove(action);

    }
}
