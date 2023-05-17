package net.atos.wolf.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.val;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final HashMap<Integer, Section> sections = new HashMap<>();


    public SectionService() {

        try {
            List<Section> sectionList = MAPPER.readValue(Main.class.getResourceAsStream("/ew1.json"), new TypeReference<List<Section>>() {
            });

            for (Section s : sectionList) {
                sections.put(s.getSectionNumber(), s);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load ew1.json file: ", e);
        }

    }

    /**
     * Get the section with the passed number
     *
     * @param idToLoad the {@link Section#getSectionNumber()} if the section to load
     * @return Section
     */
    public Section getSection(int idToLoad) {
        return sections.get(idToLoad);
    }

    public void addSection(Section section) {
        sections.put(section.getSectionNumber(), section);
    }

    /**
     * Returns all section stored in the service
     *
     * @return the list of sections
     */
    public List<Section> getSections() {
        return new ArrayList<Section>(sections.values());
    }

    /**
     * Replaces all sections in this service with sthe sections from the passed list.
     *
     * @param sections the sections
     */
    public void setSections(List<Section> sections) {
        this.sections.clear();
        sections.forEach(s -> this.sections.put(s.getSectionNumber(), s));

    }


}
