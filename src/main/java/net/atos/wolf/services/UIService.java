package net.atos.wolf.services;

import java.util.List;
import java.util.Scanner;

public class UIService {
    /**
     * Renders things
     * @param text
     * @param options
     * @return
     */
    public int render(String text, int options) {

        // VALDIERUNG
        // --------------------------------------------------------------
        // Überprüfung der Textlänge
        // Formatierung des Textes auf eine festgelegte Zeilenlänge
        // Überprüfung ob ein text Übergeben wurde (oder null ist(
        // Überprüfung ob eine Liste mit Optionen vorhanden ist)
        // Überprüfen ob mindestens eine Option vorhanden ist
        // Überprüfen, dass wir nicht mehr als x Optionen haben


        // TEXT AUSGABE
        // --------------------------------------------------------------

        // Ausgabe des Textes auf der KOmmandozeile
        // Schleife und Ausgabe der Optionen, rendern von Nummern

        // EINGABE
        // --------------------------------------------------------------

        // Anlegen eines Scanners
        // Überprüfen pb gültige Werte eingegeben wurden


        // Rückgabe eines integer Wertes


        return 0;
    }

    /**
     * Wait for player input
     * @return
     */
    public String waitForUserInput(){

        return null;
    }

    public static void main(String[] args) {
        String s = """                
                Willkommen zum Einsamer Wolf Spiel.
                Möchtest du ein neues Spiel starten (1) oder
                einen bestehenden Character laden (2) ?              
                """;

        System.out.println(s);
        System.out.println(">");


        UIService service = new UIService();

        int selection = service.render(s,2);


    }


}
