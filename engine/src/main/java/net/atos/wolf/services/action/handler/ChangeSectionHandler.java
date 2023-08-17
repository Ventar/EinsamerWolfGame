package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;

@ActionHandler(ActionType.CHANGE_SECTION)
public class ChangeSectionHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {

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
            return session.modifiedAnswerOptions() != null && session.modifiedAnswerOptions().size() == 0;
        }

        if (action.item() != null) {
            return session.character().hasItem(action.item());
        }

        return true;
    }

    @Override
    public void handleAction(GameSession session, Action action) {

        if (action.randomSection() != null) {
            session.section(getSection(action.randomSection().get(generate())));
        } else {
            session.section(getSection(action.targetSection()));
        }

    }

}
