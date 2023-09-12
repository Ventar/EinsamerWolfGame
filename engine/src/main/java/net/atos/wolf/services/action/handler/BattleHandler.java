package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.BattleLogEntry;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.battle.BattleService;

import java.util.ArrayList;

@Slf4j
@ActionHandler(ActionType.BATTLE)
public class BattleHandler extends AbstractActionHandler {

    BattleService battleService = new BattleService();

    @Override
    public void handleAction(GameSession session, Action action) {

        LOG.debug("Started battle with enemy ::= [{}]", action.battle().enemy());

        BattleLogEntry entry = new BattleLogEntry();
        entry.battleRound(session.battleRounds());
        session.battleLog().add(entry);

        BattleService.BattleStatus battleStatus = battleService.executeBattleRound(session, action.battle().enemy().get(0), entry);
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

                    // reset session for next battle
                    session.battleRounds(1);
                    session.battleLog().clear();
                   // session.battleLog().add(new BattleLogEntry("Ein neuer Kampf startet..."));

                    // add new action to continue the battle
                    Action changeSection = new Action(ActionType.CHANGE_SECTION, "Du hast den Kampf Gewonnen, gehe weiter...");
                    changeSection.targetSection(brt.targetSection());
                    session.modifiedAnswerOptions().clear();
                    session.modifiedAnswerOptions().add(changeSection);
                    entry.battleRound(session.battleRounds());

                    //session.section(getSection(brt.targetSection()));


                    return;
                }
            }
        }

        throw new RuntimeException("YOU DIED !!!!");

    }

}
