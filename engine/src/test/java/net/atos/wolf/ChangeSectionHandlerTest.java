//package net.atos.wolf;
//
//import net.atos.wolf.data.GameSession;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//public class ChangeSectionHandlerTest {
//
//     private Character character;
//
//     private GameEngine engine = new GameEngine();
//
//     @BeforeEach
//     public void beforeAll() {
//         character = new Character();
//         character.setWeaponOne(Weapon.AXE);
//         character.addSkill(KaiSkill.HEAL);
//         character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
//         character.addSkill(KaiSkill.ARMORY_SWORD);
//         character.addSkill(KaiSkill.THOUGHT_RAY);
//         character.addSkill(KaiSkill.ARMORY_MACE);
//         //character.addItemToBackpack(Item.CINDER);
//         character.addItemToBackpack(Item.FIREBOTTLE);
//         character.addItemToBackpack(Item.GOLDENKEY);
//         character.addItemToBackpack(Item.FEARWHEEL);
//         character.addItemToBackpack(Item.GREEN_EMERALD);
//
//         character.addSpecialItem(SpecialItem.CHAIN_MAIL);
//         character.addSpecialItem(SpecialItem.BELT);
//         character.addSpecialItem(SpecialItem.MAP);
//         character.addSpecialItem(SpecialItem.HELMET);
//
//         character.endurance().add(26);
//         character.endurance().maxValue(26);
//         character.battleStrength().add(14);
//         character.gold().add(20);
//         character.food().add(1);
//     }
//
//     @Test
//     @Order(0)
//     public void changeSectionWithoutPrerequisites(GameSession session) {
//         character.section(16);
//
//         List<Action> actions = engine.getPossibleActions(session);
//
//         Action a1 = new Action();
//         a1.type(ActionType.CHANGE_SECTION);
//         a1.text("Wenn du hinaufsteigen willst, um das Baumhaus zu untersuchen");
//         a1.targetSection(470);
//
//         Action a2 = new Action();
//         a2.type(ActionType.CHANGE_SECTION);
//         a2.text("Willst du stattdessen deinen Weg durch den Wald fortsetzen");
//         a2.targetSection(317);
//
//
//         Assertions.assertTrue(actions.contains(a1));
//         Assertions.assertTrue(actions.contains(a2));
//         Assertions.assertEquals(2, actions.size());
//
//
//         ActionResultType result = engine.executeAction(session, null, actions);
//
//         Assertions.assertEquals(ActionResultType.ActionResultType.SECTION_FINISHED, result.type());
//         Assertions.assertEquals(470, character.section());
//
//     }
//
//     @Test
//     @Order(1)
//     public void changeSectionWithRandomSection(GameSession session) {
//         character.section(22);
//
//         List<Action> actions = engine.getPossibleActions(session);
//         Assertions.assertEquals(1, actions.size());
//
//
//         ActionResultType result = engine.executeAction(session, null, actions);
//
//         Assertions.assertEquals(ActionResultType.ActionResultType.SECTION_FINISHED, result.type());
//         Assertions.assertTrue(character.section() == 479 || character.section() == 311);
//
//     }
//     @Test
//     @Order(2)
//     public void changeSectionWithWeapon(GameSession session) {
//
//         character.section(15);
//         Action a1 = new Action();
//         a1.type(ActionType.CHANGE_SECTION);
//         a1.text("Glücklicherweise hast du eine Axt");
//         a1.targetSection(166);
//         a1.weapon(Weapon.AXE);
//
//         Action a2 = new Action();
//         a2.type(ActionType.CHANGE_SECTION);
//         a2.text("Leider hast du keine Axt");
//         a2.targetSection(477);
//         a2.noOtherOption(true);
//
//         List<Action> actions = engine.getPossibleActions(session);
//
//         Assertions.assertTrue(actions.contains(a1));
//         Assertions.assertFalse(actions.contains(a2));
//         Assertions.assertEquals(1, actions.size());
//
//         character.setWeaponOne(null);
//
//          actions = engine.getPossibleActions(session);
//
//         Assertions.assertFalse(actions.contains(a1));
//         Assertions.assertTrue(actions.contains(a2));
//         Assertions.assertEquals(1, actions.size());
//
//
//
//     }
//     @Test
//     @Order(3)
//     public void changeSectionWithSkill(GameSession session) {
//         character.section(118);
//
//         List<Action> actions = engine.getPossibleActions(session);
//
//         Action a1 = new Action();
//         a1.type(ActionType.CHANGE_SECTION);
//         a1.text("Weiter");
//         a1.targetSection(63);
//         a1.skill(KaiSkill.SIXTH_SENSE);
//
//         Action a2 = new Action();
//         a2.type(ActionType.CHANGE_SECTION);
//         a2.text("Willst du der Aufforderung der Soldaten nachkommen");
//         a2.targetSection(303);
//
//         Action a3 = new Action();
//         a3.type(ActionType.CHANGE_SECTION);
//         a3.text("Beschließt du deine Waffe zu ziehen, für den Fall, dass sie dich angreifen");
//         a3.targetSection(263);
//
//         Action a4 = new Action();
//         a4.type(ActionType.CHANGE_SECTION);
//         a4.text("Wenn du verlangst zu erfahren, was sie von dir wollen");
//         a4.targetSection(348);
//
//
//
//         Assertions.assertFalse(actions.contains(a1));
//         Assertions.assertTrue(actions.contains(a2));
//         Assertions.assertTrue(actions.contains(a3));
//         Assertions.assertTrue(actions.contains(a4));
//
//         Assertions.assertEquals(3, actions.size());
//
//         character.addSkill(KaiSkill.SIXTH_SENSE);
//
//         actions = engine.getPossibleActions(session);
//
//         Assertions.assertTrue(actions.contains(a1));
//         Assertions.assertTrue(actions.contains(a2));
//         Assertions.assertTrue(actions.contains(a3));
//         Assertions.assertTrue(actions.contains(a4));
//
//         Assertions.assertEquals(4, actions.size());
//
//
//     }
//     @Test
//     @Order(4)
//     public void changeSectionWithItem(GameSession session) {
//
//         character.section(242);
//         Action a1 = new Action();
//         a1.type(ActionType.CHANGE_SECTION);
//         a1.text("Lege Feuer");
//         a1.targetSection(544);
//         a1.item(Item.CINDER);
//
//         Action a2 = new Action();
//         a2.type(ActionType.CHANGE_SECTION);
//         a2.text("Geh Weiter");
//         a2.targetSection(359);
//         a2.noOtherOption(true);
//
//         List<Action> actions = engine.getPossibleActions(session);
//
//         Assertions.assertFalse(actions.contains(a1));
//         Assertions.assertTrue(actions.contains(a2));
//         Assertions.assertEquals(1, actions.size());
//
//         character.addItemToBackpack(Item.CINDER);
//
//         actions = engine.getPossibleActions(session);
//
//         Assertions.assertTrue(actions.contains(a1));
//         Assertions.assertFalse(actions.contains(a2));
//         Assertions.assertEquals(1, actions.size());
//
//
//
//     }
//}
