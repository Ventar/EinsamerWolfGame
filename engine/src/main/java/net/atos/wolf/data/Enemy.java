package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Enemy {

    private String name;
    private int battleStrength;
    private int endurance;
    private boolean thoughRayResistance;

    public Enemy() {

    }

    /**
     * Gives the Enemy attributes
     *
     * @param enemy
     */
    public Enemy(Enemy enemy) {
        this.name = enemy.name;
        this.battleStrength = enemy.battleStrength;
        this.endurance = enemy.endurance;
        this.thoughRayResistance = enemy.thoughRayResistance;
    }

}
