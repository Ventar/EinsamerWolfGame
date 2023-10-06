package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.Action;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;

@ActionHandler(ActionType.CHANGE_SECTION)
public class ChangeSectionHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {

        if (action.weapon() != null) {
            return (session.character().weaponOne() != null && action.weapon().id().equals(session.character().weaponOne().id())) ||
                    (session.character().weaponTwo() != null && action.weapon().id().equals(session.character().weaponTwo().id()));
        }
        if (action.skill() != null) {
            return session.character().skills().contains(action.skill());
        }


        if (action.numberOfSkills() != null && action.numberOfSkills() != 0) {
            return session.character().skills().size() >= action.numberOfSkills();
        }

        if (action.noOtherOption() != null && action.noOtherOption()) {
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
