package net.atos.wolf.service;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.*;
import net.atos.wolf.data.Character;


/**
 * class dictates the battle section
 */
@Slf4j
public class BattleService {

    /**
     * The final result/status after a battle
     */
    public enum BattleStatus {
        /**
         * Character died the adventure ends at this point
         */
        CHARACTER_DIED,
        /**
         * The enemy died the adventure goes forward
         */
        ENEMY_DIED,
        /**
         * Nobody dies, the battle continues
         */
        TIE
    }

    /**
     * Make a new instance of the DiceService
     */
    private DiceService diceService = new DiceService();

    /**
     * Checks if the Character has the right WeaponSkill for the current weapon
     */
    private boolean checkApplyWeaponSkill(Character character, KaiSkill skill, String weaponId) {
        return character.skills().contains(skill) && (
                (character.weaponOne() != null && character.weaponOne().id().equals(weaponId)) ||
                        (character.weaponTwo() != null && character.weaponTwo().id().equals(weaponId)));
    }

    /**
     * Calculates the final BattleStrength for the character
     *
     * @param gameSession
     * @param enemy
     * @return
     */
    private int calculateBattleStrength(GameSession gameSession, Enemy enemy, BattleLogEntry entry) {
        LOG.trace("Calculate the battle strength character ::= [{}], enemy ::= [{}]...", gameSession.character(), enemy);

        int battleStrength = gameSession.character().battleStrength().get();
        boolean applyWeaponSkill = false;


        if (gameSession.character().skills().contains(KaiSkill.THOUGHT_RAY)) {
            if (!enemy.thoughRayResistance()) {
                LOG.trace("Character uses kai skill  thought ray");
                battleStrength = battleStrength + KaiSkill.THOUGHT_RAY.addBattleStrength();
                entry.characterUsesThoughtRay(true);
            } else {
                LOG.trace("Character uses kai skill  thought ray but enemy has thought resistance...");
                entry.enemyHasThoughRayResistance(true);
            }
        } else {
            LOG.trace("Character does not have the kai skill thought ray");
        }

        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_AXE, "AXE");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_SHORT_SWORD, "SHORT_SWORD");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_MACE, "MACE");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_BATTLE_STAFF, "BATTLE_STAFF");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_DAGGER, "DAGGER");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_BROAD_SWORD, "BROAD_SWORD");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_SPEAR, "SPEAR");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_WARHAMMER, "WARHAMMER");
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_SWORD, "SWORD");


        if (applyWeaponSkill) {
            battleStrength = battleStrength + 2;
            entry.characterApplyWeaponSkill(applyWeaponSkill);
            LOG.trace("Charakter setzt eine Waffenkunde Fähigkeit ein...::= [{}]");
        }


        // System.out.println("\nMod. Battle Strength  : " + battleStrength);
        LOG.trace("Mod.Battlestrength::= [{}]", battleStrength);
        entry.battleStrength(battleStrength);
        return battleStrength;
    }

    /**
     * calculates the battle quotient by comparing your endurance against the enemies
     *
     * @param
     * @param
     */
    private BattleTable.BattleValue calculateBattleQuotient(GameSession gameSession, Enemy enemy, BattleLogEntry entry) {

        int battleQuotient = calculateBattleStrength(gameSession, enemy, entry) - enemy.battleStrength();
        entry.battleQuotient(battleQuotient);


        LOG.trace("Battlequotient ::= [{}]", battleQuotient);

        //Zufallszähler basiert auf 0
        int rand = diceService.generate();
        entry.dice(rand);

        BattleTable.BattleValue bv = null;
        LOG.trace("Rolled Number ::= [{}]", rand);


        if (battleQuotient <= -11) {
            bv = BattleTable.get(0, rand);
        } else if (battleQuotient == -10 || battleQuotient == -9) {
            //System.out.println("Use -10/-9 with rand " + rand);
            bv = BattleTable.get(1, rand);
        } else if (battleQuotient == -8 || battleQuotient == -7) {
            bv = BattleTable.get(2, rand);
        } else if (battleQuotient == -6 || battleQuotient == -5) {
            bv = BattleTable.get(3, rand);
        } else if (battleQuotient == -4 || battleQuotient == -3) {
            bv = BattleTable.get(4, rand);
        } else if (battleQuotient == -2 || battleQuotient == -1) {
            bv = BattleTable.get(5, rand);
        } else if (battleQuotient == 0) {
            bv = BattleTable.get(6, rand);
        } else if (battleQuotient == 1 || battleQuotient == 2) {
            bv = BattleTable.get(7, rand);
        } else if (battleQuotient == 3 || battleQuotient == 4) {
            bv = BattleTable.get(8, rand);
        } else if (battleQuotient == 5 || battleQuotient == 6) {
            bv = BattleTable.get(9, rand);
        } else if (battleQuotient == 7 || battleQuotient == 8) {
            bv = BattleTable.get(10, rand);
        } else if (battleQuotient == 9 || battleQuotient == 10) {
            bv = BattleTable.get(11, rand);
        } else if (battleQuotient >= 11) {
            bv = BattleTable.get(12, rand);

        }
        entry.battleValue(bv);

        return bv;
    }

    /**
     * Executes a single battle round of the game. The result of the execution are recorded in a new {@link BattleLogEntry} that is added to the passed game
     * session so that the client can display detailed information about things that happened during the battle round.
     *
     * @param gameSession
     * @param enemy
     */
    public BattleStatus executeBattleRound(GameSession gameSession, Enemy enemy, BattleLogEntry entry) {


        BattleTable.BattleValue bv = calculateBattleQuotient(gameSession, enemy, entry);

        enemy.endurance(enemy.endurance() - bv.enemy() * -1);
        gameSession.character().endurance().remove(bv.character() * -1);
        entry.characterEndurance(gameSession.character().endurance().get());

        BattleStatus status = BattleStatus.TIE;

        if (gameSession.character().endurance().get() <= 0) {
            status = BattleStatus.CHARACTER_DIED;
        } else if (enemy.endurance() <= 0) {
            status = BattleStatus.ENEMY_DIED;
        }

        LOG.debug("Results::= [{}]", bv, enemy.endurance(), status);

        gameSession.battleRounds(gameSession.battleRounds() + 1);

        return status;

    }

}