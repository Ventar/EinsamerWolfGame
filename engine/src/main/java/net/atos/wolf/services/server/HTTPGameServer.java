package net.atos.wolf.services.server;

import jakarta.servlet.DispatcherType;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.server.servlet.GameServlet;
import net.atos.wolf.services.server.servlet.InfoServlet;
import net.atos.wolf.services.server.servlet.SessionServlet;
import net.atos.wolf.services.session.SessionService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.EnumSet;

/**
 * HTTP server to be used by the client.
 */
public class HTTPGameServer {

    private Server server;

    private SectionService sectionService = new SectionService();

    private SessionService sessionService = new SessionService(sectionService);

    private GameEngine engine = new GameEngine(sectionService);


    public HTTPGameServer() {
        SessionServlet sessionServlet = new SessionServlet(sessionService, engine, sectionService);
        GameServlet gameServlet = new GameServlet(sessionService, engine, sectionService);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(new ServletHolder(gameServlet), "/section/");
        context.addServlet(InfoServlet.class, "/");
        context.addServlet(new ServletHolder(sessionServlet), "/session/");

        // allow usage with the svelte app from a different process
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");

        server = new Server(8080);
        server.setHandler(context);
    }

    public void start() throws Exception {
        GameSessionObserver gameSessionObserver = new GameSessionObserver(sessionService);
        gameSessionObserver.start();
        server.start();
        server.join();
    }


}
