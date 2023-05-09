package net.atos.wolf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class Main {

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





        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(learn);
        System.out.println(json);

    }

    public static void generateSection332() throws JsonProcessingException {

        Section F = new Section();
        F.setSectionNumber(332);
        F.setText("""
                Du durchquerst den Wald, bis du an eine Stelle kommst, an der sich
                der Pfad in zwei Richtungen teilt.
                """);

        Action skill = new Action();
        skill.setTargetSection(96);
        skill.setMandatory(true);
        skill.setType(ActionType.CHANGE_SECTION_IF_SKILL);

        Action noSkill = new Action();
        noSkill.setTargetSection(197);
        noSkill.setMandatory(false);
        noSkill.setType(ActionType.CHANGE_SECTION);

        Action fewSkill = new Action();
        fewSkill.setTargetSection(380);
        fewSkill.setMandatory(false);
        fewSkill.setType(ActionType.CHANGE_SECTION);

        ObjectMapper maaper = new ObjectMapper();
        String jssohn = maaper.writerWithDefaultPrettyPrinter().writeValueAsString(F);
        System.out.println(jssohn);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(skill);
        System.out.println(json);

        ObjectMapper mapperr = new ObjectMapper();
        String jsonn = mapperr.writerWithDefaultPrettyPrinter().writeValueAsString(noSkill);
        System.out.println(jsonn);

        ObjectMapper maper = new ObjectMapper();
        String jsohn = maper.writerWithDefaultPrettyPrinter().writeValueAsString(fewSkill);
        System.out.println(jsohn);




    }

    public static void generateSection333() throws JsonProcessingException{

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





        Action A = new Action();
        A.setTargetSection(192);
        A.setMandatory(false);
        A.setType(ActionType.CHANGE_SECTION);

        Action B = new Action();
        B.setTargetSection(15);
        B.setMandatory(false);
        B.setType(ActionType.CHANGE_SECTION);


        ObjectMapper mapperr = new ObjectMapper();
        String jsonn = mapperr.writerWithDefaultPrettyPrinter().writeValueAsString(nolearn);
        System.out.println(jsonn);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(A);
        System.out.println(json);

        ObjectMapper maper = new ObjectMapper();
        String jsohn = maper.writerWithDefaultPrettyPrinter().writeValueAsString(B);
        System.out.println(jsohn);






    }

    public static void main(String[] args) throws JsonProcessingException {
        generateSection4();
        generateSection332();
        generateSection333();


    }

}
