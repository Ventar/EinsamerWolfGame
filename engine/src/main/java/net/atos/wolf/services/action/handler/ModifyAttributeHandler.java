package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.GameSession;

import java.util.List;

@ActionHandler(ActionType.MODIFY_ATTRIBUTE)
public class ModifyAttributeHandler extends AbstractActionHandler {
    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {

        if (action.attribute().equals("ENDURANCE")) {
            session.character().endurance().remove(Integer.valueOf(action.value()));
        }

        if (action.attribute().equals("FOOD")) {
            session.character().food().add(Integer.valueOf(action.value()));
        }
        if (action.attribute().equals("GOLD")) {
            session.character().gold().add(Integer.valueOf(action.value()));
        }
        if (action.attribute().equals("BATTLE_STRENGTH")) {
            session.character().battleStrength().add(Integer.valueOf(action.value()));
        }

        return ActionResult.representActions(answerOptions);
    }

}
