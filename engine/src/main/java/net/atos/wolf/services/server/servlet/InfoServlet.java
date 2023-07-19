package net.atos.wolf.services.server.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.section.SectionService;

import java.io.IOException;

public class InfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("{ \"status\": \"ok\"}");
    }
}
