package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;

@ActionHandler(ActionType.GET_KAI_SKILL)
public class GetKaiSkillHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {
        return session.character().getNumberOfSkills() < 5 && !session.character().hasSkill(action.skill());
    }

    @Override
    public void handleAction(GameSession session, Action action) {
        session.character().addSkill(action.skill());

        session.modifiedAnswerOptions(session.section().actions());
    }


}
