package net.atos.wolf.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * Service registry contains multiple services and allows easy access to all available services of the application.
 *
 * @author Michael Rodenbuecher
 * @since 2023-09-28
 */
public class ServiceRegistry {
    
    @Getter
    private ObjectMapper jsonMapper = new ObjectMapper();
    
    @Getter
    private BattleService battle = new BattleService();
    
    @Getter
    private TranslationService translation = new TranslationService("/translation.json");
    
    @Getter
    private SectionService sectionService = new SectionService();
    
    @Getter
    private SessionService sessionService = new SessionService(this);
    
    @Getter
    private GameService gameService = new GameService(sectionService);
    
    public ServiceRegistry() {
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
    
}
