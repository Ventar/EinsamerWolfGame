package net.atos.wolf.services.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.character.Item;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.character.SpecialItem;
import net.atos.wolf.services.character.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Meta information stored in the JSON representation of the book to determine which actions a player can execute in a certain section of the book.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Action {

    private ActionType type;
    private boolean mandatory;
    private int targetSection;
    private KaiSkill skill;
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
    private int hand;

    public Action() {
    }

    public Action(ActionType type, String text) {
        this.type = type;
        this.text = text;
    }

    public Action(Action action) {
        this.type = action.type;
        this.mandatory = action.mandatory;
        this.targetSection = action.targetSection;
        this.skill = action.skill;
        this.text = action.text;
        this.weapon = action.weapon;
        this.specialItem = action.specialItem;
        this.value = action.value;
        this.attribute = action.attribute;
        this.item = action.item;
        if (action.randomSection != null) {
            this.randomSection = new ArrayList<>();
            for (Integer integer : action.randomSection) {
                this.randomSection.add(integer);
            }
        }
        this.numberOfSkills = action.numberOfSkills;
        this.noOtherOption = action.noOtherOption;
        if (action.battle != null) {
            this.battle = new Battle(action.battle);
        }

        this.hand = hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return mandatory == action.mandatory && targetSection == action.targetSection && numberOfSkills == action.numberOfSkills && noOtherOption == action.noOtherOption && hand == action.hand && type == action.type && skill == action.skill && Objects.equals(text, action.text) && weapon == action.weapon && specialItem == action.specialItem && Objects.equals(value, action.value) && Objects.equals(attribute, action.attribute) && item == action.item && Objects.equals(randomSection, action.randomSection) && Objects.equals(battle, action.battle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mandatory, targetSection, skill, text, weapon, specialItem, value, attribute, item, randomSection, numberOfSkills, noOtherOption, battle, hand);
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
                       ", value='" + value + '\'' +
                       ", attribute='" + attribute + '\'' +
                       ", item=" + item +
                       ", randomSection=" + randomSection +
                       ", numberOfSkills=" + numberOfSkills +
                       ", noOtherOption=" + noOtherOption +
                       ", battle=" + battle +
                       ", hand=" + hand +
                       '}';
    }
}
