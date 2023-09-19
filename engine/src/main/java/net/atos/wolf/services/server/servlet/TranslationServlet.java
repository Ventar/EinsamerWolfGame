package net.atos.wolf.services.server.servlet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.SessionService;
import net.atos.wolf.services.translation.Translation;
import net.atos.wolf.services.translation.TranslationService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TranslationServlet extends BaseServlet {

    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestData {
        public List<String> keys;
    }

    @ToString
    public static class ResponseData {
        public List<Translation> translations;

    }

    /**
     * a translater that filters with keywords words and translate them into german etc.
     */
    private TranslationService translationService = new TranslationService("/translation.json");

    public TranslationServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            TranslationServlet.RequestData data = JsonUtils.MAPPER.readValue(request.getReader(), TranslationServlet.RequestData.class);
            LOG.debug("Received translation servlet request with data: {}", data);
            TranslationServlet.ResponseData responseData = new TranslationServlet.ResponseData();


            List<Translation> translations = new ArrayList<>();

            for (String key : data.keys) {

                Translation t = translationService.translate(key);

                if (t == null) {
                    t = new Translation();
                    t.de(key);
                    t.tooltip(key);
                    t.key(key);
                    t.png("");
                }

                translations.add(t);
            }

            responseData.translations = translations;

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JsonUtils.MAPPER.writeValueAsString(responseData));

        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
