package net.atos.wolf.services;

import java.sql.SQLOutput;
import java.util.*;

public class UIService {
    /**
     * Renders things
     *
     * @param text
     * @param options
     * @return
     */
    public AnswerOption render(String text, String headLine, Collection<AnswerOption> options) {

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
        System.out.println("\n--------------------------------------------------------------");

        System.out.println(headLine);

        System.out.println("--------------------------------------------------------------\n");
        System.out.println(splitIntoLines(text, 80) + "\n");


        // Ausgabe des Textes auf der KOmmandozeile
        // Schleife und Ausgabe der Optionen, rendern von Nummern
        for (AnswerOption answerOption : options) {
            System.out.println("(" + answerOption.answerKey() + ") " + answerOption.text());
        }
        // EINGABE
        // --------------------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWähle einen Option");


        for (; ; ) {

            try {
                String userChoice = scanner.nextLine();
                int i = Integer.parseInt(userChoice);
                for (AnswerOption a : options) {
                    if (i == a.answerKey()) {
                        return a;
                    }
                }
                System.out.println("Bitte wähle eine gültige Option");
            } catch (NumberFormatException e) {
                System.out.println("Nur Zahlenwerte sind gültig.");
            }


        }


        // Anlegen eines Scanners
        // Überprüfen pb gültige Werte eingegeben wurden


        // Rückgabe eines integer Wertes


    }

    /**
     * Split the passed string into multiple lines.
     *
     * @param text       the text to split
     * @param lineLength the line length
     * @return the splited string
     */
    public String splitIntoLines(String text, int lineLength) {

        StringBuffer stringBuffer = new StringBuffer(text.length() + 20);
        StringTokenizer stringTokenizer = new StringTokenizer(text, " \n", true);
        int count = 0;

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            if (token.equals(" ")) {
                continue;
            } else if (token.equals("\n")) {
                count = 0;
            }

            if (count > lineLength) {
                stringBuffer.append("\n");
                count = 0;
            } else {
                count = count + token.length();
            }

            stringBuffer.append(token).append(" ");
        }


        return stringBuffer.toString();

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
