package net.atos.wolf.services.character;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy {

    private String name;
    private Attribute combatStrength;
    private Attribute endurance;

}
