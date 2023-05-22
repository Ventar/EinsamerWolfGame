package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.ArrayList;
import java.util.List;

@ActionHandler(ActionType.TAKE_WEAPON)
public class TakeWeaponHandler extends AbstractActionHandler {

    @Override
    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {

        // TODO: Change weapon of character

        List<Action> ans = new ArrayList<>(answerOptions);
        ans.remove(action);

        return ActionResult.representActions(ans);
    }

}
