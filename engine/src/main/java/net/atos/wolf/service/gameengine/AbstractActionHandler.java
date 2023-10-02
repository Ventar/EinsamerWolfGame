package net.atos.wolf.service.gameengine;

import net.atos.wolf.data.Action;
import net.atos.wolf.data.Character;
import net.atos.wolf.data.Section;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.ServiceRegistry;

import java.util.List;

/**
 * Abstract base implementation for all action handler. Contains common logic to check if a handler
 * {@link #isExecutable(Character, Action, boolean)} for a certain character state and provides base implementation for
 * the default {@link #handleAction(Character, Action, List)} method which usually leads to a section change.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-19
 */
public abstract class AbstractActionHandler implements IActionHandler {
    
    private ServiceRegistry registry;
    
    /**
     * The action type that is handled by the implementation
     */
    private ActionType type;
    
    
    /**
     * Creates a new instance of the action handler and resolves the {@link ActionType} for which the handler is
     * responsible from the name of the extending class.
     *
     * @throws IllegalStateException if the class name cannot be resolved to an action type
     */
    public AbstractActionHandler() {
        
        ActionHandler ahAnnotation = getClass().getAnnotation(ActionHandler.class);
        
        if (ahAnnotation == null) {
            throw new IllegalStateException(
                "Cannot instantiate an action handler without proper @ActionHandler annotation");
        }
        
        this.type = ahAnnotation.value();
    }
    
    protected boolean checkExecutable(GameSession session, Action action) {
        return true;
    }
    
    public void setRegistry(ServiceRegistry registry) {
        this.registry = registry;
    }
    
    @Override
    public ActionType getType() {
        return type;
    }
    
    @Override
    public boolean isExecutable(GameSession session, Action action) {
        return action.type() == type && checkExecutable(session, action);
    }
    
    public void handleAction(GameSession session, Action action) {
        session.section(getSection(action.targetSection()));
    }
    
    protected Section getSection(int sectionID) {
        return registry.bookRepository().getSection(sectionID);
    }
    
    protected int generate() {
        return registry.dice().generate();
    }
    
    
}
