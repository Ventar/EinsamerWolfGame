package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.MODIFY_ATTRIBUTE)
public class ModifyAttributeHandler extends AbstractActionHandler {

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {
        character.endurance().remove(Integer.valueOf(action.value()));
        return ActionResult.representActions(answerOptions);
    }

}
