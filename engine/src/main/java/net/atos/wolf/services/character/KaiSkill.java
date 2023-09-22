package net.atos.wolf.services.character;

import lombok.Getter;

public enum KaiSkill {
    
    CAMOUFLAGE,
    HUNT,
    SIXTH_SENSE,
    TRACKING,
    HEAL,
    ARMORY,
    ARMORY_DAGGER,
    ARMORY_SPEAR,
    ARMORY_MACE,
    ARMORY_SHORT_SWORD,
    ARMORY_WARHAMMER,
    ARMORY_SWORD,
    ARMORY_AXE,
    ARMORY_BATTLE_STAFF,
    ARMORY_BROAD_SWORD,
    MENTAL_DEFENCE,
    THOUGHT_RAY(4),
    ANIMAL_UNDERSTANDING,
    MIND_OVER_MATTER,
    RAGE;
    
    @Getter
    private int addBattleStrength = 0;
    
    KaiSkill() {
    
    }
    
    KaiSkill(int battleStrength) {
        this.addBattleStrength = battleStrength;
    }
    
    
}
