package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.server.GameServlet;
import net.atos.wolf.service.ServiceRegistry;
import net.atos.wolf.data.GameSession;

import java.io.IOException;

@Slf4j
@GameServlet("/session/load/")
public class LoadSessionServlet extends BaseServlet {
    
    /**
     * Object representing the JSON data send by the client
     */
    @ToString
    public static class RequestData {

        public String name;

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        
        
        try {
            LoadSessionServlet.RequestData data = registry.jsonMapper().readValue(request.getReader(),
                                                                             LoadSessionServlet.RequestData.class);
            LOG.debug("Received load session servlet request with data: {}", data);
            
            GameSession game = registry().sessionService().loadGameSession(data.name);
            game.modifiedAnswerOptions(registry.gameService().resolveActions(game));
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(registry.jsonMapper().writeValueAsString(game));
            
        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
