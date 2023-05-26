package net.atos.wolf.services.action.handler;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;

@ActionHandler(ActionType.CHANGE_SECTION_IF_NUMBER_OF_SKILLS)
@Slf4j
public class ChangeSectionIfNumberOfSkillsHandler extends AbstractActionHandler {

    @Override
    protected boolean checkExecutable(Character character, Action action, boolean onlyAction) {
        LOG.debug("Check CHANGE_SECTION_IF_NUMBER_OF_SKILLS, number of skills == " + character.getNumberOfSkills());
        return character.getNumberOfSkills() >= 5;
    }

}
