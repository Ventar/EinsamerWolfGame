package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.MODIFY_ATTRIBUTE)
public class ModifyAttributeHandler extends AbstractActionHandler {

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {

        if (action.attribute().equals("ENDURANCE")) {
            character.endurance().remove(Integer.valueOf(action.value()));
        }

        if (action.attribute().equals("FOOD")) {
            character.food().add(Integer.valueOf(action.value()));
        }
        if (action.attribute().equals("GOLD")){
            character.gold().add(Integer.valueOf(action.value()));
        }
        if (action.attribute().equals("BATTLE_STRENGTH")){
            character.battleStrength().add(Integer.valueOf(action.value()));
        }

        return ActionResult.representActions(answerOptions);
    }

}
