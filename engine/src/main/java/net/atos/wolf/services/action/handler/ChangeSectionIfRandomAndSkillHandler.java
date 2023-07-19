package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;

@ActionHandler(ActionType.IF_RANDOM_AND_SKILL)
@Slf4j
public class ChangeSectionIfRandomAndSkillHandler extends AbstractActionHandler {
    @Override
    public void handleAction(GameSession session, Action action) {

        int rdm = generate();

        if (session.character().hasSkill(action.skill())) {
            rdm = rdm + 1;
            LOG.debug("Character has skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        } else {
            LOG.debug("Character has no skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        }

        session.section(getSection(action.randomSection().get(rdm)));

    }


}
