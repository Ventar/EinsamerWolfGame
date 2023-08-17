package net.atos.wolf;

import net.atos.wolf.services.action.Battle;
import net.atos.wolf.services.action.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BattleTest {

    @Test
    public void testBattle() {
        Battle battle = new Battle();
        battle.enemy = new ArrayList<>();
        battle.enemy.add(new Enemy());
        battle.enemy.add(new Enemy());

        battle.targetSectionBattleRound();


        Assertions.assertEquals(battle.enemy, battle.enemy());
        Assertions.assertEquals(null, battle.targetSectionBattleRound());


    }
}

