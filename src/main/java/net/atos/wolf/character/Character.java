package net.atos.wolf.character;

import java.util.ArrayList;

public class Character {


    Boolean hasBackpack = false;

    private int food;

    private int gold;

    private Weapon weaponOne = Weapon.AXE;
    private Weapon weaponTwo = Weapon.DAGGER;

    private ArrayList backpack = new ArrayList<Item>();

    private ArrayList specialItems = new ArrayList<SpecialItem>();

    private ArrayList skills = new ArrayList<KaiSkill>();

    public static final int BASE_STRENGTH = 10;

    public static final int BASE_ENDURANCE = 20;

    private int combantStrenght;

    private int endurance;

    private int section;

    /**
     * add a skill from the catogery to the character
     * @param kaiSkill
     * @return
     */
    public KaiSkill addSkill(KaiSkill kaiSkill) {

        return kaiSkill;
    }

    /**
     * get the current weapon on the first weapon slot
     * @return
     */
    public Weapon getWeaponOne() {
        return weaponOne;
    }

    /**
     * get the current weapon on the second weapon slot
     * @return
     */
    public Weapon getWeaponTwo() {
        return weaponTwo;
    }

    /**
     * remove the item from the backpack
     */
    public void removeBackpack() {


    }

    /**
     * add a item to the backpack
     * @param item
     */
    public void addItemToBackpack(Item item) {

    }

    /**
     * remove a item from the backpack
     * @param item
     */
    public void removeItemFromBackpack(Item item) {

    }

    /**
     * checks if the item that requirred is given
     * @param item
     */
    public void hasItem(Item item){


    }

    /**
     * add a special / rare item to the list
     * @param specialItem
     */
    public void addSpecialItem(SpecialItem specialItem){



    }

    /**
     * remove a special / rare item from the list
     * @param specialItem
     */
    public void removeSpecialItem(SpecialItem specialItem){

    }

    /**
     * checks if the rare special item that needed is given
     * @param item
     */
    public void hasSpecialItem(Item item){

    }

    /**
     * add gold to the character
     * @param gold
     * @return
     */
    public int addGold (int gold){
        return gold;
    }

    /**
     * remove gold from the character
     * @param gold
     * @return
     */
    public int removeGold(int gold){

        return gold;
    }

    /**
     * get the current amount of gold from the character
     * @return
     */
    public int getGold() {
        return gold;
    }

    /**
     * add food to the character
     * @param food
     * @return
     */
    public int addFood(int food){
        return food;
    }

    /**
     * remove food from the character
     * @param food
     * @return
     */
    public int removeFood(int food){
        return food;
    }

    /**
     * get the current amount of food from the character
     * @return
     */
    public int getFood() {
        return food;
    }

    /**
     * get the current combat strenght from the character
     * @return
     */
    public int getCombantStrenght() {
        return combantStrenght;
    }

    /**
     * set the combat strenght
     * @param combantStrenght
     */
    public void setCombantStrenght(int combantStrenght) {
        this.combantStrenght = combantStrenght;
    }

    /**
     * get the current endurance from the character
     * @return
     */
    public int getEndurance() {
        return endurance;
    }

    /**
     * set the endurance for the character
     * @param endurance
     */
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    /**
     * give the current section of the book/story
     * @return
     */
    public int getSection() {
        return section;
    }

    /**
     * set/go to the next section from the book/story
     * @param section
     */
    public void setSection(int section) {
        this.section = section;
    }
}
