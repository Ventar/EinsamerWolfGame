package net.atos.wolf.services.battle;

public class BattleTable {

    public record BattleValue(int enemy, int character) {

    }

    private static final int T = Integer.MIN_VALUE;

    private static BattleValue[][] TABLE_MATRIX = new BattleValue[][]{
            // @formatter:off
            {V(0, T), V(0, T), V(0, -8), V(0, -6), V(-1, -6), V(-2, -5), V(-3, -5), V(-4, -5), V(-5, -4), V(-6, -4), V(-7, -4), V(-8, -3), V(-9, -3)},
            {V(0, T), V(0, -8), V(0, -7), V(-1, -6), V(-2, -5), V(-3, -5), V(-4, -4), V(-5, -4), V(-6, -3), V(-7, -3), V(-8, -3), V(-9, -3), V(-10, -2),},
            {V(0, -8), V(0, -7), V(-1, -6), V(-2, -5), V(-3, -5), V(-4, -4), V(-5, -4), V(-6, -3), V(-7, -3), V(-8, -3), V(-9, -2), V(-10, -2), V(-11, -2)},
            {V(0, -8), V(0, -7), V(-1, -6), V(-2, -5), V(-3, -5), V(-4, -4), V(-5, -4), V(-6, -3), V(-7, -3), V(-8, -3), V(-9, -2), V(-10, -2), V(-11, -2)},
            {V(1, -7), V(-1, -7), V(-2, -6), V(-3, -5), V(-4, -4), V(-5, -4), V(-6, -3), V(-7, -3), V(-8, -2), V(-9, -2), V(-10, -2), V(-11, -2), V(-12, -2)},
            {V(-2, -6), V(-3, -6), V(-4, -5), V(-5, -4), V(-6, -3), V(-7, -2), V(-8, -2), V(-9, -2), V(-10, -2), V(-11, -1), V(-12, -1), V(-14, -1), V(-16, -1)},
            {V(-3, -5), V(-4, -5), V(-5, -4), V(-6, -3), V(-7, -2), V(-8, -2), V(-9, -1), V(-10, -1), V(-11, -1), V(-12, 0), V(-14, 0), V(-16, 0), V(-18, 0)},
            {V(-4, -4), V(-5, -4), V(-6, -3), V(-7, -2), V(-8, -1), V(-9, -1), V(-10, 0), V(-11, 0), V(-12, 0), V(-14, 0), V(-16, 0), V(-18, 0), V(T, 0)},
            {V(-5, -3), V(-6, -3), V(-7, -2), V(-8, 0), V(-9, 0), V(-10, 0), V(-11, 0), V(-12, 0), V(-14, 0), V(-16, 0), V(-18, 0), V(T, 0), V(T, 0)},
            {V(-6, 0), V(-7, 0), V(-8, 0), V(-9, 0), V(-10, 0), V(-11, 0), V(-12, 0), V(-14, 0), V(-16, 0), V(-18, 0), V(T, 0), V(T, 0), V(T, 0)},
            // @formatter:on
    };

    public static BattleValue[][] getTableMatrix() {
        return TABLE_MATRIX;
    }

    /**
     * Creates a new battle value to add it to the table that is used to calculate the battle quotient.
     *
     * @param enemy     the modifier for the enemy
     * @param character the modifier for the character
     */
    private static BattleValue V(int enemy, int character) {
        return new BattleValue(enemy, character);
    }

    public static BattleValue get(int column, int row) {
        return TABLE_MATRIX[row][column];
    }


}
