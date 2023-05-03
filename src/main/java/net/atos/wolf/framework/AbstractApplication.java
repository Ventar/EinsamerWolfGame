package net.atos.wolf.framework;


import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Abstract base class to create an application
 *
 * @author Michael Rodenbuecher
 * @since 2023-05-03
 */
@Slf4j
public abstract class AbstractApplication implements Runnable {

    /**
     * The current state of the application.
     */
    public enum ApplicationState {
        CREATED,
        INITIALIZATION_ERROR,
        INITIALIZED
    }

    /**
     * Map that contains all instantiated services.
     */
    private HashMap<Class, Object> serviceRegistry = new HashMap<>();

    /**
     * The current state of the application.
     */
    private ApplicationState state = ApplicationState.CREATED;

    private void performInjection() {
        Reflections reflections = new Reflections(getPackageToScan());
        Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(Service.class);

        // create one instance of every class that is annotated with the @Service annotation
        serviceClasses.stream().forEach(clazz -> {
            try {
                Object service = clazz.getConstructor().newInstance();
                serviceRegistry.put(clazz, service);
                log.debug("Created service instance of type ::= [{}]", clazz);
            } catch (Exception e) {
                log.warn("Cannot instantiate service:", e);
                state = ApplicationState.INITIALIZATION_ERROR;
            }
        });

        // Add the application itself to the service registry so that the dependency injection can be performed.
        serviceRegistry.put(getClass(), this);


        // perform the dependency injection of services into each other
        serviceRegistry.entrySet().forEach(entry -> {
            Object serviceToInjectTo = entry.getValue();
            Class<?> clazz = entry.getKey();

            log.debug("Perform dependency injection for class ::= [{}]", clazz.getName());

            Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Inject.class)).forEach(f ->
            {
                log.debug("Try to inject dependency of type ::= [{}] into field ::= [{}]", f.getType(), f.getName());

                Class<?> fieldType = f.getType();
                Object fieldServiceInstance = serviceRegistry.get(fieldType);

                if (fieldServiceInstance == null) {
                    log.warn("Cannot find service of type ::= [{}]", fieldType);
                    state = ApplicationState.INITIALIZATION_ERROR;
                }

                try {
                    f.setAccessible(true);
                    f.set(serviceToInjectTo, fieldServiceInstance);
                    log.debug("Injected service instance to field ::= [{}] for service of type ::= [{}]", f.getName(), clazz.getName());
                } catch (Exception e) {
                    log.warn("Cannot inject service:", e);
                    state = ApplicationState.INITIALIZATION_ERROR;
                }

            });

        });

        if (state != ApplicationState.INITIALIZATION_ERROR) {
            state = ApplicationState.INITIALIZED;
        }

    }

    /**
     * Returns the current application state
     *
     * @return the state
     */
    public ApplicationState getState() {
        return state;
    }

    /**
     * Returns the package to scan for {@link Service} and {@link Inject} annotations.
     *
     * @return the package to scan
     */
    public abstract String getPackageToScan();

    /**
     * Method executed when the dependency injection was done. Can be used to execute the initial business logic of the application based on the used services.
     */
    public abstract void afterStartup();

    /**
     * Runs the application
     */
    public void run() {
        performInjection();
        log.debug("Finished dependency injection...");
        afterStartup();
    }


}
