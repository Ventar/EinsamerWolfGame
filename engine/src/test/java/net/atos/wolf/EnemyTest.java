package net.atos.wolf;

import net.atos.wolf.services.action.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class EnemyTest {

    @Test
    public void testEnemyCopyConstructor() {
        // Create a sample Enemy object for testing
        Enemy enemy = new Enemy();
        enemy.name("Zwerg");
        enemy.battleStrength(10);
        enemy.endurance(15);
        enemy.thoughRayResistance(false);

        Enemy copiedEnemy = new Enemy(enemy);


        assertEquals(enemy.name(), copiedEnemy.name());
        assertEquals(enemy.battleStrength(), copiedEnemy.battleStrength());
        assertEquals(enemy.battleStrength(), copiedEnemy.battleStrength());
        assertEquals(enemy.thoughRayResistance(), copiedEnemy.thoughRayResistance());
    }

}
