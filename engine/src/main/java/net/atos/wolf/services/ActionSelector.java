package net.atos.wolf.services;

import net.atos.wolf.services.action.Action;

import java.util.List;

public interface ActionSelector {

    Action selectAction(String text, String headLine, List<Action> options);

}
