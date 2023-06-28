package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.SpecialItem;

import java.util.List;

@ActionHandler(ActionType.GET_INITIAL_ITEMS)
public class GetInitialItemsHandler extends AbstractActionHandler {

    public void handleAction(GameSession session, Action action, List<Action> answerOptions) {

        session.character().addSpecialItem(SpecialItem.KAI_ROBE);
        session.character().addSpecialItem(SpecialItem.BELT);
        session.character().addSpecialItem(SpecialItem.MAP);
        session.character().gold().add(generate());

        session.section(getSection(action.targetSection()));
    }

}
