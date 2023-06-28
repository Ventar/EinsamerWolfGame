package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.battle.BattleService;

@Slf4j
@ActionHandler(ActionType.BATTLE)
public class BattleHandler extends AbstractActionHandler {

    BattleService battleService = new BattleService();

    @Override
    public void handleAction(GameSession session, Action action) {

        LOG.debug("Started battle with enemy ::= [{}]", action.battle().enemy());

        BattleService.BattleStatus battleStatus = battleService.executeBattleRound(session.character(), action.battle().enemy().get(0));
        session.battleRounds(session.battleRounds() + 1);

        if (battleStatus.equals(BattleService.BattleStatus.TIE)) {
            action.text("Führe den Kampf fort...");
            session.modifiedAnswerOptions().clear();
            session.modifiedAnswerOptions().add(action);
            return;
        }

        if (battleStatus.equals(BattleService.BattleStatus.ENEMY_DIED)) {
            for (BattleRoundTarget brt : action.battle().targetSectionBattleRound()) {
                if (session.battleRounds() >= brt.min() && session.battleRounds() <= brt.max()) {
                    session.section(getSection(brt.targetSection()));
                    return;
                }
            }
        }

        throw new RuntimeException("YOU DIED !!!!");

    }

}
