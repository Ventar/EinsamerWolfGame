package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.GameSession;

import java.util.List;

@ActionHandler(ActionType.IF_RANDOM_AND_SKILL)
@Slf4j
public class ChangeSectionIfRandomAndSkillHandler extends AbstractActionHandler {

    DiceService diceService = new DiceService();

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {


        int rdm = diceService.generate();

        if (session.character().hasSkill(action.skill())) {
            rdm = rdm + 1;
            LOG.debug("Character has skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        } else {
            LOG.debug("Character has no skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        }

        session.character().section(action.randomSection().get(rdm));


        return ActionResult.sectionFinished();
    }


}
