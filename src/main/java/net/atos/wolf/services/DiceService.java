package net.atos.wolf.services;

import net.atos.wolf.framework.Service;

import java.util.Random;

@Service
public class DiceService {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random number between 0 (inclusive) and 9 (inclusive).
     *
     * @return the gerneated number
     */
    public int generate() {
        int randomizer = RANDOM.nextInt(10);
        return randomizer;
    }


    /**
     * Generates a random number between min (inclusive) and max (inclusive) where max must be less or equal to 100.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @return the random number
     * @throws IllegalArgumentException if min < 0 or min > max  or max > 100
     */
    public int generate(int min, int max) {

        if (min < 0) {
            throw new IllegalArgumentException("Min value must be at least 0");
        }

        if (max > 100) {
            throw new IllegalArgumentException("Max value must be less or equal to 100");
        }

        if (max < min) {
            throw new IllegalArgumentException("Max value must be larger than min value");
        }

        int rdm = min + RANDOM.nextInt(max + 1 - min);
        return rdm;
    }


}
