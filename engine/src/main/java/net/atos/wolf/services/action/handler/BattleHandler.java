package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.battle.BattleService;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.action.Enemy;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.BATTLE)
public class BattleHandler extends AbstractActionHandler {

    BattleService battleService = new BattleService();

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {

        BattleService.BattleStatus battleStatus = battleService.executeBattleRound(character, action.battle().enemy().get(0));
        action.battleRounds(action.battleRounds() + 1);


        if (battleStatus.equals(BattleService.BattleStatus.TIE)) {
            answerOptions.get(0).text("Führe den Kampf fort...");
            return ActionResult.representActions(answerOptions);
        } else if (battleStatus.equals(BattleService.BattleStatus.ENEMY_DIED)) {
            for (BattleRoundTarget brt : action.battle().targetSectionBattleRound()) {
                if (action.battleRounds() >= brt.min() && action.battleRounds() <= brt.max()) {
                    character.section(brt.targetSection());
                    return ActionResult.sectionFinished();
                }
            }
        }

        return ActionResult.characterDied();

    }

}
