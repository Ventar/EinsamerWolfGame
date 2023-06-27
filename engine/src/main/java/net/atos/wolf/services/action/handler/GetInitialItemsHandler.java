package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.SpecialItem;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.GET_INITIAL_ITEMS)
public class GetInitialItemsHandler extends AbstractActionHandler {

    DiceService diceService = new DiceService();


    public ActionResult handleAction(Character character, Action action, List<Action> answerOptions) {
        int i = diceService.generate();
        character.addSpecialItem(SpecialItem.KAI_ROBE);
        character.addSpecialItem(SpecialItem.BELT);
        character.addSpecialItem(SpecialItem.MAP);
        character.gold().add(i);

        character.section(action.targetSection());

        return ActionResult.sectionFinished();
    }

}
