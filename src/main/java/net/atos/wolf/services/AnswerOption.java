package net.atos.wolf.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerOption {
    private String text = null;

    private int answer = 0;


    @Override
    public String toString() {
        return "AnswerOption{" +
                "text='" + text + '\'' +
                ", answer=" + answer +
                '}';
    }
}
