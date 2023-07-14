package net.atos.wolf.services.server;

import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.SessionService;
import net.atos.wolf.services.section.SectionService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HTTPGameServer {
    private Server server;

    private SessionService sessionService = new SessionService();

    private GameEngine engine = new GameEngine();

    private SectionService sectionService = new SectionService("/ew1.json");

    public HTTPGameServer() {
        server = new Server(8080);
        ServletHandler handler = new ServletHandler();

        server.setHandler(handler);

        SessionServlet sessionServlet = new SessionServlet(sessionService, engine, sectionService);
        GameServlet gameServlet = new GameServlet(sessionService, engine, new SectionService("/ew1.json"));

        handler.addServletWithMapping(new ServletHolder(gameServlet), "/section/");
//        handler.addServletWithMapping(InfoServlet.class, "/");
        handler.addServletWithMapping(new ServletHolder(sessionServlet), "/session/");
    }

    public void start() throws Exception {
        GameSessionObserver gameSessionObserver = new GameSessionObserver(sessionService);
        gameSessionObserver.start();
        server.start();
        server.join();
    }

    public static void main(String[] args) throws Exception {
        HTTPGameServer jettyServer = new HTTPGameServer();
        jettyServer.start();

    }
}
