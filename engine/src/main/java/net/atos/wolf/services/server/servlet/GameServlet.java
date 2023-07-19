package net.atos.wolf.services.server.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.session.SessionService;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.section.SectionService;

import java.io.IOException;

/**
 * Servlet responsible for progressing with the adventure.
 *
 * @author Michael Rodenbuecher
 * @since 2023-07-18
 */
@Slf4j
public class GameServlet extends BaseServlet {

    /**
     * Object representing the JSON data send by the client
     */
    public static class RequestData {
        /**
         * The action to execute on the {@link GameSession} which is attached to the id attribute of the request.
         */
        public int action;
        /**
         * The unique session id on the server that is handled by the client sending this request.
         */
        public String id;
    }


    public GameServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            RequestData data = MAPPER.readValue(request.getReader(), RequestData.class);
            GameSession session = sessionService.getSessionById(data.id);

            if (session != null) {
                Action actionToExecute = session.modifiedAnswerOptions().get(data.action);
                engine.executeAction(session, actionToExecute);

                session.lastUsed(System.currentTimeMillis());
                session.modifiedAnswerOptions(engine.resolveActions(session));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(MAPPER.writeValueAsString(session));
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
