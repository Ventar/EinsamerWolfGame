package net.atos.wolf.services.actions;

import net.atos.wolf.character.Character;
import net.atos.wolf.character.KaiSkill;
import net.atos.wolf.services.Action;
import net.atos.wolf.services.ActionType;
import net.atos.wolf.services.AnswerOption;

import java.util.List;

public class ChangeSectionIfSkillHandler extends AbstractActionHandler {


    public ChangeSectionIfSkillHandler() {
        super(ActionType.CHANGE_SECTION_IF_SKILL);
    }


    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {

        KaiSkill skill = action.getSkill();

        if (character.getSkills().contains(skill)) {
            return true;
        }

        return false;
    }

}
