package net.atos.wolf.services;

import java.util.ArrayList;
import java.util.HashMap;

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


}
