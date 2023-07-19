package net.atos.wolf.services.server.servlet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.SessionService;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.section.SectionService;

@Getter
@Setter
public class BaseServlet extends HttpServlet {

    protected static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    protected SessionService sessionService;

    protected GameEngine engine;

    protected SectionService sectionService;

    public BaseServlet(SessionService service, GameEngine engine, SectionService sectionService){
        this.sessionService = service;
        this.engine = engine;
        this.sectionService = sectionService;
    }
}
