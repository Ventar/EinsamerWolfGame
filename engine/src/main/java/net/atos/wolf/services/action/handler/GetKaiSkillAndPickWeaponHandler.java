package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.action.AbstractActionHandler;
import net.atos.wolf.services.action.Action;
import net.atos.wolf.services.action.ActionHandler;
import net.atos.wolf.services.action.ActionType;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.common.DiceService;

@ActionHandler(ActionType.GET_KAI_SKILL_AND_PICK_WEAPON)
public class GetKaiSkillAndPickWeaponHandler extends AbstractActionHandler {

    DiceService dic = new DiceService();

    @Override
    protected boolean checkExecutable(GameSession session, Action action) {
        return session.character().getNumberOfSkills() < 5 && !session.character().hasSkill((KaiSkill.ARMORY_DAGGER)) &&
                       !session.character().hasSkill((KaiSkill.ARMORY_SPEAR)) && !session.character().hasSkill((KaiSkill.ARMORY_MACE)) &&
                       !session.character().hasSkill(KaiSkill.ARMORY_SHORT_SWORD) && !session.character().hasSkill(KaiSkill.ARMORY_WARHAMMER)
                       && !session.character().hasSkill(KaiSkill.ARMORY_AXE) && !session.character().hasSkill(KaiSkill.ARMORY_BATTLE_STAFF)
                       && !session.character().hasSkill(KaiSkill.ARMORY_BROAD_SWORD) && !session.character().hasSkill(KaiSkill.ARMORY_SWORD);
    }

    @Override
    public void handleAction(GameSession session, Action action) {

        switch (generate()) {
            case 0 -> session.character().addSkill(KaiSkill.ARMORY_DAGGER);
            case 1 -> session.character().addSkill(KaiSkill.ARMORY_SPEAR);
            case 2 -> session.character().addSkill(KaiSkill.ARMORY_MACE);
            case 3 -> session.character().addSkill(KaiSkill.ARMORY_SHORT_SWORD);
            case 4 -> session.character().addSkill(KaiSkill.ARMORY_WARHAMMER);
            case 5, 7 -> session.character().addSkill(KaiSkill.ARMORY_SWORD);
            case 6 -> session.character().addSkill(KaiSkill.ARMORY_AXE);
            case 8 -> session.character().addSkill(KaiSkill.ARMORY_BATTLE_STAFF);
            case 9 -> session.character().addSkill(KaiSkill.ARMORY_BROAD_SWORD);
            default -> throw new IllegalStateException("cannot select armory skill");
        }

        session.modifiedAnswerOptions(session.section().actions());

    }


}
