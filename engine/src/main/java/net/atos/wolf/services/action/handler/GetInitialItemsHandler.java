package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.SpecialItem;

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
