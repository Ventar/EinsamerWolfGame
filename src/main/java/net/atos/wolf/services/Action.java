package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import net.atos.wolf.character.KaiSkill;
import net.atos.wolf.character.SpecialItem;
import net.atos.wolf.character.Weapon;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Action {

    private ActionType type;

    private boolean mandatory;

    private int targetSection;

    private KaiSkill skill;

    private String text;

    private Weapon weapon;

    private SpecialItem specialItem;


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
}
