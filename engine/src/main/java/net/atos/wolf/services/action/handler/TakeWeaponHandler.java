package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ActionHandler(ActionType.TAKE_WEAPON)
public class TakeWeaponHandler extends AbstractActionHandler {

    @Override
    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {

        Action a1 = new Action();
        a1.weapon(action.weapon());
        a1.type(ActionType.TAKE_WEAPON);
        a1.text("Waffe 1");

        Action a2 = new Action();
        a2.weapon(action.weapon());
        a2.type(ActionType.TAKE_WEAPON);
        a2.text("Waffe 2");

        Action selectedAction = ui.render("Welche Waffe möchtest du ersetzen ?", null, Arrays.asList(a1, a2));

        if (selectedAction.equals(a1)) {
            character.setWeaponOne(action.weapon());
        } else {
            character.setWeaponTwo(action.weapon());
        }


        List<Action> ans = new ArrayList<>(answerOptions);
        ans.remove(action);

        return ActionResult.representActions(ans);
    }

}
