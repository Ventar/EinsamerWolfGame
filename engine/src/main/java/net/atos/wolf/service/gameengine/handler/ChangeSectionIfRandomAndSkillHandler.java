package net.atos.wolf.service.gameengine.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.IF_RANDOM_AND_SKILL)
@Slf4j
public class ChangeSectionIfRandomAndSkillHandler extends AbstractActionHandler {
    @Override
    public void handleAction(GameSession session, Action action) {

        int rdm = generate();

        if (session.character().skills().contains(action.skill())) {
            rdm = rdm + 1;
            LOG.debug("Character has skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        } else {
            LOG.debug("Character has no skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        }

        session.section(getSection(action.randomSection().get(rdm)));

    }


}
