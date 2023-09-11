package net.atos.wolf.services.translation;

import com.fasterxml.jackson.core.type.TypeReference;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.common.ServiceUtilities;
import net.atos.wolf.services.section.SectionService;

import java.util.HashMap;
import java.util.List;

public class TranslationService {
    private final HashMap<String, Translation> translations = new HashMap<>();

    public TranslationService(String jsonFile) {

        try {
            List<Translation> translationsList = ServiceUtilities.OBJECT_MAPPER.readValue(SectionService.class.getResourceAsStream(jsonFile), new TypeReference<List<Translation>>() {
            });

            for (Translation s : translationsList) {
                translations.put(s.key(), s);
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not load " + jsonFile + " file: ", e);
        }

    }

    public String translate(String key) {
        if (translations.containsKey(key)) {
            return translations.get(key).de();
        } else {
            return key;
        }
    }

    public String translateTip(String key) {
        if (translations.containsKey(key)) {
            return translations.get(key).tooltip();
        } else {
            return key;
        }
    }


}
