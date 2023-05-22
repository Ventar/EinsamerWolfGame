package net.atos.wolf.services.battle;

import net.atos.wolf.framework.Service;
import net.atos.wolf.services.character.Character;
import net.atos.wolf.services.character.Enemy;
import net.atos.wolf.services.common.DiceService;


/**
 * class dictates the battle section
 */
@Service
public class BattleService {

    public enum BattleStatus {
        CHARACTER_DIED,
        ENEMY_DIED,
        TIE
    }


    public BattleService() {

    }

    private DiceService diceService = new DiceService();


    /**
     * calculates the battle quotient by comparing your endurance against the enemies
     *
     * @param character
     * @param enemy
     */
    private BattleTable.BattleValue calculateBattleQuotient(Character character, Enemy enemy) {


        int battleQuotient = character.endurance().get() - enemy.endurance().get();


        //Zufallszähler basiert auf 0
        int rand = diceService.generate();
        BattleTable.BattleValue bv = null;

        System.out.println(battleQuotient);
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
     * @param character
     * @param enemy
     */
    private BattleStatus executeBattleRound(Character character, Enemy enemy) {
        BattleTable.BattleValue bv = calculateBattleQuotient(character, enemy);
        if (enemy.endurance().canRemove(bv.enemy())) {
            enemy.endurance().remove(bv.enemy() * -1);
        } else {
            return BattleStatus.ENEMY_DIED;
        }

        if (character.endurance().canRemove(bv.character() * -1) == true) {
            character.endurance().remove(bv.character() * -1);
        } else {

            //throw new CharacterDiedException("Sie sind gestorben");
            return BattleStatus.CHARACTER_DIED;
        }

        System.out.println(bv);
        System.out.println("After fight ENEMY:" + enemy.endurance());
        System.out.println("After fight CHARACTER:" + character.endurance());
        return BattleStatus.TIE;
    }

    private BattleStatus executeBattle(Character character, Enemy enemy) {

        while (true) {
            executeBattleRound(character, enemy);
        }

    }
}