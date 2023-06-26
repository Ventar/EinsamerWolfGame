package net.atos.wolf.services.action.handler;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.SpecialItem;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.IF_RANDOM_AND_SKILL)
@Slf4j
public class ChangeSectionIfRandomAndSkillHandler extends AbstractActionHandler {

    DiceService diceService = new DiceService();

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {


        int rdm = diceService.generate();

        if (character.hasSkill(action.skill())) {
            rdm = rdm + 1;
            LOG.debug("Character has skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        } else {
            LOG.debug("Character has no skill ::= [{}], result is ::= [{}], target section is ::= [{}]", action.skill(), rdm, action.randomSection().get(rdm));
        }

        character.section(action.randomSection().get(rdm));


        return ActionResult.sectionFinished();
    }


}
