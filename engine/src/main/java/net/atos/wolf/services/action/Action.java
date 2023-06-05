package net.atos.wolf.services.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.character.Item;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.character.SpecialItem;
import net.atos.wolf.services.character.Weapon;

import java.util.List;
import java.util.Objects;

/**
 * Meta information stored in the JSON representation of the book to determine which actions a player can execute in a certain section of the book.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Action {

    @Getter
    private ActionType type;
    private boolean mandatory;
    private int targetSection;
    private KaiSkill skill;
    @Setter
    private String text;
    private Weapon weapon;
    private SpecialItem specialItem;
    private String value;
    private String attribute;
    private Item item;
    private List<Integer> randomSection;
    private int numberOfSkills;
    private boolean noOtherOption;
    private Battle battle;
    @Getter
    @Setter
    private int battleRounds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return mandatory == action.mandatory && targetSection == action.targetSection && type == action.type && skill == action.skill && Objects.equals(text, action.text) && weapon == action.weapon && specialItem == action.specialItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mandatory, targetSection, skill, text, weapon, specialItem);
    }


    @Override
    public String toString() {
        return "Action{" +
                       "type=" + type +
                       ", mandatory=" + mandatory +
                       ", targetSection=" + targetSection +
                       ", skill=" + skill +
                       ", text='" + text + '\'' +
                       ", weapon=" + weapon +
                       ", specialItem=" + specialItem +
                       '}';
    }
}
