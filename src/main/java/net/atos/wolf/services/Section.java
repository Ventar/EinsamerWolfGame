package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
