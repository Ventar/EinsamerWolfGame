package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.data.SpecialItem;

import java.util.List;

@ActionHandler(ActionType.GET_INITIAL_ITEMS)
public class GetInitialItemsHandler extends AbstractActionHandler {

    public void handleAction(GameSession session, Action action, List<Action> answerOptions) {

        session.character().specialItemsList().add(SpecialItem.KAI_ROBE);
        session.character().specialItemsList().add(SpecialItem.BELT);
        session.character().specialItemsList().add(SpecialItem.MAP);
        session.character().gold().add(generate());

        session.section(getSection(action.targetSection()));
    }

}
