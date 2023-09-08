package net.atos.wolf.services.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.SessionService;
import net.atos.wolf.services.translation.TranslationService;

@Slf4j
public class TranslationServlet extends BaseServlet {

    @ToString
    public static class RequestData {
        public String key;
    }

    @ToString
    public static class ResponseData {
        public String key;
        public String value;
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

            String translated = translationService.translate(data.key);

            TranslationServlet.ResponseData responseData = new TranslationServlet.ResponseData();
            responseData.key = data.key;
            responseData.value = translated;

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
