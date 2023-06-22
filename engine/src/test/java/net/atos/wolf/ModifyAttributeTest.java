package net.atos.wolf;

import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.Item;
import net.atos.wolf.services.character.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModifyAttributeTest {

    private Character character;

    private GameEngine engine = new GameEngine();

    @BeforeEach
    public void beforAll() {
        character = new Character();
        character.endurance().add(26);
        character.endurance().maxValue(26);
        character.battleStrength().add(14);
        character.gold().add(20);
        character.food().add(1);
    }

    @Test
    @Order(1)
    public void modifyAttributeEndurance() {
        character.section(74);
        Action a1 = new Action();
        a1.type(ActionType.MODIFY_ATTRIBUTE);
        a1.text("TAKE_DAMAGE");
        a1.mandatory(true);
        a1.skill(null);
        a1.attribute("ENDURANCE");
        a1.value("1");

        Action a2 = new Action();
        a2.type(ActionType.CHANGE_SECTION);
        a2.text("Geh durch den Gang zum Dormitorium");
        a2.targetSection(333);

        Action a3 = new Action();
        a3.type(ActionType.CHANGE_SECTION);
        a3.text("Geh durch die Tür zum Trainingsgelände");
        a3.targetSection(374);

        List<Action> actions = engine.getPossibleActions(character);

        Assertions.assertTrue(actions.contains(a1));
        Assertions.assertTrue(actions.contains(a2));
        Assertions.assertTrue(actions.contains(a3));
        Assertions.assertEquals(3, actions.size());


    }

    @Test
    @Order(2)
    public void modifyAttributeFood() {
        character.section(119);
        Action a1 = new Action();
        a1.type(ActionType.MODIFY_ATTRIBUTE);
        a1.text("TAKE FOOD");
        a1.mandatory(true);
        a1.attribute("FOOD");
        a1.value("2");

        Action a2 = new Action();
        a2.type(ActionType.TAKE_WEAPON);
        a2.text("Nimm den Kolben");
        a2.weapon(Weapon.MACE);

        Action a3 = new Action();
        a3.type(ActionType.TAKE_WEAPON);
        a3.text("Geh durch die Tür zum Trainingsgelände");
        a3.weapon(Weapon.SPEAR);

        Action a4 = new Action();
        a4.type(ActionType.TAKE_WEAPON);
        a4.text("Geh durch den Gang zum Dormitorium");
        a4.weapon(Weapon.AXE);

        Action a5 = new Action();
        a5.type(ActionType.TAKE_WEAPON);
        a5.text("Geh durch die Tür zum Trainingsgelände");
        a5.weapon(Weapon.DAGGER);

        Action a6 = new Action();
        a6.type(ActionType.CHANGE_SECTION);
        a6.text("Geh durch den Gang zum Dormitorium");
        a6.targetSection(242);

        Action a7 = new Action();
        a7.type(ActionType.TAKE_BACKPACK);
        a7.text("Nimm den Rucksack");

        List<Action> actions = engine.getPossibleActions(character);

        Assertions.assertTrue(actions.contains(a1));
        Assertions.assertFalse(actions.contains(a2));
        Assertions.assertFalse(actions.contains(a3));
        Assertions.assertFalse(actions.contains(a4));
        Assertions.assertFalse(actions.contains(a5));
        Assertions.assertFalse(actions.contains(a6));
        Assertions.assertTrue(actions.contains(a7));

        Assertions.assertEquals(7, actions.size());


    }

}