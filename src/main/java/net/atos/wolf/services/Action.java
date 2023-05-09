package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.atos.wolf.character.KaiSkill;

import java.lang.reflect.Type;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {
    private ActionType type;

    private boolean mandatory;
    private int targetSection;

    private KaiSkill skill;


    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public void setTargetSection(int targetSection) {
        this.targetSection = targetSection;
    }

    public int getTargetSection() {
        return targetSection;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public void setSkill(KaiSkill skill) {
        this.skill = skill;
    }

    public KaiSkill isSkill() {
        return skill;
    }

    public String toString() {
        return "Action{" +
                "mandatory=" + mandatory +
                ", targetSection=" + targetSection +
                ", type=" + type +
                '}';
    }
}
