package net.atos.wolf.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import net.atos.wolf.server.GameServlet;
import net.atos.wolf.server.servlet.BaseServlet;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.service.gameengine.IActionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Service registry contains multiple services and allows easy access to all available services of the application.
 *
 * @author Michael Rodenbuecher
 * @since 2023-09-28
 */
public class ServiceRegistry {
    
    @Getter
    private ObjectMapper jsonMapper = new ObjectMapper();
    
    @Getter
    private DiceService dice = new DiceService();
    
    @Getter
    private BattleService battle = new BattleService();
    
    @Getter
    private TranslationService translation = new TranslationService(jsonMapper, "/translation.json");
    
    @Getter
    private SectionService sectionService = new SectionService(jsonMapper);
    
    @Getter
    private SessionService sessionService = new SessionService(this);
    
    @Getter
    private GameService gameService = new GameService(this);
    
    public ServiceRegistry() {
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
    
    
    /**
     * Uses the {@link Reflections} utilities to build a list of action handlers.
     */
    public static Map<ActionType, IActionHandler> buildActionHandler(ServiceRegistry registry) {
        
        Reflections reflections = new Reflections("net.atos.wolf");
        Map<ActionType, IActionHandler> handlerMap = new HashMap<>();
        Set<Class<?>> handlerList = reflections.getTypesAnnotatedWith(ActionHandler.class);
        
        for (Class<?> aClass : handlerList) {
            // create a new instance of the handler and add it to the map of handlers
            IActionHandler handler;
            
            try {
                handler = (IActionHandler) aClass.getConstructor().newInstance();
                ((AbstractActionHandler) handler).setRegistry(registry);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot create action handler for game engine: ", e);
            }
            
            if (handlerMap.containsKey(handler.getType())) {
                throw new IllegalStateException("An action handler for action type ::= [" + handler.getType() + "] already exists");
            }
            
            handlerMap.put(handler.getType(), handler);
        }
        
        // after the instantiation we perform some sanity checks to ensure that all handlers are available
        for (ActionType type : ActionType.values()) {
            if (!handlerMap.containsKey(type)) {
                throw new IllegalStateException("No action handler available for action type ::= [" + type + "]");
            }
        }
        
        return handlerMap;
        
    }
    
    
    public static void registerServlets(ServiceRegistry registry, ServletContextHandler context) {
        
        try {
            
            Reflections reflections = new Reflections("net.atos.wolf");
            
            for (Class<?> aClass : reflections.getTypesAnnotatedWith(GameServlet.class)) {
                BaseServlet servlet = (BaseServlet) aClass.getConstructor().newInstance();
                servlet.setRegistry(registry);
                GameServlet annotation = servlet.getClass().getAnnotation(GameServlet.class);
                context.addServlet(new ServletHolder(servlet), annotation.value());
            }
            
        } catch (Exception e) {
            throw new IllegalStateException("Cannot create action handler for game engine: ", e);
        }
        
    }
    
}
