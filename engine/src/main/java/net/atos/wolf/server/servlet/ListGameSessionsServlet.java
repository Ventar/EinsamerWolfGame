package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.service.GameService;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.service.SectionService;
import net.atos.wolf.service.SessionService;

import java.util.List;

@Slf4j
public class ListGameSessionsServlet extends BaseServlet {

    @ToString
    public static class ResponseData {
        public List<String> names;
    }

    public ListGameSessionsServlet(SessionService sessionService, GameService engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("Try to list available game sessions in file system...");
        try {
            ResponseData data = new ResponseData();
            data.names = sessionService().listGameSessions();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JsonUtils.MAPPER.writeValueAsString(data));


        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
