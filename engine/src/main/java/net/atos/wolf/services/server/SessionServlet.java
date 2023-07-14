package net.atos.wolf.services.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.SessionService;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.section.SectionService;

import java.io.IOException;

public class SessionServlet extends BaseServlet {

    public SessionServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(sessionService.createNewSession()));
    }

}