package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.atos.wolf.character.KaiSkill;
import net.atos.wolf.character.Weapon;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Section {

    private int sectionNumber = 0;

    private String text = null;
    private ArrayList<Action> actions = new ArrayList<>();

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setSectionNumber(int s) {
        sectionNumber = s;
    }

    public void setText(String z) {
        text = z;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public String getText() {
        return text;
    }

    public void setActions(ArrayList<Action> f) {
        actions = f;
    }

    public void addAction(Action action) {
        actions.add(action);
    }


    public Section addAction(int targetSectionNumber, boolean mandatory, ActionType actionType, String actionText, KaiSkill skill, Weapon weapon) {

        Action action = new Action();

        action.setTargetSection(targetSectionNumber);
        action.setMandatory(mandatory);
        action.setType(actionType);
        action.setText(actionText);
        action.setSkill(skill);
        action.setWeapon(weapon);

        addAction(action);

        return this;
    }
    public Action accusation(int i){
        return actions.get(i);
        
    }


    @Override
    public String toString() {
        String s = "";
        s = s + "sectionNumber=" + sectionNumber + ",\n";
        if (text == null) {
            s = s + "text = null \n";
        } else if (text.length() < 15) {
            s = s + "text=" + text + "\n";
        } else {
            s = s + "text=" + text.substring(0, 15) + "...\n";
        }
        s = s + "actions=" + actions;
        return s;
    }


}
