package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;
import net.atos.wolf.services.AnswerOption;

import java.util.ArrayList;
import java.util.List;

public class TakeWeaponHandler extends AbstractActionHandler {

    public TakeWeaponHandler() {
        super(ActionType.TAKE_WEAPON);
    }

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {
        return true;
    }


    @Override
    public ActionResult handleAction(Character character, Action action, List<AnswerOption> answerOptions) {

        // TODO: Change weapon of character


        List<Action> ans = new ArrayList<>();

        for (AnswerOption an : answerOptions) {
            if (!an.action().equals(action)) {
                ans.add(an.action());
            }
        }

        ActionResult res = new ActionResult();
        res.setDead(false);
        res.setActions(ans);
        res.setTargetSection(-1);


        return res;
    }

}
