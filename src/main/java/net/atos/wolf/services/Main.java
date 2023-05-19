package net.atos.wolf.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.List;

public class Main {


    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static void main(String[] args) throws Exception {
        SectionService sectionService = new SectionService("/ew1.json");
        UIService uiService = new UIService();

        int sectionToRender = 333;

        while (true) {

            Section section = sectionService.getSection(sectionToRender);
            List<AnswerOption> answerOptions = new LinkedList<>();

            int i = 1;
            for (Action action : section.getActions()) {
                AnswerOption answerOption = new AnswerOption();
                answerOption.setText(action.getText());
                answerOption.setAnswer(i++);
                answerOptions.add(answerOption);
            }


            AnswerOption answerOption = uiService.render(section.getText(), answerOptions);
            Action a = section.getActions().get(answerOption.getAnswer() - 1);

            System.out.println(a);

            switch (a.getType()) {
                case CHANGE_SECTION:
                    sectionToRender = a.getTargetSection();
                    break;
                case CHANGE_SECTION_IF_SKILL:
                    sectionToRender = a.getTargetSection();
                    break;
                case CHANGE_SECTION_IF_WEAPON:
                    sectionToRender = a.getTargetSection();
                    break;
            }
        }

    }
}



