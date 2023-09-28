package net.atos.wolf.service.gameengine.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.*;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.service.BattleService;

@Slf4j
@ActionHandler(ActionType.BATTLE)
public class BattleHandler extends AbstractActionHandler {

    BattleService battleService = new BattleService();

    @Override
    public void handleAction(GameSession session, Action action) {
        
        LOG.debug("Started battle with enemy ::= [{}]", action.battle().enemy());

        BattleLogEntry entry = new BattleLogEntry();
        entry.battleRound(session.battleRounds());
        session.battleLog().add(0, entry);

        Enemy enemy = nextEnemy(action);
        
        BattleService.BattleStatus battleStatus = battleService.executeBattleRound(session, enemy, entry);

        if (battleStatus == BattleService.BattleStatus.TIE) {
            battleContinue(session, action);
        } else if (battleStatus == BattleService.BattleStatus.ENEMY_DIED) {
            if (nextEnemy(action) != null) {
                battleContinue(session, action);
            } else {
                battleWon(session, action);
            }
        } else {
            battleLoose(session, action);
        }

    }

    /**
     * Returns the next enemy from the passed action that has an endurance value higher than zero.
     *
     * @param action action with the list of enemies to filter
     * @return the next enemy that matches the described criteria, if
     * no enemy matches the criteria {@code  null} is returned
     */
    public Enemy nextEnemy(Action action) {
        for (Enemy enemy : action.battle().enemy()) {
            if (enemy.endurance() > 0) {
                return enemy;
            }
        }

        return null;
    }

    /**
     * This method modifies the game session in a way, that the player sees only
     * a single action that allows him to proceed to the next section.
     *
     * @param session the session
     * @param action  the current action
     */
    public void battleWon(GameSession session, Action action) {
        for (BattleRoundTarget brt : action.battle().targetSectionBattleRound()) {
            if (session.battleRounds() >= brt.min() && session.battleRounds() <= brt.max()) {

                session.battleRounds(1);
                session.battleLog().clear();

                // add new action to continue the battle
                Action changeSection = new Action(ActionType.CHANGE_SECTION, "Du hast den Kampf gewonnen, gehe weiter...");
                changeSection.targetSection(brt.targetSection());
                session.modifiedAnswerOptions().clear();
                session.modifiedAnswerOptions().add(changeSection);

                return;
            }
        }
    }

    /**
     * Modifies the game session so that the player stays in battle and proceeds with the next battle round
     *
     * @param session the session
     * @param action  the current action
     */
    public void battleContinue(GameSession session, Action action) {
        action.text("Führe den Kampf fort...");
        session.modifiedAnswerOptions().clear();
        session.modifiedAnswerOptions().add(action);
    }

    /**
     * Player has lost all endurance...GAME OVER...YOU DIED...dark souls...you know...
     *
     * @param session the session
     * @param action  the current action
     */
    public void battleLoose(GameSession session, Action action) {
        session.battleRounds(1);
        session.battleLog().clear();

        // add new action to continue the battle
        Action changeSection = new Action(ActionType.CHANGE_SECTION, "YOU DIED");
        changeSection.targetSection(4004);
        session.modifiedAnswerOptions().clear();
        session.modifiedAnswerOptions().add(changeSection);
    }

}
