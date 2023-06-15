package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.SpecialItem;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.IF_RANDOM_AND_SKILL)
public class ChangeSectionIfRandomAndSkillHandler extends AbstractActionHandler {

    DiceService diceService = new DiceService();

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {

        int rdm = diceService.generate();

         if (character.hasSkill(action.skill())){

            rdm =  rdm + 1;
         }


        return ActionResult.changeSection(action.targetSection());
    }


}
