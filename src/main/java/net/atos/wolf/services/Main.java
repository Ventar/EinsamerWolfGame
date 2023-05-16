package net.atos.wolf.services;

import ch.qos.logback.classic.net.SMTPAppender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.atos.wolf.character.KaiSkill;
import net.atos.wolf.character.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class Main {


    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static Section generateSection4() throws JsonProcessingException {
        Section section = new Section();
        section.setSectionNumber(4);
        section.setText("Du folgst dem Oberst, während er  sein  Pferd durch die Menschenmenge treibt und sich dabei wenig darum schert, ob ihm die Leute noch rechtzeitig ausweichen können. Dir ist es unmöglich,deinen sommerlendischen Landsleuten gegenüber ein  derartgewissenloses Verhalten an den Tag zu legen, und so hast du den rücksichtslosen Offizier schon bald aus den Augen verloren.Die  breite Prachtstraße, die  sich  am  anderen Ende  des  Platzesanschließt, ist ebenso bevölkert wie der Platz selbst, und dein Pferdist dir auch hier nicht von Vorteil, so dass du widerwillig beschließtabzusteigen und deinen Weg zu Fuß fortzusetzen.Nach etwa fünfzig Metern siehst du eine weitere Straße, die nachlinks  führt.  Auf  einem Schild, das  an  der  Ecke  einer  Taverneangebracht ist, steht \"Farisallee\".");
        section.addAction(289, false, ActionType.CHANGE_SECTION, "Biege in diese Seitenstraße ab", null, null);
        section.addAction(203, false, ActionType.CHANGE_SECTION, "Bleibe auf der Hauptstraße", null, null);

        return section;
    }

    public static Section generateSection332() throws JsonProcessingException {

        Section section = new Section();
        section.setSectionNumber(332);
        section.setText("Du durchquerst den Wald, bis du an eine Stelle kommst, an der sich der Pfad in zwei Richtungen teilt.");

        Action action1 = new Action();
        action1.setTargetSection(96);
        action1.setMandatory(true);
        action1.setType(ActionType.CHANGE_SECTION_IF_SKILL);
        action1.setSkill(KaiSkill.TRACKING);
        action1.setText("Glücklicherweise beherrst du die Fähigkeit Spurensuche");
        section.addAction(action1);

        Action action2 = new Action();
        action2.setTargetSection(197);
        action2.setMandatory(false);
        action2.setType(ActionType.CHANGE_SECTION);
        action1.setText("Gehe nach Süden");
        section.addAction(action2);

        Action action3 = new Action();
        action3.setTargetSection(380);
        action3.setMandatory(false);
        action3.setType(ActionType.CHANGE_SECTION);
        action1.setText("Gehe nach Osten");
        section.addAction(action3);

        return section;
    }

    public static Section generateSection333() throws JsonProcessingException {

        Section section = new Section();
        section.setSectionNumber(333);
        section.setText("Du öffnest die Tür und betrittst den Bogengang. Fünf deiner Mitkadetten eilen dir mit gezogenen Waffen entgegen, entschlossen, die Kammern der Kai-Meister zu verteidigen. Du trittst beiseite, um sie passieren zu lassen, und während sie an dir vorbeilaufen, informierst du sie noch über den Angriff, der gerade über der Klosterküche stattfindet. Sie danken dir für die Warnung und ziehen ihre Kapuzen über, während sie den Gang entlangeilen, den du gerade gekommen bist. Schließlich gelangst du zu einer Abzweigung, an der ein weiterer Korridor zur Waffenkammer führt. Direkt vor dir siehst du die offene Tür zu deinem Dormitorium.");
        section.addAction(new Action().setTargetSection(192).setMandatory(false).setType(ActionType.CHANGE_SECTION).setText("Betrete das Domitorium"));
        section.addAction(new Action().setTargetSection(15).setMandatory(false).setType(ActionType.CHANGE_SECTION).setText("Folge dem Korridor zur Rüstkammer"));

        return section;
    }

    public static Section generateSection192() throws JsonProcessingException {

        Section section = new Section();
        section.setSectionNumber(192);
        section.setText("Der Schlafsaal ist nun leer. Die Kai-Kadetten, die dir im Korridor\n" +
                "begegnet sind, waren die letzten Initianden auf dem Weg zu ihren\n" +
                "Gefechtsstationen. Du gehst zu deiner Pritsche und ziehst den\n" +
                "kleinen Lederkoffer hervor, der darunter verstaut ist. Er enthält\n" +
                "deine wenigen persönlichen Besitztümer.\n" +
                "Von deinen Büchern beschließt du keine mitzunehmen, da sie zu\n" +
                "schwer sind und dich vermutlich nur behindern würden. Aber du\n" +
                "nimmst die 10 Goldkronen, die du in der Zeit gespart hast, seit du\n" +
                "hier in der Abtei bist, und steckst sie in deine Tasche. (Notiere sie\n" +
                "auf deinem Aktionsblatt.)\n" +
                "Der einzige Gegenstand, den du sonst noch besitzt und der sich als\n" +
                "nützlich erweisen könnte, ist deine Axt. Zwar hast du sie bisher stets\n" +
                "nur für das Greel-Spiel oder zum Holzhacken benutzt, aber jetzt\n" +
                "könntest du sie möglicherweise dazu einsetzen, wofür sie eigentlich\n" +
                "gedacht war: für die Schlacht! (Wenn du die Axt mitnehmen willst,\n" +
                "vergiss nicht, sie als Waffe auf deinem Aktionsblatt einzutragen.\n" +
                "Falls du schon zwei Waffen besitzt, musst du eine davon ablegen.)\n" +
                "Nachdem du deinen Koffer geschlossen und ihn wieder unter die\n" +
                "Pritsche geschoben hast, blickst du dich noch einmal im\n" +
                "Dormitorium um – du hast das ungute Gefühl, dass es das allerletzte\n" +
                "Mal sein könnte. Die Geräusche der Schlacht werden nun immer\n" +
                "lauter und veranlassen dich, den Schlafsaal schleunigst zu verlassen\n" +
                "und dich durch den Korridor auf den Weg zur Rüstkammer der Abtei\n" +
                "zu machen.");
        section.addAction(new Action().setTargetSection(15).setMandatory(true).setType(ActionType.CHANGE_SECTION).setText("Lies Weiter").setWeapon(Weapon.AXE));


        return section;
    }

    public static Section generateSection15() throws JsonProcessingException {

        Section section = new Section();
        section.setSectionNumber(15);
        section.setText("Du näherst dich gerade einer Gangbiegung nach rechts, als du\n" +
                "gedämpften Kampflärm vernimmst, der aus nicht allzu weiter\n" +
                "Entfernung zu kommen scheint. Mit gezogener Waffe spähst du\n" +
                "vorsichtig um die Ecke, besorgt darüber, was dich dahinter erwarten\n" +
                "mag.\n" +
                "Am anderen Ende des Korridors, nahe der Tür zur Rüstkammer,\n" +
                "erblickst du einen deiner Freunde. Sein Name ist Schneefalke, und er\n" +
                "ist wie du ein Initiand. Zwar wurden die Wandfackeln bereits alle\n" +
                "gelöscht, so dass der Gang im Dunkeln liegt, aber dennoch erkennst\n" +
                "du ohne jeden Zweifel deinen Freund. Sein schneeweißes Albinohaar\n" +
                "ist im schwachen Licht der Morgendämmerung, das durch das\n" +
                "zerstörte Fenster in den Korridors fällt, gut zu sehen.\n" +
                "Schneefalke hat einen Giak-Krieger gepackt und ringt mit ihm. Die\n" +
                "regungslosen Körper zweier weiterer Eindringlinge liegen in der\n" +
                "Nähe, erschlagen von deinem tapferen Freund. Plötzlich holt der\n" +
                "Giak zu einem Schlag aus und streift Schneefalke mit dem\n" +
                "Schwertknauf am Kopf. Schneefalke taumelt zurück, und du siehst,\n" +
                "wie ihm das Blut von seiner Stirn in die Augen läuft und ihm die\n" +
                "Sicht raubt. Als er hilflos gegen die Tür der Rüstkammer prallt, stößt\n" +
                "der Giak-Soldat ein hämisches Lachen aus und hebt sein Schwert\n" +
                "zum tödlichen Hieb.");
        section.addAction(new Action().setTargetSection(166).setMandatory(false).setType(ActionType.CHANGE_SECTION_IF_WEAPON).setText("Wenn du eine Axt hast").setWeapon(Weapon.AXE));
        section.addAction(new Action().setTargetSection(477).setMandatory(false).setType(ActionType.CHANGE_SECTION).setText("Wenn du keine Axt hast"));


        return section;
    }

    public static void main(String[] args) throws Exception {
        SectionService sectionService = new SectionService();
        UIService uiService = new UIService();

        sectionService.addSection(generateSection15());
        sectionService.addSection(generateSection192());
        sectionService.addSection(generateSection333());
        sectionService.addSection(generateSection332());
        sectionService.addSection(generateSection4());

        List<Section> sections = sectionService.getSections();
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(sections));

        FileWriter writer = new FileWriter(new File("C:/Development/EinsamerWolfGame/src/main/resources/ew1.json"));
        writer.write(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(sections));
        writer.close();


        List<Section> sections1 = MAPPER.readValue(new File("C:/Development/EinsamerWolfGame/src/main/resources/ew1.json"), new TypeReference<List<Section>>() {
        });

        System.out.println(sections1);

//        Section section = new Section();
//        List<Section> sectionslist = new ArrayList<>();
//        sectionslist.add(generateSection4());
//        section.storeSections(sectionslist);
//        section.Filetoread();
    }


    // 1. Store section list in JSON file in C:\Development\EinsamerWolfGame\src\main\resources\lw1.data
    // 2. Load sections from that file and convert it to a list in Java
    // 3. print that list on the command line
    // FRAMEWORK: JACKSON
    // Gute Hilfe Baeldung

        /*

        Section section = sectionService.getSection(15);
        List<AnswerOption> answerOptions = new LinkedList<>();

        int i = 1;
        for (Action action : section.getActions()) {
            AnswerOption answerOption = new AnswerOption();
            answerOption.setText(action.getText());
            answerOption.setAnswer(i++);
            answerOptions.add(answerOption);
        }

        AnswerOption answerOption = uiService.render(section.getText(), answerOptions);
//        String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(sectionService.getSection(333));
//
        System.out.println(answerOption);

        Action a = section.getActions().get(answerOption.getAnswer() - 1);

        System.out.println(a);

        switch (a.getType()) {
            case CHANGE_SECTION: break;
            case CHANGE_SECTION_IF_SKILL: break;
            case CHANGE_SECTION_IF_WEAPON: break;
        }

*/


}
//    private static String filePath =


