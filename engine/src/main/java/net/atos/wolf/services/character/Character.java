package net.atos.wolf.services.character;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.translation.TranslationService;


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
    private ArrayList backpack = new ArrayList<Item>();
    private ArrayList baseBackpack = new ArrayList<BaseItems>();
    /**
     * Creates a new list for the SpecialItems
     */
    private ArrayList specialItemsList = new ArrayList<SpecialItem>();
    /**
     * Creates a new list for the skills
     */
    private ArrayList skills = new ArrayList<KaiSkill>();
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



    /**
     * Add a skill to the character
     *
     * @param kaiSkill
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
     * @param weapon
     */
    public void setWeaponOne(Weapon weapon) {
        this.weaponOne = weapon;
    }

    /**
     * Set the weapon for the hand two
     * @param weapon
     */
    public void setWeaponTwo(Weapon weapon) {
        this.weaponTwo = weapon;
    }


    /**
     * add an item to the backpack
     *
     * @param item
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
    public boolean hasItem(Item item) {
        return backpack.contains(item);

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


    /**
     * Get an attribute by his name
     * @param name
     * @return
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


    /**
     * Returns skill from character skill list, if there is no skill return minus
     *
     * @param pos
     * @return
     */
    private String getEntryAtPosition(TranslationService translationService, List list, int pos) {
        if (pos < list.size()) {
            return translationService.translate(list.get(pos).toString());
        } else {
            return "-";
        }
    }

    /**
     * Creates an a String the shows the current values of the character
     * @param translationService
     * @return
     */
    public String createCharacterString(TranslationService translationService) {


        StringBuilder buf = new StringBuilder();

        buf.append(String.format("Kampfstärke               : %15s    Kai Skill: %-30s    Gegenstände:    %s            Basisausrüstung:      %-30s\n", battleStrength.get(), getEntryAtPosition(translationService,
                skills, 0), getEntryAtPosition(translationService, backpack, 0), getEntryAtPosition(translationService, baseBackpack, 0)));
        buf.append(String.format("Ausdauer                  : %15s               %-30s                    %-30s     %s\n", endurance.get() + "/" + endurance().maxValue(),
                getEntryAtPosition(translationService, skills, 1), getEntryAtPosition(translationService, backpack, 1), getEntryAtPosition(translationService, baseBackpack, 1)));
        buf.append(String.format("%10s                                                %-30s                    %s\n", "", getEntryAtPosition(translationService, skills, 2), getEntryAtPosition(translationService, backpack, 2)));
        if (weaponOne == null) {
            buf.append(String.format("Waffe 1                   : %15s               %-30s                    %s\n", "-", getEntryAtPosition(translationService, skills, 3),
                    getEntryAtPosition(translationService, backpack, 3)));
        } else {
            buf.append(String.format("Waffe 1                   : %15s               %-30s                    %s\n", translationService.translate(weaponOne.toString()),
                    getEntryAtPosition(translationService, skills, 3), getEntryAtPosition(translationService, backpack, 3)));

        }
        if (weaponTwo == null) {
            buf.append(String.format("Waffe 2                   : %15s               %-30s                    %s\n", "-", getEntryAtPosition(translationService, skills, 4),
                    getEntryAtPosition(translationService, backpack, 4)));
        } else {
            buf.append(String.format("Waffe 2                   : %15s               %-30s                    %s\n", translationService.translate(weaponTwo.toString()),
                    getEntryAtPosition(translationService, skills, 4), getEntryAtPosition(translationService, backpack, 4)));

        }
        buf.append(String.format("Nahrung                   : %15s               %-30s                    %s\n", food.get(), "", getEntryAtPosition(translationService, backpack,
                5)));

        buf.append(String.format("Gold                      : %15s               %-30s                    %s\n", gold.get(), "", getEntryAtPosition(translationService, backpack,
                6)));
        buf.append(String.format("%10s                                           %-30s                         %s\n", "", "", getEntryAtPosition(translationService, backpack, 7)));
        buf.append("Spezialgegenstände        : ");
        for (int i = 0; i < specialItemsList.size(); i++) {
            buf.append(translationService.translate(specialItemsList.get(i).toString()));
            if (i < specialItemsList.size() - 1) {
                buf.append(", ");
            }
        }


        return buf.toString();

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



