package net.atos.wolf.services.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.atos.wolf.services.character.Attribute;

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

//    @Override
//    public String toString() {
//        return "Enemy{" +
//                "name='" + name + '\'' +
//                ", battleStrength=" + battleStrength +
//                ", endurance=" + endurance +
//                ", thoughRayResistance=" + thoughRayResistance +
//                '}';
//    }
}
