package net.atos.wolf.services.character;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;

@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
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
    private boolean hasBaseBackpack = true;
    
    /**
     * Checks which weapons have the character
     */
    @Getter
    private Weapon weaponOne = null;
    
    @Getter
    private Weapon weaponTwo = null;
    /**
     * Creates a new list for the backpack
     */
    private List<Item> backpack = new ArrayList<>();
    private List<BaseItems> baseBackpack = new ArrayList<>();
    /**
     * Creates a new list for the SpecialItems
     */
    private List<SpecialItem> specialItemsList = new ArrayList<>();
    /**
     * Creates a new list for the skills
     */
    @Getter
    private List<KaiSkill> skills = new ArrayList<>();
    /**
     * Gives the character the attribute gold with a value where we start with and and the maximum of gold we can have
     */
    @Getter
    private Attribute gold = new Attribute("GOLD", 50, 0);
    /**
     * Gives the character the attribute food with a value where we start with and and the maximum of food we can have
     */
    @Getter
    private Attribute food = new Attribute("FOOD", 5, 0);
    
    @Getter
    private Attribute battleStrength = new Attribute("BATTLE_STRENGTH", 19, 10);
    
    @Getter
    private Attribute endurance = new Attribute("ENDURANCE", 29, 20);
    
    
    public Character() {
    
    }
    
    /**
     * Add a skill to the character
     */
    public void addSkill(KaiSkill kaiSkill) {
        skills.add(kaiSkill);
    }
    
    
    /**
     * remove the item from the backpack
     */
    public void removeBackpack() {
        backpack.clear();
        hasBackpack = false;
    }
    
    /**
     * Add a backpack for the character
     */
    public void addBackpack() {
        hasBackpack = true;
    }
    
    public void addBaseBackpack() {
        hasBaseBackpack = true;
    }
    
    /**
     * Set the Weapon for hand one
     */
    public void setWeaponOne(Weapon weapon) {
        this.weaponOne = weapon;
    }
    
    /**
     * Set the weapon for the hand two
     */
    public void setWeaponTwo(Weapon weapon) {
        this.weaponTwo = weapon;
    }
    
    
    /**
     * add an item to the backpack
     */
    public void addItemToBackpack(Item item) {
        if (hasBackpack == true) {
            backpack.add(item);
        } else {
            LOG.debug("Character has no backpack, cannot add item.");
        }
    }
    
    public void addBaseItemToBackpack(BaseItems baseItems) {
        if (hasBaseBackpack == true) {
            baseBackpack.add(baseItems);
        }
    }
    
    /**
     * Remove an item from the backpack
     */
    public void removeItemFromBackpack(Item item) {
        if (hasBackpack == true && backpack.contains(item)) {
            backpack.remove(item);
        }
    }
    
    
    /**
     * Checks if the item that requirred is given
     */
    public boolean hasItem(Item item) {
        return backpack.contains(item);
        
    }
    
    /**
     * add a special / rare item to the list
     */
    public void addSpecialItem(SpecialItem specialItem) {
        specialItemsList.add(specialItem);
    }
    
    
    /**
     * remove a special / rare item from the list
     */
    public void removeSpecialItem(SpecialItem specialItem) {
        if (specialItemsList.contains(specialItem)) {
            specialItemsList.remove(specialItem);
        }
    }
    
    /**
     * checks if the rare special item that needed is given
     */
    public boolean hasSpecialItem(SpecialItem item) {
        return specialItemsList.contains(item);
    }
    
    public boolean hasSkill(KaiSkill skill) {
        return skills.contains(skill);
    }
    
    
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
    
    /**
     * Return the number of skills of the current character
     *
     * @return the number of skills
     */
    public int getNumberOfSkills() {
        return skills.size();
    }
    
    @Override
    public String toString() {
        return "Character{" +
                   "hasBackpack=" + hasBackpack +
                   ", weaponOne=" + weaponOne +
                   ", weaponTwo=" + weaponTwo +
                   ", backpack=" + backpack +
                   ", specialItemsList=" + specialItemsList +
                   ", skills=" + skills +
                   ", gold=" + gold +
                   ", food=" + food +
                   ", combatStrength=" + battleStrength +
                   ", endurance=" + endurance +
                   '}';
        
        
    }
}



