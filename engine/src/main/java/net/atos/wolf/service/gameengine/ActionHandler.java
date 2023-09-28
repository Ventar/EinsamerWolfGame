package net.atos.wolf.service.gameengine;

import net.atos.wolf.service.GameService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation to allow for automatic scanning of {@link IActionHandler} by the {@link GameService}
 *
 * @author Michael Rodenbuecher
 * @since 2023-05-21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActionHandler {

    /**
     * The action type for which the annotated handler ist responsible.
     */
    ActionType value();

}
