package net.atos.wolf.services.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class BattleRoundTarget {

    private int max;
    private int min;
    private int targetSection;

    public BattleRoundTarget(){};

    public BattleRoundTarget(BattleRoundTarget battleRoundTarget){
        this.max = battleRoundTarget.max;
        this.min = battleRoundTarget.min;
        this.targetSection = battleRoundTarget.targetSection;
    }


}
