package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.GameSession;

import java.util.List;

@ActionHandler(ActionType.CHANGE_SECTION)
public class ChangeSectionHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action, List<Action> answerOptions) {

        if (action.weapon() != null) {
            return action.weapon() == session.character().weaponOne() || action.weapon() == session.character().weaponTwo();
        }
        if (action.skill() != null) {
            return session.character().hasSkill(action.skill());
        }
        if (action.specialItem() != null) {
            return session.character().hasSpecialItem(action.specialItem());
        }

        if (action.numberOfSkills() != 0) {
            return session.character().getNumberOfSkills() >= action.numberOfSkills();
        }

        if (action.noOtherOption()) {
            return answerOptions.size() == 0;
        }

        if (action.item() != null) {
            return session.character().hasItem(action.item());
        }

        return true;
    }

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {

        if (action.randomSection() != null) {
            session.section(action.randomSection().get(diceService.generate()));
            return ActionResult.sectionFinished();
        }

        session.section(action.targetSection());

        return ActionResult.sectionFinished();
    }

}
