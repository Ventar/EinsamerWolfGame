package net.atos.wolf.service.gameengine.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@Slf4j
@ActionHandler(ActionType.GET_KAI_SKILL)
public class GetKaiSkillHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {
        return session.character().skills().size() < 5 && !session.character().skills().contains(action.skill());
    }

    @Override
    public void handleAction(GameSession session, Action action) {
        LOG.debug("Add Kai skill ::= [{}] to character", action.skill());
        session.character().skills().add(action.skill());
        session.modifiedAnswerOptions(session.section().actions());
    }


}
