package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.ServiceRegistry;

import java.io.IOException;


@Slf4j
public class SaveSessionServlet extends BaseServlet {
    /**
     * Object representing the JSON data send by the client
     */
    @ToString
    public static class RequestData {

        public String id;

        public String name;
    }

    public SaveSessionServlet(ServiceRegistry registry) {
        super(registry);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        try {
            SaveSessionServlet.RequestData data = registry.jsonMapper().readValue(request.getReader(), SaveSessionServlet.RequestData.class);
            LOG.debug("Received save session servlet request with data: {}", data);

            GameSession saved = registry.sessionService().savedGameSession(data.id, data.name);
            LOG.debug("Saved game session ::= [{}]", saved);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(registry.jsonMapper().writeValueAsString(saved));

        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}