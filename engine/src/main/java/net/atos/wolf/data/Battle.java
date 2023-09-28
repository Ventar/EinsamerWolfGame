package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks if the Enemy and the Battle Rounds are given
 * @author Noel Masur
 * @since 2023-08-01
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
public class Battle {
    /**
     * List of Enemies
     */
    public List<Enemy> enemy;
    /**
     * List of BattleRounds
     */
    private List<BattleRoundTarget> targetSectionBattleRound;

    public Battle() {
    }

    /**
     * Checks if a Enemy or is there, if not then adds a new enemy to the current list
     * @param battle
     */
    public Battle(Battle battle) {
        if (battle.enemy != null) {
            this.enemy = new ArrayList<>();
            for (Enemy e : battle.enemy) {
                this.enemy.add(new Enemy(e));
            }
        }
        if (battle.targetSectionBattleRound != null) {
            this.targetSectionBattleRound = new ArrayList<>();
            for (BattleRoundTarget battleRoundTarget : battle.targetSectionBattleRound) {
                this.   targetSectionBattleRound.add(new BattleRoundTarget(battleRoundTarget));
            }
        }
    }
}

