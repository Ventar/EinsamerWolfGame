package net.atos.wolf.character;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Character {

    public static final int BASE_STRENGTH = 10;

    public static final int BASE_ENDURANCE = 20;

    private boolean hasBackpack = true;

    private Weapon weaponOne = null;
    private Weapon weaponTwo = null;

    private ArrayList backpack = new ArrayList<Item>();

    private ArrayList specialItemsList = new ArrayList<SpecialItem>();

    private ArrayList skills = new ArrayList<KaiSkill>();

    private Attribute gold = new Attribute("Gold", 0);
    private Attribute food = new Attribute("Food", 0);
    private Attribute combatStrength = new Attribute("CombatStrength", 0);
    private Attribute endurance = new Attribute("Endurance", 0);

    private int section = 0;

    /**
     * add a skill from the catogery to the character
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
    public void hasSpecialItem(Item item) {

    }


    public static void main(String[] args) {

        Character juli = new Character();
        juli.getGold().add(5);

        System.out.println(juli.getGold());


        Character noel = new Character();
        noel.getGold().add(10);

        System.out.println(noel.getGold());


        noel.getGold().remove(3);

        System.out.println(noel.getGold());

        juli.addItemToBackpack(Item.CINDER);
        juli.addItemToBackpack(Item.FIREBOTTLE);


        System.out.println(juli.backpack);


        juli.addSpecialItem(SpecialItem.CHAIN_MAIL);
        System.out.println(juli.specialItemsList);


        juli.addSkill(KaiSkill.HEALING);
        juli.addSkill(KaiSkill.HUNTING);
        juli.addSkill(KaiSkill.MENTAL_DEFENCE);

        System.out.println(juli.skills);


    }

}
