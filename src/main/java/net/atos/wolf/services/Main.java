package net.atos.wolf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.atos.wolf.character.KaiSkill;

import java.util.ArrayList;

public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static void generateSection4() throws JsonProcessingException {
        Section learn = new Section();
        learn.setSectionNumber(4);
        learn.setText("""
                Du folgst dem Oberst, während er  sein  Pferd durch die Menschenmenge treibt und sich dabei wenig darum schert, 
                ob ihm die Leute noch rechtzeitig ausweichen können. Dir ist es unmöglich,deinen sommerlendischen Landsleuten
                gegenüber ein  derartgewissenloses Verhalten an den Tag zu legen, und so hast du den rücksichtslosen Offizier
                schon bald aus den Augen verloren.Die  breite Prachtstraße, die  sich  am  anderen Ende  des  Platzesanschließt,
                ist ebenso bevölkert wie der Platz selbst, und dein Pferdist dir auch hier nicht von Vorteil, so dass du 
                widerwillig beschließtabzusteigen und deinen Weg zu Fuß fortzusetzen.Nach etwa fünfzig Metern siehst du eine
                weitere Straße, die nachlinks  führt.  Auf  einem Schild, das  an  der  Ecke  einer  Taverneangebracht ist,
                steht „Farisallee“.
                            
                Wenn du in diese Seitenstraße abbiegen willst,
                lies weiter bei 289.
                Willst du lieber auf der Hauptstraße bleiben, lies weiter bei 203
                """);

        Action come = new Action();
        come.setTargetSection(289);
        come.setMandatory(false);
        come.setType(ActionType.CHANGE_SECTION);

        Action go = new Action();
        go.setTargetSection(203);
        go.setMandatory(false);
        go.setType(ActionType.CHANGE_SECTION);

        ArrayList<Action> things = new ArrayList<>();
        things.add(come);
        things.add(go);
        learn.setActions(things);

        String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(learn);
        System.out.println(json);

    }

    public static void generateSection332() throws JsonProcessingException {

        Section F = new Section();
        F.setSectionNumber(332);
        F.setText("""
                Du durchquerst den Wald, bis du an eine Stelle kommst, an der sich
                der Pfad in zwei Richtungen teilt.
                                
                Wenn du die Kai-Disziplin Spurensuche beherrschst, lies weiter bei 96.
                                
                Besitzt du diese Fertigkeit nicht, kannst du entweder nach Süden
                gehen (lies weiter bei 197) oder dich nach Osten wenden (lies
                weiter bei 380).
                """);

        Action action1 = new Action();
        action1.setTargetSection(96);
        action1.setMandatory(true);
        action1.setType(ActionType.CHANGE_SECTION_IF_SKILL);
        action1.setSkill(KaiSkill.TRACKING);

        Action action2 = new Action();
        action2.setTargetSection(197);
        action2.setMandatory(false);
        action2.setType(ActionType.CHANGE_SECTION);

        Action action3 = new Action();
        action3.setTargetSection(380);
        action3.setMandatory(false);
        action3.setType(ActionType.CHANGE_SECTION);

        ArrayList<Action> thighs = new ArrayList<>();
        thighs.add(action1);
        thighs.add(action2);
        thighs.add(action3);
        F.setActions(thighs);


        String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(F);
        System.out.println(json);

//        json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(action1);
//        System.out.println(json);
//
//        json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(action2);
//        System.out.println(json);
//
//        json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(action3);
//        System.out.println(json);


    }

    public static void generateSection333() throws JsonProcessingException {

        Section nolearn = new Section();
        nolearn.setSectionNumber(333);
        nolearn.setText("""
                Du öffnest die Tür und betrittst den Bogengang. Fünf deiner
                Mitkadetten eilen dir mit gezogenen Waffen entgegen, entschlossen,
                die Kammern der Kai-Meister zu verteidigen. Du trittst beiseite, um
                sie passieren zu lassen, und während sie an dir vorbeilaufen,
                informierst du sie noch über den Angriff, der gerade über der
                Klosterküche stattfindet. Sie danken dir für die Warnung und ziehen
                ihre Kapuzen über, während sie den Gang entlangeilen, den du
                gerade gekommen bist.
                Schließlich gelangst du zu einer Abzweigung, an der ein weiterer
                Korridor zur Waffenkammer führt. Direkt vor dir siehst du die offene
                Tür zu deinem Dormitorium.""");


        Action a = new Action();
        a.setTargetSection(192);
        a.setMandatory(false);
        a.setType(ActionType.CHANGE_SECTION);

        Action b = new Action();
        b.setTargetSection(15);
        b.setMandatory(false);
        b.setType(ActionType.CHANGE_SECTION);

        ArrayList<Action> stuff = new ArrayList<>();
        stuff.add(a);
        stuff.add(b);
        nolearn.setActions(stuff);



        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(nolearn);
        System.out.println(json);


    }

    public static void main(String[] args) throws JsonProcessingException {
        //generateSection4();
        generateSection332();
        generateSection333();


    }

}
