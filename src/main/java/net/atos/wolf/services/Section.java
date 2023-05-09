package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.atos.wolf.character.Attribute;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Section {

    private ArrayList<Action> actions;

    private int sectionNumber;

    private String text;

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
