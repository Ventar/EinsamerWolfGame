package net.atos.wolf.server.servlet;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.server.GameServlet;

@Slf4j
@GameServlet("/kaiskill/drop/")
public class KaiSkillDropServlet extends BaseServlet {

    @ToString
    public static class RequestData {

        public String id;

        public int position;


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            KaiSkillDropServlet.RequestData data = registry.jsonMapper().readValue(request.getReader(), KaiSkillDropServlet.RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data);
            GameSession session = registry.sessionService().getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {

                session.character().skills().remove(data.position);


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
