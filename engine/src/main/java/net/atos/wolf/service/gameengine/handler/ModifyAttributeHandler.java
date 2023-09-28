package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.MODIFY_ATTRIBUTE)
public class ModifyAttributeHandler extends AbstractActionHandler {

    @Override
    public void handleAction(GameSession session, Action action) {

        if (action.attribute().equals("ENDURANCE")) {
            session.character().endurance().remove(Integer.parseInt(action.value()));
        }

        if (action.attribute().equals("FOOD")) {
            session.character().food().add(Integer.parseInt(action.value()));
        }
        if (action.attribute().equals("GOLD")) {
            session.character().gold().add(Integer.parseInt(action.value()));
        }
        if (action.attribute().equals("BATTLE_STRENGTH")) {
            session.character().battleStrength().add(Integer.parseInt(action.value()));
        }

        session.modifiedAnswerOptions(session.section().actions());
    }

}
