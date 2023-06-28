package net.atos.wolf.services.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
public class Battle {

    private List<Enemy> enemy;
    private List<BattleRoundTarget> targetSectionBattleRound;

    public Battle() {
    }


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
                this.targetSectionBattleRound.add(new BattleRoundTarget(battleRoundTarget));
            }
        }
    }
}

