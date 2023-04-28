package net.atos.wolf.services;

import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {


    public static void main(String[] args) {
        UIService service = new UIService();


        String section = """
                Der Weg führt dich zu einer Ansammlung von Hütten in nicht allzuweiter Entfernung.
                Du  bist  vom  langen Marschieren müde und durstig und brauchst dringend eine Pause.
                
                Wenn du eine der Hütten betreten willst, um dich dort eine Weileauszuruhen drücke (1) 
                Willst du deinen Weg trotz Müdigkeit und Durst lieber fortsetzen drücke (2)
                """;

        String menu = """
               Möchtest du
               
               1. Ein neues Spiel starten
               2. Einen Spielstand Laden
               3. Das Spiel beenden
        """;


        int selection = service.render(section, 2);
        selection = service.render(menu, 3);

        if (selection == 0) {
            // new game
        } else if (selection == 1) {
            // load
        } else if (selection == 2) {
            // exit
        }

    }


}
