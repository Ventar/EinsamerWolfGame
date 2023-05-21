package net.atos.wolf.services;


import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.battle.BattleService;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.CharacterService;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.character.Weapon;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.section.Section;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.ui.AnswerOption;
import net.atos.wolf.services.ui.UIService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class GameEngine {

    private List<ActionHandler> actionHandler = new ArrayList<>(Arrays.asList(
            new ChangeSectionHandler(),
            new ChangeSectionIfSkillHandler(),
            new ChangeSectionIfWeaponHandler(),
            new ChangeSectionIfSpecialItemHandler(),
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
        log.debug("Created character ::= [{}]", character);

        int sectionToRender = 333;

        while (true) {

            // load the section which is the current active one to offer the possible actions to the character with the UI service
            Section section = sectionService.getSection(sectionToRender);

            System.out.println(section);

            // Generate the first set of answer options directly from the actions tha are stored in the section
            List<AnswerOption> answerOptions = generateAnswerOptions(section.getActions());

            ActionResult actionResult = null;

            do {
                AnswerOption answerOption = ui.render(section.getText(), String.valueOf(section.getSectionNumber()), answerOptions);

                actionResult = getActionHandler(answerOption.action()).handleAction(character, answerOption.action(), answerOptions);

                switch (actionResult.getType()) {
                    case CHANGE_SECTION -> sectionToRender = actionResult.getTargetSection();
                    case CHARACTER_DIED -> throw new RuntimeException("Damned we died :(");
                    case REPRESENT_ACTIONS -> answerOptions = generateAnswerOptions(actionResult.getActions());
                }

            } while (actionResult.getType() == ActionResult.ActionResultType.REPRESENT_ACTIONS);


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
