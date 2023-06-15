package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.ActionSelector;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Attribute;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.INITIALIZE_ATTRIBUTE)
public class InitializeAttributeHandler extends AbstractActionHandler {

    private DiceService dice = new DiceService();

    @Override
    public ActionResult handleAction(ActionSelector selector, Character character, Action action, List<Action> answerOptions) {

        String attributeName = action.attribute();

        Attribute att = character.getAttributeByName(attributeName);
        int rand = dice.generate();

        att.maxValue(att.baseValue() + rand);
        att.add(att.baseValue() + rand);

        character.section(action.targetSection());

        return ActionResult.sectionFinished();

    }
}
