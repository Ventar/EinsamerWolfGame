package net.atos.wolf.services;


import net.atos.wolf.character.Character;
import net.atos.wolf.character.KaiSkill;
import net.atos.wolf.character.Weapon;
import net.atos.wolf.services.actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameEngine {

    private List<ActionHandler> actionHandler = new ArrayList<>(Arrays.asList(
            new ChangeSectionHandler(),
            new ChangeSectionIfSkillHandler(),
            new ChangeSectionIfWeaponHandler(),
            new ChangeSectionIfSpecialItem(),
            new ChangeSectionIfNoOtherOptionHandler(),
            new TakeWeaponHandler()));

    private DiceService diceService = new DiceService();

    private UIService ui = new UIService();

    private BattleService battleService = new BattleService();

    private CharacterService characterService = new CharacterService();

    private SectionService sectionService = new SectionService("/ew1.json");


    private Character character;

    public void start() {
        Character character = new Character();
        character.setWeaponOne(Weapon.AXE);
        character.addSkill(KaiSkill.HEALING);
        character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
        character.getEndurance().add(26);
        character.getCombatStrength().add(14);
        character.getGold().add(20);
        character.getFood().add(5);

        this.character = character;


        int sectionToRender = 333;

        while (true) {

            Section section = sectionService.getSection(sectionToRender);

            List<AnswerOption> answerOptions = generateAnswerOptions(section.getActions());
            ActionResult actionResult = null;

            do {
                AnswerOption answerOption = ui.render(section.getText(), String.valueOf(section.getSectionNumber()), answerOptions);

                actionResult = getActionHandler(answerOption.action()).handleAction(character, answerOption.action(), answerOptions);

                if (actionResult.getTargetSection() > 0) {
                    sectionToRender = actionResult.getTargetSection();
                } else if (actionResult.isDead()) {
                    throw new RuntimeException("Damned we died :(");
                } else if (actionResult.hasActions()) {
                    answerOptions = generateAnswerOptions(actionResult.getActions());
                }

            } while (actionResult.hasActions());


        }

    }

    private List<AnswerOption> generateAnswerOptions(List<Action> actions) {

        List<AnswerOption> answerOptions = new LinkedList<>();

        int i = 1;

        for (Action action : actions) {

            ActionHandler aHandler = getActionHandler(action);

            if (aHandler.shouldRenderAnswerOption(character, action, answerOptions.isEmpty())) {
                answerOptions.add(new AnswerOption(action, action.getText(), i++));
            }

        }

        return answerOptions;
    }

    private ActionHandler getActionHandler(Action action) {

        for (ActionHandler handler : actionHandler) {
            if (handler.canHandle(action)) {
                return handler;
            }
        }

        throw new IllegalStateException("No handler for action ::= [" + action + "] found");

    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }

}
