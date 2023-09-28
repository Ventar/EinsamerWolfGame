package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.server.GameServlet;
import net.atos.wolf.service.ServiceRegistry;

import java.util.List;

@Slf4j
@GameServlet("/session/list/")
public class ListGameSessionsServlet extends BaseServlet {

    @ToString
    public static class ResponseData {
        public List<String> names;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("Try to list available game sessions in file system...");
        try {
            ResponseData data = new ResponseData();
            data.names = registry.sessionService().listGameSessions();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(registry.jsonMapper().writeValueAsString(data));


        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
