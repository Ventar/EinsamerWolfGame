package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.CHANGE_SECTION)
public class ChangeSectionHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {

        if (action.weapon() != null) {
            return action.weapon() == session.character().weaponOne() || action.weapon() == session.character().weaponTwo();
        }
        if (action.skill() != null) {
            return session.character().skills().contains(action.skill());
        }
        if (action.specialItem() != null) {
            return session.character().specialItemsList().contains(action.specialItem());

        }

        if (action.numberOfSkills() != 0) {
            return session.character().skills().size() >= action.numberOfSkills();
        }

        if (action.noOtherOption()) {
            return session.modifiedAnswerOptions() != null && session.modifiedAnswerOptions().size() == 0;
        }

        if (action.item() != null) {
            return session.character().items().contains(action.item());
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
