package net.atos.wolf.framework;



import javassist.tools.reflect.Reflection;
import org.eclipse.jetty.util.Fields;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ReflectionExample {


    public static void main(String[] args) throws Exception {
        String className = "net.atos.wolf.framework.DemoClass";

        Class<?> clazz = ReflectionExample.class.getClassLoader().loadClass(className);
        Constructor constructor = clazz.getConstructor(String.class);

        Object concreteInstance = constructor.newInstance("Hello World");
        System.out.println(concreteInstance);

        Method method = clazz.getMethod("getText");
        System.out.println(method.invoke(concreteInstance));

        // -----------------------------------------------------------------------------

        DemoClass demoClass = new DemoClass("Hello World");
        System.out.println(demoClass);
        System.out.println(demoClass.getText());


        // -----------------------------------------------------------------------------


        HashMap<String, Object> services = new HashMap<>();

        Reflections reflection = new Reflections("net.atos.wolf.framework");

        for(Class cl : reflection.getTypesAnnotatedWith(Service.class)) {
            Constructor c = cl.getConstructor();
            Object i = c.newInstance();
            services.put(cl.getName(),i);
        }

        System.out.println(services);

        services.values().forEach(s -> {
            System.out.println("Check service " + s);
            for(Field f: s.getClass().getDeclaredFields()) {
                System.out.println("Check field " + f);
                if (f.getAnnotation(Inject.class) != null) {
                    System.out.println("Found inject at field " + f);
                    Object serviceInstance = services.get (f.getType().getName());
                    try {
                        f.setAccessible(true);
                        f.set(s, serviceInstance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        System.out.println(services);

    }

}
