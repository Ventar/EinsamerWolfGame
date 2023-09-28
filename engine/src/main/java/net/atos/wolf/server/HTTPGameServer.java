package net.atos.wolf.server;

import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.server.servlet.*;
import net.atos.wolf.service.GameSessionCleanupService;
import net.atos.wolf.service.ServiceRegistry;
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
    
    private ServiceRegistry registry;
    
    
    public HTTPGameServer(ServiceRegistry registry) {
        LOG.debug("Create new HTTPGameServer with default session service...");
        this.registry = registry;
        init();
    }
    
    private void init() {
        CreateSessionServlet sessionServlet = new CreateSessionServlet(registry);
        SaveSessionServlet saveSessionServlet = new SaveSessionServlet(registry);
        GameServlet gameServlet = new GameServlet(registry);
        LoadSessionServlet loadSessionServlet = new LoadSessionServlet(registry);
        ListGameSessionsServlet listGameSessionsServlet = new ListGameSessionsServlet(registry);
        TranslationServlet translationServlet = new TranslationServlet(registry);
        ItemUseServlet itemUseServlet = new ItemUseServlet(registry);
        ItemDropServlet itemDropServlet = new ItemDropServlet(registry);
        
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(InfoServlet.class, "/");
        context.addServlet(new ServletHolder(gameServlet), "/section/");
        context.addServlet(new ServletHolder(sessionServlet), "/session/");
        context.addServlet(new ServletHolder(saveSessionServlet), "/session/save/");
        context.addServlet(new ServletHolder(loadSessionServlet), "/session/load/");
        context.addServlet(new ServletHolder(listGameSessionsServlet), "/session/list/");
        context.addServlet(new ServletHolder(translationServlet), "/translate/");
        context.addServlet(new ServletHolder(itemUseServlet), "/item/use/");
        context.addServlet(new ServletHolder(itemDropServlet), "/item/drop/");
        
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
        GameSessionCleanupService gameSessionObserver = new GameSessionCleanupService(registry.sessionService());
        //gameSessionObserver.start();
        server.start();
        server.join();
    }
    
    
}
