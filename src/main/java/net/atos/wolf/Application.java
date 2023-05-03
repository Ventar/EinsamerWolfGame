package net.atos.wolf;

import net.atos.wolf.framework.AbstractApplication;
import net.atos.wolf.framework.Inject;
import net.atos.wolf.framework.Service;
import net.atos.wolf.services.DiceService;

public class Application extends AbstractApplication {

    @Inject
    private DiceService diceService;

    @Override
    public String getPackageToScan() {
        return "net.atos.wolf";
    }

    @Override
    public void afterStartup() {
        System.out.println("Application was started...");
        System.out.println(diceService.generate());
    }


    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

}
