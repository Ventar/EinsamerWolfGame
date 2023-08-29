package net.atos.wolf.services.server.servlet;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.session.SavedGameSession;
import net.atos.wolf.services.session.SessionService;

import java.io.IOException;
@Slf4j
public class LoadSessionServlet extends BaseServlet {

    /**
     * Object representing the JSON data send by the client
     */
    @ToString
    public static class RequestData {


        public String name;
    }

    public LoadSessionServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        try {
            LoadSessionServlet.RequestData data = JsonUtils.MAPPER.readValue(request.getReader(), LoadSessionServlet.RequestData.class);
            LOG.debug("Received load session servlet request with data: {}", data);

            GameSession game =  sessionService.loadGameSession(data.name);
            game.modifiedAnswerOptions(engine.resolveActions(game));

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JsonUtils.MAPPER.writeValueAsString(game));

        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
