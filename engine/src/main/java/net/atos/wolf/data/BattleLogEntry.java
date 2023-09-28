package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.data.BattleTable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BattleLogEntry {
    
    private String text;
    
    private int battleRound;
    
    private int battleQuotient;
    
    private BattleTable.BattleValue battleValue;
    
    private int battleStrength;
    
    private int dice;
    
    private boolean characterUsesThoughtRay;
    
    private boolean characterApplyWeaponSkill;
    
    private int characterEndurance;
    
    private boolean enemyHasThoughRayResistance;
    
    
    public BattleLogEntry() {
    }
    
    public BattleLogEntry(String text) {
        this.text = text;
    }
    
}
