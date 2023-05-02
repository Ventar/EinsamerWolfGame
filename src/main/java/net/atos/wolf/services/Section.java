package net.atos.wolf.services;

import javax.naming.directory.ModificationItem;
import java.util.ArrayList;
import java.util.List;

public class Section {

    private int number;
    private String text;
    private String image;
    private ArrayList<ModificationItem> modifications = new ArrayList<>();
    private List<Action> actions;

}
