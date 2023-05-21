package net.atos.wolf.services.action;

import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.KaiSkill;

public class ChangeSectionIfSkillHandler extends AbstractActionHandler {

    @Override
    public boolean shouldRenderAnswerOption(Character character, Action action, boolean onlyAction) {

        KaiSkill skill = action.getSkill();

        if (character.getSkills().contains(skill)) {
            return true;
        }

        return false;
    }

}
