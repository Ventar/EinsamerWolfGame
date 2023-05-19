package net.atos.wolf.services;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;

/**
 * Service to provide {@link Section}s to the {@link GameEngine}.
 *
 * @author Noel Masur, Julius Reismann, Michael Rodenbuecher
 * @since 2023-05-19
 */
public class SectionService {

    /**
     * Contains all services of a single book.
     */
    private final HashMap<Integer, Section> sections = new HashMap<>();


    /**
     * Creates a new section service.
     *
     * @param jsonFile the file that contains the sections of the book we want to manage with this service.
     */
    public SectionService(String jsonFile) {

        try {
            List<Section> sectionList = ServiceUtilities.OBJECT_MAPPER.readValue(Main.class.getResourceAsStream(jsonFile), new TypeReference<List<Section>>() {
            });

            for (Section s : sectionList) {
                sections.put(s.getSectionNumber(), s);
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not load " + jsonFile + " file: ", e);
        }

    }

    /**
     * Get the section with the passed number
     *
     * @param idToLoad the {@link Section#getSectionNumber()} if the section to load
     *
     * @return Section
     */
    public Section getSection(int idToLoad) {
        return sections.get(idToLoad);
    }


}
