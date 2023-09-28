package net.atos.wolf.service;


import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.service.gameengine.IActionHandler;
import net.atos.wolf.data.GameSession;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameService {
    
    /**
     * A map with all available action handlers that can be used by the engine. Has to be initialized by calling the
     * {@link ServiceUtilities#buildActionHandler(SectionService)} method which usually happens automatically during
     * construction;
     */
    private Map<ActionType, IActionHandler> actionHandler;
    
    
    public GameService(SectionService sectionService) {
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
     * Executes the mandatory actions from the section that is currently loaded in the game session. The section in the
     * game session is modified, i.e. all mandatory actions are removed during the call to this method.
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
     * Calls the {@link IActionHandler#isExecutable(GameSession, Action)} method for every action in the passed list to
     * check if the action can be executed by the character of the engine in the current state
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
    
}



