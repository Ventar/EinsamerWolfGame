package net.atos.wolf.services;

import net.atos.wolf.character.Character;
import net.atos.wolf.character.Enemy;
import net.atos.wolf.framework.Inject;
import net.atos.wolf.framework.Service;

/**
 * class dictates the battle section
 */
@Service
public class BattleService {

    //private DiceService diceService = new DiceService();
    @Inject
    private DiceService diceService;

    /**
     * calculates the battle quotient by comparing your endurance against the enemies
     * @param character
     * @param enemy
     */
    private void calculateBattleQuotient(Character character, Enemy enemy){

    }

    /**
     * executes battle segment
     * @param character
     * @param enemy
     */
    private void executeBattleRound(Character character, Enemy enemy){

    }

}
