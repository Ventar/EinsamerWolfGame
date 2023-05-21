package net.atos.wolf.services.ui;

import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UIService {
    /**
     * Renders things
     *
     * @param text
     * @param options
     *
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


        while (true) {
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

    }

    /**
     * Split the passed string into multiple lines.
     *
     * @param text       the text to split
     * @param lineLength the line length
     *
     * @return the splited string
     */
    public String splitIntoLines(String text, int lineLength) {

        StringBuffer stringBuffer = new StringBuffer(text.length() + 20);
        StringTokenizer stringTokenizer = new StringTokenizer(text, " \n", true);
        int count = 0;

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            // ensures that new lines in the JSON file are taken into account during the split, i.e we reset the counter if we detect a new line

            if (count == 0 && token.equals(" ")) {
                count++;
                continue; // do not start a new line with a white space
            } else if (!token.equals(" ") && token.equals("\n") || count > lineLength) {
                stringBuffer.append("\n");
                count = 0; // reset the counter, we start a new line
            } else {
                stringBuffer.append(token);
                count += token.length();
            }

        }

        return stringBuffer.toString();
    }

}
