package net.atos.wolf.services.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.SessionService;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.section.SectionService;
import org.eclipse.jetty.server.session.Session;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class GameServlet extends BaseServlet {

    private static final long serialVersionUID = 1;

    public GameServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GameServletPostJson data = MAPPER.readValue(request.getReader(), GameServletPostJson.class);
        GameSession session = sessionService.getSessionById(data.id);
        if (session != null) {
            Action actionToExecute = session.section().actions().get(data.action);
            engine.executeAction(session, actionToExecute);
            session.lastUsed(System.currentTimeMillis());

            response.getWriter().println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(session));
        }else {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
