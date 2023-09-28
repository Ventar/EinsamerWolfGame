package net.atos.wolf.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used by the {@link net.atos.wolf.service.ServiceRegistry} to create servlets.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GameServlet {
    
    /**
     * The path to which the servlet is mapped.
     */
    String value();
    
}
