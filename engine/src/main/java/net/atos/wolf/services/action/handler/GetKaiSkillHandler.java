package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;

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
