package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.atos.wolf.server.GameServlet;
import net.atos.wolf.service.ServiceRegistry;
import net.atos.wolf.data.GameSession;

import java.io.IOException;

@GameServlet("/session/")
public class CreateSessionServlet extends BaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        
        GameSession session = registry.sessionService().createNewSession();
        session.modifiedAnswerOptions(session.section().actions());
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(registry.jsonMapper().writeValueAsString(session));
    }
    
}