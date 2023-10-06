package net.atos.wolf.server.servlet;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.Action;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.data.Item;
import net.atos.wolf.server.GameServlet;
import net.atos.wolf.service.gameengine.ActionType;

@Slf4j
@GameServlet("/weapon/drop/")
public class WeaponDropServlet extends BaseServlet {

    @ToString
    public static class RequestData {

        public String id;

        public int weapon;


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            WeaponDropServlet.RequestData data = registry.jsonMapper().readValue(request.getReader(), WeaponDropServlet.RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data);
            GameSession session = registry.sessionService().getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {

                if (data.weapon == 1) {
                    Item weaponOne = session.character().weaponOne();
                    session.character().weaponOne(null);

                    Action pickUpAction = new Action();
                    pickUpAction.type(ActionType.TAKE_WEAPON);
                    pickUpAction.text("Hebe die Waffe wieder auf (" + weaponOne.text() + ")");
                    pickUpAction.weapon(weaponOne);
                    session.modifiedAnswerOptions().add(0, pickUpAction);
                }

                if (data.weapon == 2) {
                    Item weapon2 = session.character().weaponTwo();
                    session.character().weaponTwo(null);
                    Action pickUpAction = new Action();
                    pickUpAction.type(ActionType.TAKE_WEAPON);
                    pickUpAction.text("Hebe die Waffe wieder auf (" + weapon2.text() + ")");
                    pickUpAction.weapon(weapon2);
                    session.modifiedAnswerOptions().add(0, pickUpAction);
                }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(registry.jsonMapper().writeValueAsString(session));
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
