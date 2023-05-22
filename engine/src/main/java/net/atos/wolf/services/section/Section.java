package net.atos.wolf.services.section;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import net.atos.wolf.services.action.Action;

import java.util.ArrayList;

/**
 * Represents a section of the book. The player moves from section to section and takes decisions based on the offered actions for every individual section. These sections are
 * stored in JSON files where every file represents a complete book of the lone wolf series.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-17
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Section {

    /**
     * The number of the section from the book
     */
    private int sectionNumber = 0;

    /**
     * The text to display to the player
     */
    private String text = null;

    /**
     * A list of actions that can be executed by the player.
     */
    private ArrayList<Action> actions;

    @Override
    public String toString() {
        return "Section{" +
                       "sectionNumber=" + sectionNumber +
                       '}';
    }
}
