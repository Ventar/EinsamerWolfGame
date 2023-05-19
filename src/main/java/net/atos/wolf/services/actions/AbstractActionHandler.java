package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

public abstract class AbstractActionHandler implements ActionHandler {

    private ActionType type;

    public AbstractActionHandler(ActionType type) {
        this.type = type;

        System.out.println(getClass().getSimpleName());

        // Generate the type from the classname

    }

    @Override
    public boolean canHandle(Action action) {
        if (action.getType() == type) {
            return true;
        }
        return false;
    }

    @Override
    public ActionResult handleAction(Character character, Action action, List<AnswerOption> answerOptions) {
        ActionResult res = new ActionResult();
        res.setDead(true);
        res.setActions(null);
        res.setTargetSection(action.getTargetSection());
        return res;
    }

}
