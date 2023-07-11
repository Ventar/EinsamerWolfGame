package net.atos.wolf.services.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    private Server server;
    private JettyServer jettyServer;

    private JettyServer() {
        server = new Server(8080);
        ServletHandler handler = new ServletHandler();

        server.setHandler(handler);
        handler.addServletWithMapping(BlockingServlet.class, "/*");
    }

    public void start() throws Exception {
        if (jettyServer == null) {
            jettyServer = new JettyServer();
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main (String[] args) throws Exception {
        JettyServer jettyServer = new JettyServer();
        jettyServer.start();
    }
}
