package net.atos.wolf;

import net.atos.wolf.services.common.DiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiceServiceTest {

    @Test
    public void testGenerate() {
        DiceService diceService = new DiceService();

        for (int i = 0; i < 100; i++) {
            int value = diceService.generate();
            Assertions.assertTrue(value >= 0);
            Assertions.assertTrue(value <= 9);
        }

    }


    @Test
    public void testGenerateMinMaxSuccess() {
        DiceService diceService = new DiceService();
        for (int i = 0; i < 100; i++) {
            int value = diceService.generate(7, 16);
            Assertions.assertTrue(value >= 7);
            Assertions.assertTrue(value <= 16);
        }

    }

    @Test
    public void testGenerateMinMax_MaxLargerThanMin() {
        DiceService diceService = new DiceService();

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            diceService.generate(1, 0);
        });

        Assertions.assertEquals("Max value must be larger than min value", thrown.getMessage());

    }


    @Test
    public void testGenerateMinMax_MinIsNegative() {
        DiceService diceService = new DiceService();

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            diceService.generate(-1, 0);
        });

        Assertions.assertEquals("Min value must be at least 0", thrown.getMessage());

    }

    @Test
    public void testGenerateMinMax_MAXIsToHigh() {
        DiceService diceService = new DiceService();

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            diceService.generate(1, 101);
        });

        Assertions.assertEquals("Max value must be less or equal to 100", thrown.getMessage());

    }


}
