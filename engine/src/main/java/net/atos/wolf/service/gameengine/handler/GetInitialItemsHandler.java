package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

import java.util.List;

@ActionHandler(ActionType.GET_INITIAL_ITEMS)
public class GetInitialItemsHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        session.character().gold().add(generate() + 1);

        session.section(getSection(action.targetSection()));
    }

}
