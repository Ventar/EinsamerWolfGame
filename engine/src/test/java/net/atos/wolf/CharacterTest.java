package net.atos.wolf;

import net.atos.wolf.services.character.*;
import net.atos.wolf.services.character.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CharacterTest {


    private Character character;

    @Test
    @Order(1)
    public void testAddSkill() {
        Character character = new Character();

        Assertions.assertFalse(character.hasSkill(KaiSkill.ANIMAL_UNDERSTANDING));
        character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        Assertions.assertTrue(character.hasSkill(KaiSkill.ANIMAL_UNDERSTANDING));

        //Assertions.assertEquals(KaiSkill.ANIMAL_UNDERSTANDING,character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING))
        // Assertions.assertEquals(0, character.getGold(), "Character has wrong number of gold coins");
    }


    @Test
    @Order(2)
    public void testRemoveAndAddBackpack() {
        Character character = new Character();
        character.addItemToBackpack(Item.CINDER);
        Assertions.assertTrue(character.hasItem(Item.CINDER));
        character.removeBackpack();
        Assertions.assertFalse(character.hasItem(Item.CINDER));
        character.addBackpack();
        Assertions.assertFalse(character.hasItem(Item.CINDER));
    }

    @Test
    @Order(3)
    public void testSetWeaponOneAndTwo() {
        Character character = new Character();
        character.setWeaponOne(Weapon.AXE);
        Assertions.assertEquals(Weapon.AXE, character.weaponOne());

        character.setWeaponTwo(Weapon.SPEAR);
        Assertions.assertEquals(Weapon.SPEAR, character.weaponTwo());
    }

    @Test
    @Order(4)
    public void testAddAndRemoveItemToBackpack() {
        Character character = new Character();
        character.addItemToBackpack(Item.CINDER);
        character.addItemToBackpack(Item.GOLDENKEY);
        character.removeItemFromBackpack(Item.GOLDENKEY);
        character.removeItemFromBackpack(Item.CINDER);
        character.removeBackpack();


    }

    @Test
    @Order(5)
    public void testHasItem() {
        Character character = new Character();
        character.addItemToBackpack(Item.FIREBOTTLE);
        Assertions.assertTrue(character.hasItem(Item.FIREBOTTLE));
    }

    @Test
    @Order(6)
    public void testAddSpecialItem() {
        Character character = new Character();
        character.addSpecialItem(SpecialItem.MAP);
        Assertions.assertTrue(character.hasSpecialItem(SpecialItem.MAP));
    }

    @Test
    @Order(7)
    public void testRemoveSpecialItem() {
        Character character = new Character();
        character.addSpecialItem(SpecialItem.HELMET);
        character.removeSpecialItem(SpecialItem.HELMET);

    }


    @Test
    @Order(8)
    public void testHasSkill() {
        Character character = new Character();
        character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        Assertions.assertTrue(character.hasSkill(KaiSkill.ANIMAL_UNDERSTANDING));

    }

    @Test
    @Order(9)
    public void testGetAttributeByName() {
        Character character = new Character();
        Assertions.assertEquals("GOLD", character.getAttributeByName("GOLD").name());
        Assertions.assertEquals("FOOD", character.getAttributeByName("FOOD").name());
        Assertions.assertEquals("BATTLE_STRENGTH", character.getAttributeByName("BATTLE_STRENGTH").name());
        Assertions.assertEquals("ENDURANCE", character.getAttributeByName("ENDURANCE").name());


    }

    @Test
    @Order(10)
    public void testGetNumbersOfSkills() {
        Character character = new Character();

        character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        Assertions.assertEquals(1, character.getNumberOfSkills());

    }

    @Test
    @Order(11)
    public void testGetEntyAtPosition() {
        Character character = new Character();


    }

    @Test
    @Order(12)
    public void testAddAndRemoveBaseBackpack() {
        Character character = new Character();
        character.addBaseBackpack();
        character.addBaseItemToBackpack(BaseItems.MAP);

    }


}
