package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.GET_KAI_SKILL)
public class GetKaiSkillHandler extends AbstractActionHandler {

    protected boolean checkExecutable(Character character, Action action, List<Action> answerOptions) {
        return character.getNumberOfSkills() < 5 && !character.hasSkill(action.skill());
    }


    public ActionResult handleAction(Character character, Action action, List<Action> answerOptions) {
        character.addSkill(action.skill());
        return ActionResult.representActions(answerOptions);
    }


}
