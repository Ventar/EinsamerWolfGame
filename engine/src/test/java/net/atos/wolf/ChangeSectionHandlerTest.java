package net.atos.wolf;

import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionResult;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.*;
import net.atos.wolf.services.character.Character;
import org.junit.jupiter.api.*;

import java.util.List;

public class ChangeSectionHandlerTest {

    private Character character;

    private GameEngine engine = new GameEngine();

    @BeforeEach
    public void beforeAll() {
        character = new Character();
        character.setWeaponOne(Weapon.AXE);
        character.addSkill(KaiSkill.HEAL);
        character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        character.addSkill(KaiSkill.ARMORY_SWORD);
        character.addSkill(KaiSkill.THOUGHT_RAY);
        character.addSkill(KaiSkill.ARMORY_MACE);
        character.addItemToBackpack(Item.CINDER);
        character.addItemToBackpack(Item.FIREBOTTLE);
        character.addItemToBackpack(Item.GOLDENKEY);
        character.addItemToBackpack(Item.FEARWHEEL);
        character.addItemToBackpack(Item.GREEN_EMERALD);

        character.addSpecialItem(SpecialItem.CHAIN_MAIL);
        character.addSpecialItem(SpecialItem.BELT);
        character.addSpecialItem(SpecialItem.MAP);
        character.addSpecialItem(SpecialItem.HELMET);

        character.endurance().add(26);
        character.endurance().maxValue(26);
        character.battleStrength().add(14);
        character.gold().add(20);
        character.food().add(1);
    }

    @Test
    @Order(0)
    public void changeSectionWithoutPrerequisites() {
        character.section(16);

        List<Action> actions = engine.getPossibleActions(character);

        Action a1 = new Action();
        a1.type(ActionType.CHANGE_SECTION);
        a1.text("Wenn du hinaufsteigen willst, um das Baumhaus zu untersuchen");
        a1.targetSection(470);

        Action a2 = new Action();
        a2.type(ActionType.CHANGE_SECTION);
        a2.text("Willst du stattdessen deinen Weg durch den Wald fortsetzen");
        a2.targetSection(317);


        Assertions.assertTrue(actions.contains(a1));
        Assertions.assertTrue(actions.contains(a2));
        Assertions.assertEquals(2, actions.size());


        ActionResult result = engine.executeAction(character, null, a1, actions);

        Assertions.assertEquals(ActionResult.ActionResultType.SECTION_FINISHED, result.type());
        Assertions.assertEquals(470, character.section());

    }

    @Test
    @Order(1)
    public void changeSectionWithRandomSection() {
        character.section(22);

        List<Action> actions = engine.getPossibleActions(character);
        Assertions.assertEquals(1, actions.size());


        ActionResult result = engine.executeAction(character, null, actions.get(0), actions);

        Assertions.assertEquals(ActionResult.ActionResultType.SECTION_FINISHED, result.type());
        Assertions.assertTrue(character.section() == 479 || character.section() == 311);

    }
    @Test
    @Order(2)
    public void changeSectionWithWeapon() {

        character.section(15);
        Action a1 = new Action();
        a1.type(ActionType.CHANGE_SECTION);
        a1.text("Glücklicherweise hast du eine Axt");
        a1.targetSection(166);
        a1.weapon(Weapon.AXE);

        Action a2 = new Action();
        a2.type(ActionType.CHANGE_SECTION);
        a2.text("Leider hast du keine Axt");
        a2.targetSection(477);
        a2.noOtherOption(true);

        List<Action> actions = engine.getPossibleActions(character);

        Assertions.assertTrue(actions.contains(a1));
        Assertions.assertFalse(actions.contains(a2));
        Assertions.assertEquals(1, actions.size());

        character.setWeaponOne(null);

         actions = engine.getPossibleActions(character);

        Assertions.assertFalse(actions.contains(a1));
        Assertions.assertTrue(actions.contains(a2));
        Assertions.assertEquals(1, actions.size());



    }
    @Test
    @Order(3)
    public void changeSectionWithSkill() {
        character.section(118);

        List<Action> actions = engine.getPossibleActions(character);

        Action a1 = new Action();
        a1.type(ActionType.CHANGE_SECTION);
        a1.text("Weiter");
        a1.targetSection(63);
        a1.skill(KaiSkill.SIXTH_SENSE);

        Action a2 = new Action();
        a2.type(ActionType.CHANGE_SECTION);
        a2.text("Willst du der Aufforderung der Soldaten nachkommen");
        a2.targetSection(303);

        Action a3 = new Action();
        a3.type(ActionType.CHANGE_SECTION);
        a3.text("Beschließt du deine Waffe zu ziehen, für den Fall, dass sie dich angreifen");
        a3.targetSection(263);

        Action a4 = new Action();
        a4.type(ActionType.CHANGE_SECTION);
        a4.text("Wenn du verlangst zu erfahren, was sie von dir wollen");
        a4.targetSection(348);



        Assertions.assertFalse(actions.contains(a1));
        Assertions.assertTrue(actions.contains(a2));
        Assertions.assertTrue(actions.contains(a3));
        Assertions.assertTrue(actions.contains(a4));

        Assertions.assertEquals(3, actions.size());

        character.addSkill(KaiSkill.SIXTH_SENSE);

        actions = engine.getPossibleActions(character);

        Assertions.assertTrue(actions.contains(a1));
        Assertions.assertTrue(actions.contains(a2));
        Assertions.assertTrue(actions.contains(a3));
        Assertions.assertTrue(actions.contains(a4));

        Assertions.assertEquals(4, actions.size());


    }
}
