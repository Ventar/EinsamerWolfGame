package net.atos.wolf.services.section;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.atos.wolf.services.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a section of the book. The player moves from section to section and takes decisions based on the offered actions for every individual section. These sections are
 * stored in JSON files where every file represents a complete book of the lone wolf series.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-17
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString(exclude={"text"})
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
    private List<Action> actions;

    public Section() {}

    public Section(Section section) {
        this.sectionNumber = section.sectionNumber;
        this.text = section.text;
        if (section.actions != null) {
            this.actions = new ArrayList<>();
            for (Action action : section.actions) {
                this.actions.add(new Action(action));
            }
        }
    }

    /**
     * Gives the current section number
     * @return
     */
    public int getSectionNumber() {
        return sectionNumber;
    }
}
