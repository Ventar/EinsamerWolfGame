package net.atos.wolf.services.session;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.section.SectionService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class SessionService {

    private SectionService sectionService;

    private HashMap<String, GameSession> sessionMap = new HashMap();

    public SessionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public synchronized GameSession createNewSession() {
        GameSession gameSession = new GameSession();
        gameSession.character(new Character());
        gameSession.section(sectionService.getSection(1000));
        gameSession.id(UUID.randomUUID().toString());
        gameSession.lastUsed(System.currentTimeMillis());

        sessionMap.put(gameSession.id(), gameSession);

        LOG.debug("Created new session ::= [{}]", gameSession.id());

        return gameSession;
    }

    public synchronized GameSession getSessionById(String id) {
        return sessionMap.get(id);
    }

    public synchronized void cleanupStaleSessions(long ttl) {
        List<GameSession> sessionsToRemove = sessionMap.values().stream().filter(session -> (System.currentTimeMillis() - session.lastUsed()) > ttl).collect(Collectors.toList());

        sessionsToRemove.forEach(session -> {
            LOG.trace("Cleanup stale session ::= [{}]", session.id());
            sessionMap.remove(session);
        });

    }
}
