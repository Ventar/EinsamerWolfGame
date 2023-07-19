package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.Attribute;
import net.atos.wolf.services.common.DiceService;

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
