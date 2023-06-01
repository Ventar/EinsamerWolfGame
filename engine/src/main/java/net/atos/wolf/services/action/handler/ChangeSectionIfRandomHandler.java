package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.Weapon;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.section.Section;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.CHANGE_SECTION_IF_RANDOM)
public class ChangeSectionIfRandomHandler extends AbstractActionHandler {


    DiceService diceService = new DiceService();

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {
        return ActionResult.changeSection(action.randomSection().get(diceService.generate()));
    }

}
