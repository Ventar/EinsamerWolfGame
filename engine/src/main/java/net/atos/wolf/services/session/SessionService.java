package net.atos.wolf.services.session;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.JsonUtils;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.section.SectionService;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class SessionService {
    
    private SectionService sectionService;
    
    private int startSection = 1000;
    
    private HashMap<String, GameSession> sessionMap = new HashMap();
    
    public SessionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    
    public synchronized GameSession createNewSession() {
        
        GameSession gameSession = new GameSession();
        gameSession.character(new Character());
        gameSession.section(sectionService.getSection(startSection));
        gameSession.id(UUID.randomUUID().toString());
        gameSession.lastUsed(System.currentTimeMillis());
        
        sessionMap.put(gameSession.id(), gameSession);
        
        LOG.debug("Created new session ::= [{}]", gameSession.id());
        
        return gameSession;
    }
    
    public synchronized GameSession savedGameSession(String id, String name) {
        GameSession currentGameSession = sessionMap.get(id);
        
        SavedGameSession savedGameSession = new SavedGameSession();
        savedGameSession.name(name);
        savedGameSession.sectionID(currentGameSession.section().sectionNumber());
        savedGameSession.character(currentGameSession.character());
        
        try {
            JsonUtils.MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(name + ".lwc"), savedGameSession);
        } catch (Exception e) {
            LOG.debug("Could not save GameSession with ID ::=[{}]: ", id, e);
        }
        
        return currentGameSession;
    }
    
    public synchronized GameSession loadGameSession(String name) {
        
        
        try {
            SavedGameSession loaded = JsonUtils.MAPPER.readValue(new File(name + ".json"), SavedGameSession.class);
            GameSession gameSession = new GameSession();
            gameSession.character(loaded.character());
            gameSession.section(sectionService.getSection(loaded.sectionID()));
            gameSession.id(UUID.randomUUID().toString());
            gameSession.lastUsed(System.currentTimeMillis());
            
            sessionMap.put(gameSession.id(), gameSession);
            
            LOG.debug("Created new session ::= [{}]", gameSession.id());
            
            return gameSession;
        } catch (Exception e) {
            LOG.debug("Could not load GameSession with Name ::=[{}]: ", name, e);
        }
        return null;
    }
    
    public synchronized List<String> listGameSessions() {
        
        List<String> list = new ArrayList<>();
        File f = new File(".");
        // System.out.println(f.getAbsolutePath());
        if (f.isDirectory()) {
            for (File file : f.listFiles((file, name) -> name.endsWith("json"))) {
                list.add(file.getName().substring(0, file.getName().indexOf(".")));
                
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
    
    
    }
    
    
    public synchronized GameSession getSessionById(String id) {
        return sessionMap.get(id);
    }
    
    public synchronized void cleanupStaleSessions(long ttl) {
        List<GameSession>
            sessionsToRemove =
            sessionMap.values()
                      .stream()
                      .filter(session -> (System.currentTimeMillis() - session.lastUsed()) > ttl)
                      .collect(Collectors.toList());
        
        sessionsToRemove.forEach(session -> {
            LOG.trace("Cleanup stale session ::= [{}]", session.id());
            sessionMap.remove(session);
        });
        
    }
}
