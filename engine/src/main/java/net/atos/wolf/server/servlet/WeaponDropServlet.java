package net.atos.wolf.server.servlet;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.GameSession;
import net.atos.wolf.server.GameServlet;

@Slf4j
@GameServlet("/weapon/drop/")
public class WeaponDropServlet extends BaseServlet{

    @ToString
    public static class RequestData {

        public String id;

        public int Weapon;


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            WeaponDropServlet.RequestData data =registry.jsonMapper().readValue(request.getReader(), WeaponDropServlet.RequestData.class);
            LOG.debug("Received game servlet request with data: {}", data);
            GameSession session = registry.sessionService().getSessionById(data.id);
            LOG.debug("Current game session: {}", session);

            if (session != null) {

                if (data.Weapon == 1){
                    session.character().weaponOne(null);
                }

                if (data.Weapon == 2){
                    session.character().weaponTwo(null);
                }




//                Action pickUpAction = new Action();
//                pickUpAction.type(ActionType.TAKE_ITEM);
//                pickUpAction.text("Hebe den Gegenstand wieder auf (" + registry.translation().translate(weapon1.id()).de()  + ")");
//                pickUpAction.weapon(weapon1);
//
//                session.modifiedAnswerOptions().add(0,pickUpAction);

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
