package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.AnswerOption;

import java.util.ArrayList;
import java.util.List;

public class TakeWeaponHandler extends AbstractActionHandler {

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

        return ActionResult.representActions(ans);
    }

}
