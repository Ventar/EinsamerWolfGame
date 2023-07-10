package net.atos.wolf.services.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServerMain {

    public static void main (String[] args) throws Exception {

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        JettyServer jettyServer = new JettyServer();

        server.setHandler(handler);
        handler.addServletWithMapping(BlockingServlet.class, "/*");
        server.start();
        server.join();
//        jettyServer.start();
//        Client.callServer();
    }
}
