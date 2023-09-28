package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;

@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
@Getter
@Setter
public class Character {

    /**
     * The values which the character starts with
     */

    public static final int BASE_STRENGTH = 10;

    public static final int BASE_ENDURANCE = 20;

    /**
     * Checks if the character has a backpack or not
     */
    private boolean hasBackpack = true;

    /**
     * Checks which weapons have the character
     */
    private Weapon weaponOne = null;

    /**
     * Checks which weapons have the character
     */
    private Weapon weaponTwo = null;

    /**
     * Creates a new list for the backpack
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Creates a new list for the SpecialItems
     */
    private List<SpecialItem> specialItemsList = new ArrayList<>();
    /**
     * Creates a new list for the skills
     */
    private List<KaiSkill> skills = new ArrayList<>();
    /**
     * Gives the character the attribute gold with a value where we start with and and the maximum of gold we can have
     */
    private Attribute gold = new Attribute("GOLD", 50, 0);
    /**
     * Gives the character the attribute food with a value where we start with and and the maximum of food we can have
     */
    private Attribute food = new Attribute("FOOD", 5, 0);

    private Attribute battleStrength = new Attribute("BATTLE_STRENGTH", 19, 10);

    private Attribute endurance = new Attribute("ENDURANCE", 29, 20);

    /**
     * Get an attribute by his name
     */
    public Attribute getAttributeByName(String name) {

        if (name.equals("GOLD")) {
            return gold;
        } else if (name.equals("FOOD")) {
            return food;
        } else if (name.equals("BATTLE_STRENGTH")) {
            return battleStrength;
        } else if (name.equals("ENDURANCE")) {
            return endurance;
        }

        throw new IllegalStateException("There is no attribute with name ::= [" + name + "]");
    }

}



