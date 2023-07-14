package net.atos.wolf.services;

import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.section.SectionService;
import net.atos.wolf.services.server.SessionServlet;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
@Getter
@Setter
public class SessionService {

    private SectionService sectionService = new SectionService("/ew1.json");

    HashMap<String, GameSession> serviceHashMap = new HashMap();

    public GameSession createNewSession() {
        GameSession gameSession = new GameSession();
        gameSession.character(new Character());
        gameSession.section(sectionService.getSection(1000));
        gameSession.id(UUID.randomUUID().toString());
        gameSession.lastUsed(System.currentTimeMillis());

        serviceHashMap.put(gameSession.id(), gameSession);

        return gameSession;
    }

    public GameSession getSessionById(String id) {
        return serviceHashMap.get(id);
    }
}
