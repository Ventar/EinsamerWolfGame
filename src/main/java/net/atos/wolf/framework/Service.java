package net.atos.wolf.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that the Java class has to be instantiated as a Singleton Service
 *
 * @author Michael Rodenbuecher
 * @since 2023-05-03
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
