package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.battle.BattleService;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.Enemy;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.CHANGE_SECTION_IF_BATTLE_GOES)
public class ChangeSectionIfBattleGoes extends AbstractActionHandler {

    BattleService battleService = new BattleService();

    public ActionResult handleAction(Enemy enemy, Character character, Action action, List<Action> answerOptions) {

        battleService.calculateBattleQuotient(character,enemy);

 //       if ()

        return ActionResult.changeSection(action.targetSection());
    }
}
