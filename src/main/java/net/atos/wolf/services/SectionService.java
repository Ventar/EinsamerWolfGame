package net.atos.wolf.services;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionService {

    private HashMap<Integer, Section> sections = new HashMap<>();

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
