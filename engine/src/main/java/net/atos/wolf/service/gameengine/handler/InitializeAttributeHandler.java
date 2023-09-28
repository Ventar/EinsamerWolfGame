package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.data.Attribute;
import net.atos.wolf.service.DiceService;

@ActionHandler(ActionType.INITIALIZE_ATTRIBUTE)
public class InitializeAttributeHandler extends AbstractActionHandler {

    private DiceService dice = new DiceService();

    @Override
    public void handleAction(GameSession session, Action action) {

        String attributeName = action.attribute();

        Attribute att = session.character().getAttributeByName(attributeName);
        int rand = dice.generate();

        att.maxValue(att.baseValue() + rand);
        att.add(att.baseValue() + rand);

        session.section(getSection(action.targetSection()));

    }
}
