package net.atos.wolf.services.action.handler;

import net.atos.wolf.services.action.*;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.ui.UIService;

import java.util.List;

@ActionHandler(ActionType.GET_KAI_SKILL_AND_PICK_WEAPON)
public class GetKaiSkillAndPickWeaponHandler extends AbstractActionHandler {

    DiceService dic = new DiceService();

    protected boolean checkExecutable(Character character, Action action, boolean onlyAction) {
        return character.getNumberOfSkills() < 5 && !character.hasSkill((KaiSkill.ARMORY_DAGGER)) &&
                !character.hasSkill((KaiSkill.ARMORY_SPEAR)) && !character.hasSkill((KaiSkill.ARMORY_MACE)) &&
                !character.hasSkill((KaiSkill.ARMORY_SHORT_SWORD)) && !character.hasSkill((KaiSkill.ARMORY_WARHAMMER))
                && !character.hasSkill((KaiSkill.ARMORY_AXE)) && !character.hasSkill((KaiSkill.ARMORY_BATTLE_STAFF))
                && !character.hasSkill((KaiSkill.ARMORY_BROAD_SWORD)) && !character.hasSkill((KaiSkill.ARMORY_SWORD));
    }

    public ActionResult handleAction(UIService ui, Character character, Action action, List<Action> answerOptions) {
        int i = dic.generate();
        switch (i) {
            case 0 -> character.addSkill(KaiSkill.ARMORY_DAGGER);
            case 1 -> character.addSkill(KaiSkill.ARMORY_SPEAR);
            case 2 -> character.addSkill(KaiSkill.ARMORY_MACE);
            case 3 -> character.addSkill(KaiSkill.ARMORY_SHORT_SWORD);
            case 4 -> character.addSkill(KaiSkill.ARMORY_WARHAMMER);
            case 5, 7 -> character.addSkill(KaiSkill.ARMORY_SWORD);
            case 6 -> character.addSkill(KaiSkill.ARMORY_AXE);
            case 8 -> character.addSkill(KaiSkill.ARMORY_BATTLE_STAFF);
            case 9 -> character.addSkill(KaiSkill.ARMORY_BROAD_SWORD);
            default -> throw new IllegalStateException("cannot select armory skill");
        }
        return ActionResult.representActions(answerOptions);
    }


}
