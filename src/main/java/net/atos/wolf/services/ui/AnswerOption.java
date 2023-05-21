package net.atos.wolf.services.ui;

import net.atos.wolf.services.action.Action;


public record AnswerOption(Action action, String text, int answerKey) {

}
