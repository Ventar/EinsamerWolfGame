package net.atos.wolf.services.character;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {

    public static final int BASE_STRENGTH = 10;

    public static final int BASE_ENDURANCE = 20;

    private boolean hasBackpack = true;

    @Getter
    private Weapon weaponOne = null;

    @Getter
    private Weapon weaponTwo = null;

    private ArrayList backpack = new ArrayList<Item>();

    private ArrayList specialItemsList = new ArrayList<SpecialItem>();

    private ArrayList skills = new ArrayList<KaiSkill>();

    @Getter
    private Attribute gold = new Attribute("Gold", 0);

    @Getter
    private Attribute food = new Attribute("Food", 0);

    @Getter
    private Attribute combatStrength = new Attribute("CombatStrength", 0);

    @Getter
    private Attribute endurance = new Attribute("Endurance", 0);

    private int section = 0;

    /**
     * Add a skill to the character
     *
     * @param kaiSkill
     *
     * @return
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

    public void setWeaponOne(Weapon weapon) {
        this.weaponOne = weapon;
    }

    public void setWeaponTwo(Weapon weapon) {
        this.weaponTwo = weapon;
    }


    /**
     * add a item to the backpack
     *
     * @param item
     */
    public void addItemToBackpack(Item item) {
        if (hasBackpack == true) {
            backpack.add(item);
        }
    }

    /**
     * Remove an item from the backpack
     *
     * @param item
     */
    public void removeItemFromBackpack(Item item) {
        if (hasBackpack == true && backpack.contains(item)) {
            backpack.remove(item);
        }
    }


    /**
     * Checks if the item that requirred is given
     *
     * @param item
     */
    public void hasItem(Item item) {


    }

    /**
     * add a special / rare item to the list
     *
     * @param specialItem
     */
    public void addSpecialItem(SpecialItem specialItem) {
        specialItemsList.add(specialItem);
    }

    /**
     * remove a special / rare item from the list
     *
     * @param specialItem
     */
    public void removeSpecialItem(SpecialItem specialItem) {
        if (specialItemsList.contains(specialItem)) {
            specialItemsList.remove(specialItem);
        }
    }

    /**
     * checks if the rare special item that needed is given
     *
     * @param item
     */
    public boolean hasSpecialItem(SpecialItem item) {
        return specialItemsList.contains(item);
    }

    public boolean hasSkill(KaiSkill skill) {
        return skills.contains(skill);
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
                       ", combatStrength=" + combatStrength +
                       ", endurance=" + endurance +
                       ", section=" + section +
                       '}';
    }


}
