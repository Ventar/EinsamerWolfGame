package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import net.atos.wolf.character.KaiSkill;
import net.atos.wolf.character.Weapon;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

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
    public void storeSections(List<Section> sections){
        File file = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        JSONObject objectItem = new JSONObject();
        //Initialize new Instance of an Json Array
        //Initialize new Instance of an Json Object as object
        //Initialize new Instance of an Json Object as objectItem
        for(int i = 0; i < sections.size(); i++) {
            objectItem.put("sectionNumber",sections.get(i).getSectionNumber());
            objectItem.put("text",sections.get(i).getText());
            objectItem.put("actions",sections.get(i).getActions());
            object.put("list",objectItem);
            jsonArray.put(object);


            // Put Attributes with value into obejct item
            // Put Obejct item into Object
            // Put Object into Json Array
        }

        // Create new instance of a file with path
        // Create this File
        // Write Json Array into File



file = new File("C:EinsamerWolfGame/src/main/resources/lw1.data.json");

        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

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
