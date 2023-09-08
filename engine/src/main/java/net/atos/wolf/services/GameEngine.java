package net.atos.wolf.services;


import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.action.IActionHandler;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.CharacterService;
import net.atos.wolf.services.common.ServiceUtilities;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.translation.TranslationService;
import net.atos.wolf.services.ui.UIService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameEngine {


    /**
     * Service to manage the character
     */
    private CharacterService characterService = new CharacterService();

    /**
     * A map with all available action handlers that can be used by the engine. Has to be initialized by calling the {@link ServiceUtilities#buildActionHandler(SectionService)}
     * method which usually happens automatically during construction;
     */
    private Map<ActionType, IActionHandler> actionHandler;


    public GameEngine(SectionService sectionService) {
        actionHandler = ServiceUtilities.buildActionHandler(sectionService);
    }

    /**
     * Resolves the responsible action handler for the passed action and executes it
     *
     * @param session         the session to execute the action on
     * @param actionToExecute the action to execute
     *
     * @return the result of the action execution
     */
    public void executeAction(GameSession session, Action actionToExecute) {
        actionHandler.get(actionToExecute.type()).handleAction(session, actionToExecute);
    }

    /**
     * Executes the mandatory actions from the section that is currently loaded in the game session. The section in the game session is modified, i.e. all mandatory actions are
     * removed during the call to this method.
     *
     * @param session the session to execute the mandatory actions for
     *
     * @return the session
     */
    private GameSession executeAndFilterMandatoryActions(GameSession session) {
        ArrayList<Action> resultList = new ArrayList<>();
        for (Action action : session.section().actions()) {
            if (action.mandatory()) {
                executeAction(session, action);
                LOG.debug("[{}] - Execute mandatory action ::= [{}]", session.section(), action);
            } else {
                resultList.add(action);
            }
        }

        session.section().actions(resultList);
        return session;
    }

    /**
     * Calls the {@link IActionHandler#isExecutable(GameSession, Action)} method for every action in the passed list to check if the action can be executed by the character of the
     * engine in the current state
     *
     * @param actions the actions to filter
     *
     * @return the filtered actions
     */
    private List<Action> filterActions(GameSession session, List<Action> actions) {

        List<Action> answerOptions = new LinkedList<>();

        for (Action action : actions) {
            if (actionHandler.get(action.type()).isExecutable(session, action)) {
                answerOptions.add(action);
            }
        }

        return answerOptions;
    }

    /**
     * Resolves a list of actions that can be executed based on the passed session information
     *
     * @param session the session
     *
     * @return the possible actions to execute
     */
    public List<Action> resolveActions(GameSession session) {


        int currentSection = session.section().sectionNumber();

        LOG.debug("------------------------------------------------------------------------------");
        LOG.debug("[{}] handle section for character ::= [{}]", currentSection, session);

        List<Action> answerOptions = null;


        if (session.modifiedAnswerOptions() == null || session.modifiedAnswerOptions().isEmpty()) {
            // we use the actions from the section, i.e. that is the first time the section is handled and it was not modified yet
            LOG.debug("[{}] original actions ::= [{}]", currentSection, session);

            executeAndFilterMandatoryActions(session); // the mandatory actions will only be executed once

            answerOptions = filterActions(session, session.section().actions());
        } else {
            LOG.debug("[{}] modified actions ::= [{}]", currentSection, session);
            answerOptions = filterActions(session, session.modifiedAnswerOptions());

        }

        return answerOptions;

    }
    //
    //
    //  public static void main(String[] args) {
    //      LOG.trace("Start new instance of the game engine...");
    //      GameEngine engine = new GameEngine();
    //      ActionSelector actionSelector = new UIService();
    //      SectionService sectionService = new SectionService("/ew1.json");
    //
    //      Character character = new Character();
    //  //    character.setWeaponOne(Weapon.AXE);
    //     //  character.addSkill(KaiSkill.RAGE);
    //      //character.addSkill(KaiSkill.ANIMAL_UNDERSTANDING);
    //      //character.addSkill(KaiSkill.ARMORY_SWORD);
    //      //character.addSkill(KaiSkill.THOUGHT_RAY);
    //      //character.addSkill(KaiSkill.ARMORY_MACE);
    //      //character.addBaseItemToBackpack(BaseItems.MAP);
    // //     character.addItemToBackpack(Item.CINDER);
    //   //   character.addItemToBackpack(Item.FIREBOTTLE);
    //   //   character.addItemToBackpack(Item.GOLDENKEY);
    //   //   character.addItemToBackpack(Item.FEARWHEEL);
    //   //   character.addItemToBackpack(Item.GREEN_EMERALD);
    //   //   character.addSpecialItem(SpecialItem.CHAIN_MAIL);
    //   //   character.addSpecialItem(SpecialItem.BELT);
    //   //   character.addSpecialItem(SpecialItem.MAP);
    //   //   character.addSpecialItem(SpecialItem.HELMET);
    //      character.endurance().add(26);
    //      character.endurance().maxValue(26);
    //      character.battleStrength().add(14);
    //    //  character.gold().add(20);
    //    //  character.food().add(1);
    //
    //      GameSession session = new GameSession();
    //      session.character(character);
    //      session.section(sectionService.getSection(1000));
    //
    //
    //      LOG.debug("Created character ::= [{}]", character);
    //      LOG.trace("Start to handle sections...");
    //
    //
    //      while (true) {
    //
    //          Action actionToExecute = actionSelector.selectAction(session.getModifiedSectionText(), String.valueOf(session.section().sectionNumber()), engine.resolveActions(session));
    //          engine.executeAction(session, actionToExecute);
    //
    //      }
    //
    //  }

    }



