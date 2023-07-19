package net.atos.wolf.services.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.session.SessionService;
import net.atos.wolf.services.section.SectionService;

import java.io.IOException;

public class SessionServlet extends BaseServlet {

    public SessionServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        GameSession session = sessionService.createNewSession();
        session.modifiedAnswerOptions(session.section().actions());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(MAPPER.writeValueAsString(session));
    }

}