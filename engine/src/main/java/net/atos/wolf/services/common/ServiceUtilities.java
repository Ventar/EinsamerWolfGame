package net.atos.wolf.services.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.action.IActionHandler;
import net.atos.wolf.services.section.SectionService;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Utility class with functionality used by multiple services.
 */
public final class ServiceUtilities {

    /**
     * Jackson mapper to deserialize JSON data into Java objects.
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * Uses the {@link Reflections} utilities to build a list of action handlers.
     */
    public static Map<ActionType, IActionHandler> buildActionHandler(SectionService sectionService) {

        Reflections reflections = new Reflections("net.atos.wolf");
        Map<ActionType, IActionHandler> handlerMap = new HashMap<>();
        Set<Class<?>> handlerList = reflections.getTypesAnnotatedWith(ActionHandler.class);

        for (Class<?> aClass : handlerList) {
            // create a new instance of the handler and add it to the map of handlers
            IActionHandler handler;

            try {
                handler = (IActionHandler) aClass.getConstructor().newInstance();
                ((AbstractActionHandler) handler).setSectionService(sectionService);
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

}
