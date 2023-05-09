package net.atos.wolf.services;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class UIService {
    /**
     * Renders things
     *
     * @param text
     * @param options
     * @return
     */
    public int render(String text, Collection<AnswerOption> options) {

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
        System.out.println(text);
        // Ausgabe des Textes auf der KOmmandozeile
        // Schleife und Ausgabe der Optionen, rendern von Nummern
        for (AnswerOption answerOption : options) {
            System.out.println("(" + answerOption.getAnswer() + ") " + answerOption.getText());
        }
        // EINGABE
        // --------------------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wähle einen Weg");

        int userChoice = scanner.nextInt();


        // Anlegen eines Scanners
        // Überprüfen pb gültige Werte eingegeben wurden


        // Rückgabe eines integer Wertes


        return userChoice;
    }

    /**
     * Wait for player input
     *
     * @return
     */
    public String waitForUserInput() {

        return null;
    }


}
