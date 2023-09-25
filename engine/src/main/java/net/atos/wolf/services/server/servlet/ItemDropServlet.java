package net.atos.wolf.services.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.session.SessionService;

@Slf4j
public class ItemDropServlet extends BaseServlet {

    @ToString
    public static class RequestData {

        public String id;

        public int position;

    }

    public ItemDropServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            ItemDropServlet.RequestData data = JsonUtils.MAPPER.readValue(request.getReader(), ItemDropServlet.RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data);
            GameSession session = sessionService.getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {

                session.character().items().remove(data.position);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(JsonUtils.MAPPER.writeValueAsString(session));
            } else {
                response.setContentType("text/plain");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }


}
