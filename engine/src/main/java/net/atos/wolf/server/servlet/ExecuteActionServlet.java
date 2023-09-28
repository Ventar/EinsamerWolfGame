package net.atos.wolf.server.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.Action;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.server.GameServlet;
import net.atos.wolf.service.ServiceRegistry;

import java.io.IOException;

/**
 * Servlet responsible for progressing with the adventure.
 *
 * @author Michael Rodenbuecher
 * @since 2023-07-18
 */
@Slf4j
@GameServlet("/section/")
public class ExecuteActionServlet extends BaseServlet {

    /**
     * Object representing the JSON data send by the client
     */
    @ToString
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            RequestData data = registry.jsonMapper().readValue(request.getReader(), RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data );
            GameSession session = registry.sessionService().getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {
                Action actionToExecute = session.modifiedAnswerOptions().get(data.action);
                registry.gameService().executeAction(session, actionToExecute);

                session.lastUsed(System.currentTimeMillis());
                session.modifiedAnswerOptions(registry.gameService().resolveActions(session));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(registry.jsonMapper().writeValueAsString(session));
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
