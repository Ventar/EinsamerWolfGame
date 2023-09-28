package net.atos.wolf;


import net.atos.wolf.service.ServiceRegistry;
import net.atos.wolf.server.HTTPGameServer;

public class Application {

    public static void main(String[] args) throws Exception {
        ServiceRegistry registry = new ServiceRegistry();
        
        HTTPGameServer jettyServer = new HTTPGameServer(registry);
        jettyServer.start();
    }
    
}
