package net.atos.wolf.services;

import lombok.Getter;
import lombok.Setter;


public record AnswerOption(Action action, String text, int answerKey) {

}
