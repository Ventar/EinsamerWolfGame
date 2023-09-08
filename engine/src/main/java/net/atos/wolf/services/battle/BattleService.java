package net.atos.wolf.services.battle;

import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.action.Enemy;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.KaiSkill;
import net.atos.wolf.services.character.Weapon;
import net.atos.wolf.services.common.DiceService;
import net.atos.wolf.services.session.GameSession;
import org.eclipse.jetty.server.session.Session;


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


    public BattleService() {

    }

    /**
     * Make a new instance of the DiceService
     */
    private DiceService diceService = new DiceService();

    /**
     * Checks if the Character has the right WeaponSkill for the current weapon
     *
     * @param character
     * @param skill
     * @param weapon
     * @return
     */
    private boolean checkApplyWeaponSkill(Character character, KaiSkill skill, Weapon weapon) {
        return character.hasSkill(skill) && (character.weaponOne() == weapon || character.weaponTwo() == weapon);
    }

    /**
     * Calculates the final BattleStrength for the character
     *
     * @param character
     * @param enemy
     * @return
     */
    private int calculateBattleStrength(GameSession gameSession, Enemy enemy) {
        LOG.trace("Calculate the battle strength character ::= [{}], enemy ::= [{}]...", gameSession.character(), enemy);
        int battleStrength = gameSession.character().battleStrength().get();
        boolean applyWeaponSkill = false;
        boolean rage = false;

        if (gameSession.character().hasSkill(KaiSkill.THOUGHT_RAY)) {
            if (!enemy.thoughRayResistance()) {
                gameSession.battleLog().add("Du setzt die Fähigkeit Gedankenstrahl ein (Kampfstärke + 2)");
                battleStrength = battleStrength + 2;
                LOG.trace("Character uses kai skill  thought ray");
            } else {
                gameSession.battleLog().add("Dein Gegner ist gegen deinen Gedankenstrahl immun...");
                LOG.trace("Character uses kai skill  thought ray but enemy has thought resistance...");
            }
        } else {
            LOG.trace("Character does not have the kai skill thought ray");
        }


        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_AXE, Weapon.AXE);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_SHORT_SWORD, Weapon.SHORT_SWORD);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_MACE, Weapon.MACE);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_BATTLE_STAFF, Weapon.BATTLE_STAFF);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_DAGGER, Weapon.DAGGER);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_BROAD_SWORD, Weapon.BROAD_SWORD);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_SPEAR, Weapon.SPEAR);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_WARHAMMER, Weapon.WARHAMMER);
        applyWeaponSkill = applyWeaponSkill || checkApplyWeaponSkill(gameSession.character(), KaiSkill.ARMORY_SWORD, Weapon.SWORD);


        if (applyWeaponSkill) {
            gameSession.battleLog().add("Du wendest den Skill Armory ein(Kampfstärke +2");
            battleStrength = battleStrength + 2;
            //System.out.println("Charakter setzt eine Waffenkunde Fähigkeit ein...");
            LOG.trace("Charakter setzt eine Waffenkunde Fähigkeit ein...::= [{}]");
        }


        // System.out.println("\nMod. Battle Strength  : " + battleStrength);
        LOG.trace("Mod.Battlestrength::= [{}]", battleStrength);

        return battleStrength;
    }

    /**
     * calculates the battle quotient by comparing your endurance against the enemies
     *
     * @param
     * @param
     */
    public BattleTable.BattleValue calculateBattleQuotient(GameSession gameSession, Enemy enemy) {


        int battleQuotient = calculateBattleStrength(gameSession, enemy) - enemy.battleStrength();


        // System.out.println("BATTLE QUOTIENT       : " + battleQuotient);

        gameSession.battleLog().add("Der Kampfquotient beträgt " + battleQuotient);

        gameSession.battleLog().add("Der Kampfquotient beträgt  " + battleQuotient);

        LOG.trace("Battlequotient ::= [{}]", battleQuotient);


        //Zufallszähler basiert auf 0
        int rand = diceService.generate();
        BattleTable.BattleValue bv = null;
        // System.out.println("DICE ROLL             : " + rand);
        gameSession.battleLog().add("Die gewürfelte Nummer ist  " + rand);
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
        //
        return bv;
    }

    /**
     * executes battle segment
     *
     * @param gameSession
     * @param enemy
     */
    public BattleStatus executeBattleRound(GameSession gameSession, Enemy enemy) {
        BattleTable.BattleValue bv = calculateBattleQuotient(gameSession, enemy);

        enemy.endurance(enemy.endurance() - bv.enemy() * -1);
        gameSession.character().endurance().remove(bv.character() * -1);

        BattleStatus status = BattleStatus.TIE;

        if (gameSession.character().endurance().get() <= 0) {
            status = BattleStatus.CHARACTER_DIED;
            gameSession.battleLog().add("Du bist Tot");
        } else if (enemy.endurance() <= 0) {
            status = BattleStatus.ENEMY_DIED;
            gameSession.battleLog().add("Der Gegner ist Tot");
        }

        //System.out.println("Battle Value          : " + bv);
        //System.out.println("After fight ENEMY     : " + enemy.endurance());
        //System.out.println("Result                : " + status + "\n");
        gameSession.battleLog().add("Kampf Ergebnis  " + status);
        LOG.debug("Results::= [{}]", bv, enemy.endurance(), status);

        return status;

    }

}