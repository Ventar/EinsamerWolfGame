package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.battle.BattleService;
import net.atos.wolf.services.GameSession;

import java.util.List;

@Slf4j
@ActionHandler(ActionType.BATTLE)
public class BattleHandler extends AbstractActionHandler {

    BattleService battleService = new BattleService();

    @Override
    public ActionResult handleAction(GameSession session, Action action, List<Action> answerOptions) {

        LOG.debug("Started battle with enemy ::= [{}]", action.battle().enemy());


        BattleService.BattleStatus battleStatus = battleService.executeBattleRound(session.character(), action.battle().enemy().get(0));
        session.battleRounds(session.battleRounds() + 1);


        if (battleStatus.equals(BattleService.BattleStatus.TIE)) {
            answerOptions.get(0).text("Führe den Kampf fort...");
            return ActionResult.representActions(answerOptions);
        } else if (battleStatus.equals(BattleService.BattleStatus.ENEMY_DIED)) {
            for (BattleRoundTarget brt : action.battle().targetSectionBattleRound()) {
                if (session.battleRounds() >= brt.min() && session.battleRounds() <= brt.max()) {
                    session.section(brt.targetSection());
                    return ActionResult.sectionFinished();
                }
            }
        }

        return ActionResult.characterDied();

    }

}
