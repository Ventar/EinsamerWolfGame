package net.atos.wolf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.atos.wolf.character.KaiSkill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main {



    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static Section generateSection4() throws JsonProcessingException {
        Section section = new Section();
        section.setSectionNumber(4);
        section.setText("Du folgst dem Oberst, während er  sein  Pferd durch die Menschenmenge treibt und sich dabei wenig darum schert, ob ihm die Leute noch rechtzeitig ausweichen können. Dir ist es unmöglich,deinen sommerlendischen Landsleuten gegenüber ein  derartgewissenloses Verhalten an den Tag zu legen, und so hast du den rücksichtslosen Offizier schon bald aus den Augen verloren.Die  breite Prachtstraße, die  sich  am  anderen Ende  des  Platzesanschließt, ist ebenso bevölkert wie der Platz selbst, und dein Pferdist dir auch hier nicht von Vorteil, so dass du widerwillig beschließtabzusteigen und deinen Weg zu Fuß fortzusetzen.Nach etwa fünfzig Metern siehst du eine weitere Straße, die nachlinks  führt.  Auf  einem Schild, das  an  der  Ecke  einer  Taverneangebracht ist, steht \"Farisallee\".");
        section.addAction(289, false, ActionType.CHANGE_SECTION, "Biege in diese Seitenstraße ab", null);
        section.addAction(203, false, ActionType.CHANGE_SECTION, "Bleibe auf der Hauptstraße", null);

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

    public static void main(String[] args) throws JsonProcessingException {
        SectionService sectionService = new SectionService();
        UIService uiService = new UIService();


        sectionService.addSection(generateSection333());
        sectionService.addSection(generateSection332());
        sectionService.addSection(generateSection4());

        Section section = sectionService.getSection(333);
        List<AnswerOption> answerOptions = new LinkedList<>();

        int i = 1;
        for (Action action: section.getActions()) {
            AnswerOption answerOption = new AnswerOption();
            answerOption.setText(action.getText());
            answerOption.setAnswer(i++);
            answerOptions.add(answerOption);
        }

        uiService.render(section.getText(), answerOptions);
//        String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(sectionService.getSection(333));
//
//        System.out.println(json);

    }

}
