package net.atos.wolf;

import net.atos.wolf.data.BattleRoundTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleRoundTargetTest {


    @Test
    public void BattleRoundTargetTest(){

        BattleRoundTarget battleRoundTarget = new BattleRoundTarget();
        battleRoundTarget.min(2);
        battleRoundTarget.max(4);
        battleRoundTarget.targetSection(122);




        BattleRoundTarget copybattleRoundTarget = new BattleRoundTarget(battleRoundTarget);

        assertEquals(battleRoundTarget.targetSection(), copybattleRoundTarget.targetSection());
        assertEquals(battleRoundTarget.min(), copybattleRoundTarget.min());
        assertEquals(battleRoundTarget.max(), copybattleRoundTarget.max());




    }
}
