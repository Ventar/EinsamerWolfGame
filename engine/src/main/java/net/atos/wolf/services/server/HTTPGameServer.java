package net.atos.wolf.services.server;

import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.server.servlet.*;
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
@Slf4j
public class HTTPGameServer {

    private Server server;

    private SectionService sectionService;

    private SessionService sessionService;

    private GameEngine engine = new GameEngine(sectionService);

    public HTTPGameServer() {
<<<<<<< HEAD
        LOG.debug("Create new HTTPGameServer with default session service...");
        this.sectionService = new SectionService();
        this.sessionService = new SessionService(sectionService);
        this.engine = new GameEngine(sectionService);
        init();
    }

    private void init() {
        SessionServlet sessionServlet = new SessionServlet(sessionService, engine, sectionService);
=======
        CreateSessionServlet sessionServlet = new CreateSessionServlet(sessionService, engine, sectionService);
        SaveSessionServlet saveSessionServlet = new SaveSessionServlet(sessionService,engine,sectionService);
>>>>>>> a949a96 (making UI page)
        GameServlet gameServlet = new GameServlet(sessionService, engine, sectionService);
        LoadSessionServlet loadSessionServlet = new LoadSessionServlet(sessionService,engine,sectionService);
        ListGameSessionsServlet listGameSessionsServlet = new ListGameSessionsServlet(sessionService,engine,sectionService);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(InfoServlet.class, "/");
        context.addServlet(new ServletHolder(gameServlet), "/section/");
        context.addServlet(new ServletHolder(sessionServlet), "/session/");
        context.addServlet(new ServletHolder(saveSessionServlet), "/session/save/");
        context.addServlet(new ServletHolder(loadSessionServlet), "/session/load/");
        context.addServlet(new ServletHolder(listGameSessionsServlet), "/session/list/");
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
