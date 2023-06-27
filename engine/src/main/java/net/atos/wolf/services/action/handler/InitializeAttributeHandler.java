package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Attribute;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.GameSession;

import java.util.List;

@ActionHandler(ActionType.INITIALIZE_ATTRIBUTE)
public class InitializeAttributeHandler extends AbstractActionHandler {

    private DiceService dice = new DiceService();

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {

        String attributeName = action.attribute();

        Attribute att = session.character().getAttributeByName(attributeName);
        int rand = dice.generate();

        att.maxValue(att.baseValue() + rand);
        att.add(att.baseValue() + rand);

        session.character().section(action.targetSection());

        return ActionResult.sectionFinished();

    }
}
