package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.GameSession;

import java.util.ArrayList;
import java.util.List;

@ActionHandler(ActionType.TAKE_WEAPON)
public class TakeWeaponHandler extends AbstractActionHandler {

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {

        if (action.hand() == 1) {
            session.character().setWeaponOne(action.weapon());
        } else {
            session.character().setWeaponTwo(action.weapon());
        }

        List<Action> ans = new ArrayList<>(answerOptions);
        ans.remove(action);

        return ActionResult.representActions(ans);
    }

}
