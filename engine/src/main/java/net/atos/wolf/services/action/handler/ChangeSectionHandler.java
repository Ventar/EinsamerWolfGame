package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.CHANGE_SECTION)
public class ChangeSectionHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(Character character, Action action, List<Action> answerOptions) {

        if (action.weapon() != null) {
            return action.weapon() == character.weaponOne() || action.weapon() == character.weaponTwo();
        }
        if (action.skill() != null) {
            return character.hasSkill(action.skill());
        }
        if (action.specialItem() != null) {
            return character.hasSpecialItem(action.specialItem());
        }

        if (action.numberOfSkills() != 0) {
            return character.getNumberOfSkills() >= action.numberOfSkills();
        }

        if (action.noOtherOption()) {
            return answerOptions.size() == 0;
        }

        return true;
    }

    @Override
    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {

        if (action.randomSection() != null) {
            return ActionResult.changeSection(action.randomSection().get(diceService.generate()));
        }

        return ActionResult.changeSection(action.targetSection());
    }

}
