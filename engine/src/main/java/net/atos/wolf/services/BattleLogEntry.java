package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.battle.BattleService;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BattleLogEntry {

    private String text;

    private int battleQuotient;

    private int battleStrength;

    private boolean hasSkill;

    private boolean applyWeaponSkill;

    private int endurance;


    public BattleLogEntry() {
   }

    public BattleLogEntry(String text) {
        this.text = text;
    }
}
