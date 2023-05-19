package net.atos.wolf.services.actions;

import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

@Setter
@Getter
public class ActionResult {

    private boolean dead;

    private int targetSection;

    private List<Action> actions;

    public boolean hasActions() {
        if (actions == null || actions.isEmpty()) {
            return false;
        }

        return true;
    }


}
