package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.GameSession;

import java.util.ArrayList;
import java.util.List;

@ActionHandler(ActionType.TAKE_BACKPACK)
public class TakeBackpackHandler extends AbstractActionHandler {

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {

        session.character().addBackpack();

        List<Action> ans = new ArrayList<>(answerOptions);
        ans.remove(action);

        return ActionResult.representActions(ans);
    }

}
