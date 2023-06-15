package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.ActionSelector;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.ArrayList;
import java.util.List;

@ActionHandler(ActionType.TAKE_BACKPACK)
public class TakeBackpackHandler extends AbstractActionHandler {

    @Override
    public ActionResult handleAction(ActionSelector selector, Character character, Action action, List<Action> answerOptions) {

        character.addBackpack();

        List<Action> ans = new ArrayList<>(answerOptions);
        ans.remove(action);

        return ActionResult.representActions(ans);
    }

}
