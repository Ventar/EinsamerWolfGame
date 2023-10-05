package net.atos.wolf.service.gameengine.handler;

import net.atos.wolf.data.GameSession;
import net.atos.wolf.service.gameengine.AbstractActionHandler;
import net.atos.wolf.data.Action;
import net.atos.wolf.service.gameengine.ActionHandler;
import net.atos.wolf.service.gameengine.ActionType;
import net.atos.wolf.data.KaiSkill;
import net.atos.wolf.service.DiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ActionHandler(ActionType.GET_KAI_SKILL_AND_PICK_WEAPON)
public class GetKaiSkillAndPickWeaponHandler extends AbstractActionHandler {


    @Override
    protected boolean checkExecutable(GameSession session, Action action) {
        boolean checkExecute = session.character().skills().size() < 5;
        return checkExecute;
    }

    @Override
    public void handleAction(GameSession session, Action action) {

        List<KaiSkill> weaponSkills = registry().bookRepository().getWeaponKaiSkills();

        for (KaiSkill skill : session.character().skills()) {
            if (skill.weaponSkill()) {
                weaponSkills.remove(skill);
            }
        }

        int rdm = registry().dice().generate(0, weaponSkills.size() - 1);

        session.character().skills().add(weaponSkills.get(rdm));

        session.modifiedAnswerOptions(session.section().actions());

    }


}
