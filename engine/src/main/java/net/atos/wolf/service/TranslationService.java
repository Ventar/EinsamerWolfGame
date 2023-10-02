package net.atos.wolf.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.atos.wolf.data.Translation;

import java.util.HashMap;
import java.util.List;

public class TranslationService {
    private final HashMap<String, Translation> translations = new HashMap<>();
    
    public TranslationService(ObjectMapper mapper, String jsonFile) {
        
        try {
            List<Translation> translationsList =
                mapper.readValue(TranslationService.class.getResourceAsStream(jsonFile),
                                             new TypeReference<List<Translation>>() {
                                             });
            
            for (Translation s : translationsList) {
                translations.put(s.key(), s);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Could not load " + jsonFile + " file: ", e);
        }
        
    }
    
    public Translation translate(String key) {
        if (translations.containsKey(key)) {
            return translations.get(key);
        } else {
            return null;
        }
    }
    
}
