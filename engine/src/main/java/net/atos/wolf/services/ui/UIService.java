package net.atos.wolf.services.ui;

import net.atos.wolf.services.action.Action;

import java.util.List;
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
    public Action render(String text, String headLine, List<Action> options) {


        // print the text and possible actions

        System.out.println("\n--------------------------------------------------------------");
        System.out.println(headLine);
        System.out.println("--------------------------------------------------------------\n");

        System.out.println(splitIntoLines(text, 120) + "\n");

        for (int i = 1; i <= options.size(); i++) {
            System.out.println("(" + i + ") " + options.get(i - 1).text());
        }

        // handle the user input

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWähle einen Option");


        while (true) {
            try {
                String userChoice = scanner.nextLine();
                int i = Integer.parseInt(userChoice);
                return options.get(i - 1);
            } catch (Exception e) {
                System.out.println("Bitte wähle eine gültige Option");
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

            // ensures that new lines in the JSON file are taken into account during the split, i.e. we reset the counter if we detect a new line

            if (count == 0 && token.equals(" ")) {
                count++;
                continue; // do not start a new line with a white space
            } else if (!token.equals(" ") && token.equals("\n") || count > lineLength) {
                stringBuffer.append("\n");
                stringBuffer.append(token);
                count = token.length(); // reset the counter, we start a new line
            } else {
                stringBuffer.append(token);
                count += token.length();
            }

        }

        return stringBuffer.toString();
    }

}
