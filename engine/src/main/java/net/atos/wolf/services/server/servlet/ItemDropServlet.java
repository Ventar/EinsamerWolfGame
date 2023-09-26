package net.atos.wolf.services.server.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameEngine;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.Item;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.session.GameSession;
import net.atos.wolf.services.session.SessionService;
import net.atos.wolf.services.translation.TranslationService;

@Slf4j
public class ItemDropServlet extends BaseServlet {

    @ToString
    public static class RequestData {

        public String id;

        public int position;

    }

    public ItemDropServlet(SessionService sessionService, GameEngine engine, SectionService sectionService) {
        super(sessionService, engine, sectionService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            ItemDropServlet.RequestData data = JsonUtils.MAPPER.readValue(request.getReader(), ItemDropServlet.RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data);
            GameSession session = sessionService.getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {

                Item item = session.character().items().remove(data.position);


//                {
//                    "type": "TAKE_ITEM",
//                        "text": "Nimm den Heiltrank",
//                        "item": {
//                    "id": "LAUMSPUR_POTION",
//                            "usable": true,
//                            "modifiedAttribute": "ENDURANCE",
//                            "modificationValue": 4
//                }



                Action pickUpAction = new Action();
                pickUpAction.type(ActionType.TAKE_ITEM);
                pickUpAction.text("Hebe den Gegenstand wieder auf (" + TRANSLATION_SERVICE.translate(item.id()).de()  + ")");
                pickUpAction.item(item);

                session.modifiedAnswerOptions().add(0,pickUpAction);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(JsonUtils.MAPPER.writeValueAsString(session));
            } else {
                response.setContentType("text/plain");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (Exception e) {
            LOG.debug("Exception during post to /section :", e);
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }


}
