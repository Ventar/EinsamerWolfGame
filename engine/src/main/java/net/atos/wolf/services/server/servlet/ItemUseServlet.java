package net.atos.wolf.services.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.SessionService;
import net.atos.wolf.services.translation.Translation;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemUseServlet extends BaseServlet {


    @ToString
    public static class ResponseData {
        public int value;

    }

    public ItemUseServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }






}



