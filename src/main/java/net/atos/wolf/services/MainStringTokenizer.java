package net.atos.wolf.services;

import java.util.StringTokenizer;

public class MainStringTokenizer {

    private static int LINE_LENGTH = 75;


    public static void main(String[] args) {
        String s = "Du folgst dem Oberst, während er sein Pferd durch die Menschenmenge treibt und sich dabei wenig darum schert, ob ihm die Leute noch rechtzeitig ausweichen können. Dir ist es unmöglich,deinen sommerlendischen Landsleuten gegenüber ein derartgewissenloses Verhalten an den Tag zu legen, und so hast du den rücksichtslosen Offizier schon bald aus den Augen verloren.Die breite Prachtstraße, die sich am anderen Ende des Platzes anschließt, ist ebenso bevölkert wie der Platz selbst, und dein Pferd ist dir auch hier nicht von Vorteil, so dass du widerwillig beschließt abzusteigen und deinen Weg zu Fuß fortzusetzen.Nach etwa fünfzig Metern siehst du eine weitere Straße, die nachlinks führt. Auf einem Schild, das an der Ecke einer Taverneangebracht ist, steht \"Farisallee\".";

        // replace p with StringBuffer class
        // recap modulo (check result of0  MOD x and explain to Michael)
        // fix bug in Michaels Code

       // String p = "";
        StringBuffer stringBuffer = new StringBuffer();
        boolean newLine = false;

        for (int i = 1; i <= s.length(); i = i + 1) { // start at 1 to avoid 0 % LINE_LENGTH
            stringBuffer = stringBuffer.append(s.charAt(i - 1));
            if (i % LINE_LENGTH == 0) {
                newLine = true;
            }

            if (s.charAt(i - 1) == ' ' && newLine) {
                stringBuffer = stringBuffer.append("\n");
                newLine = false;
            }

        }


        //System.out.println(p);


        StringTokenizer tokenizer = new StringTokenizer(s, " ", false);
        String currentToken = null;
        String l = "";
        int cnt = 0;

        while (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();

            if (cnt + currentToken.length() > LINE_LENGTH) {
                l = l + "\n";
                cnt = 0;
            } else {
                cnt = cnt + currentToken.length();
            }

            l = l + currentToken + " ";
        }

        System.out.println(l);


//        System.out.println("20 / 5 = " + 20 / 5 + " REST 0");
//        System.out.println("21 / 5 = " + 21 / 5 + " REST 1");
//        System.out.println("22 / 5 = " + 22 % 5 + " REST 2");

        // 20 / 5 = 4 REST 0
        // 21 / 5 = 4 REST 1

    }
}
