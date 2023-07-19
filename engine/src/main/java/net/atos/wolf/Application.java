package net.atos.wolf;


import net.atos.wolf.services.server.HTTPGameServer;

public class Application {


    public static void main(String[] args) throws Exception {
        HTTPGameServer jettyServer = new HTTPGameServer();
        jettyServer.start();
    }
}
