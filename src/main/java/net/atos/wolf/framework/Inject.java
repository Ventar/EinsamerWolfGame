package net.atos.wolf.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that the field has to be populated by a Java instance of the given type that has been annotated with the {@link Service} annotation.
 *
 * @author Michael Rodenbuecher
 * @since 2023-05-03
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
