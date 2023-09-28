package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.data.Item;
import net.atos.wolf.service.ServiceRegistry;

@Slf4j
public class ItemUseServlet extends BaseServlet {


    @ToString
    public static class ResponseData {

        public int value;
    }


    @ToString
    public static class RequestData {
        public String id;
        public int position;

    }

    public ItemUseServlet(ServiceRegistry registry) {
        super(registry);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            ItemUseServlet.RequestData data = registry.jsonMapper().readValue(request.getReader(), ItemUseServlet.RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data);
            GameSession session = registry.sessionService().getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {

                Item item = session.character().items().remove(data.position);
                session.character().getAttributeByName(item.modifiedAttribute()).add(item.modificationValue());

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



