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

    private String text;


    public Action setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    public Action setTargetSection(int targetSection) {
        this.targetSection = targetSection;
        return this;
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

    public Action setType(ActionType type) {
        this.type = type;
        return this;
    }

    public Action setSkill(KaiSkill skill) {
        this.skill = skill;
        return this;
    }

    public KaiSkill isSkill() {
        return skill;
    }

    public String getText() {
        return text;
    }

    public Action setText(String text) {
        this.text = text;
        return this;
    }



    public String toString() {
        String s = "";


        if (text == null) {
            s = s + "text = null \n";
        } else if (text.length() < 15) {
            s = s + "text=" + this.text + "\n";
        } else {
            s = s + "text=" + this.text.substring(0, 15) + "...\n";
        }


        return "Action{" +
                "mandatory=" + mandatory +
                ", targetSection=" + targetSection +
                ", type=" + type +
                ", text=" + s +
                ", skill=" + skill +
                '}';
    }
}
