package net.atos.wolf.framework;

@Service
public class DemoService1 {

    @Inject
    DemoService2 demoService2;


    @Override
    public String toString() {
        return "DemoService1{" +
                "demoService2=" + demoService2 +
                '}';
    }
}
