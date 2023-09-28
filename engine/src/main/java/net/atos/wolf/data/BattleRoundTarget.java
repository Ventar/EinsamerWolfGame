package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Check how much Rounds the Battle can go
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BattleRoundTarget {
    /**
     * Max Value of Rounds
     */
    private int max;
    /**
     * Min Value of Rounds
     */
    private int min;
    /**
     * The next Section
     */
    private int targetSection;

    public BattleRoundTarget(){};

    public BattleRoundTarget(BattleRoundTarget battleRoundTarget){
        this.max = battleRoundTarget.max;
        this.min = battleRoundTarget.min;
        this.targetSection = battleRoundTarget.targetSection;
    }


}
