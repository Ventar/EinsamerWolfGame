package net.atos.wolf.services;

import net.atos.wolf.character.Attribute;
import net.atos.wolf.character.Character;
import net.atos.wolf.character.Enemy;
import net.atos.wolf.framework.Service;


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


    public static void main(String[] args) {
        Character juli = new Character();
        Enemy enemy = new Enemy();

        juli.setEndurance(new Attribute("End", 15));
        enemy.setEndurance(new Attribute("END", 10));

        BattleService s = new BattleService();
        s.executeBattleRound(juli, enemy);

        //        System.out.println("END Char:"+ juli.getEndurance());
        //        System.out.println("END ENY:"+ enemy.getEndurance());


    }

    private DiceService diceService = new DiceService();


    /**
     * calculates the battle quotient by comparing your endurance against the enemies
     *
     * @param character
     * @param enemy
     */
    private BattleValue calculateBattleQuotient(Character character, Enemy enemy) {


        int battleQuotient = character.getEndurance().getValue() - enemy.getEndurance().getValue();


        //Zufallszähler basiert auf 0
        int rand = diceService.generate();
        BattleValue bv = null;

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
        BattleValue bv = calculateBattleQuotient(character, enemy);
        if (enemy.getEndurance().canRemove(bv.getF())) {
            enemy.getEndurance().remove(bv.getF() * -1);
        } else {
            return BattleStatus.ENEMY_DIED;
        }

        if (character.getEndurance().canRemove(bv.getEw() * -1) == true) {
            character.getEndurance().remove(bv.getEw() * -1);
        } else {

            //throw new CharacterDiedException("Sie sind gestorben");
            return BattleStatus.CHARACTER_DIED;
        }

        System.out.println(bv);
        System.out.println("After fight ENEMY:" + enemy.getEndurance());
        System.out.println("After fight CHARACTER:" + character.getEndurance());
        return BattleStatus.TIE;
    }

    private BattleStatus executeBattle(Character character, Enemy enemy) {

        while (true) {
            executeBattleRound(character, enemy);
        }

    }
}