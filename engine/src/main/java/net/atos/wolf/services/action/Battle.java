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

   // @Override
   // public String toString() {
   //     return "Battle{" +
   //             "enemy=" + enemy +
   //             ", targetSectionBattleRound=" + targetSectionBattleRound +
   //             '}';
    }

