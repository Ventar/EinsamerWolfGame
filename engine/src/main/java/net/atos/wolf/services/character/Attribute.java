package net.atos.wolf.services.character;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Container class to manage the value of an attribute that is part of the {@link Character}.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-04
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute {

    /**
     * The name of the managed attribute. At the moment we have gold, food, combatStrength and endurance. Can be used to identify the attribute during logging.
     */
    @Getter
    private String name = null;

    /**
     * The current value of the attribute.
     */
    private int value = 0;

    @Getter
    @Setter
    private int maxValue = 0;

    @Getter
    @Setter
    private int baseValue = 0;

    private Attribute() {

    }

    /**
     * Creates new instance for value and name
     *
     * @param name
     * @param value
     */
    public Attribute(String name, int maxValue, int baseValue) {
        this.name = name;
        this.maxValue = maxValue;
        this.baseValue = baseValue;
    }

    /**
     * Increases the managed value of the attribute by the passed number. Only positive values are allowed.
     *
     * @param addNumber the value to add
     *
     * @return the new value
     *
     * @throws IllegalArgumentException in case the passed value is negative
     */
    public int add(int addNumber) {

        if (addNumber < 0) {
            throw new IllegalArgumentException("The added value has to be positive for attribute ::= [" + name + "]");
        }

        if (value + addNumber > maxValue) {
            value = maxValue;
        } else {
            value = value + addNumber;
        }

        return value;
    }

    /**
     * Checks if action results in negative numbers, if numbers gets negative it gets removed
     *
     * @param removeNumber
     *
     * @return
     */
    public int remove(int removeNumber) {

        if (removeNumber < 0) {
            throw new IllegalArgumentException("The removed value has to be positive for attribute ::= [" + name + "]");
        }

        if (value - removeNumber < 0) {
            throw new IllegalArgumentException("You cannot remove number ::= [" + removeNumber + "] from the current value of ::= [" + value + "] for attribute ::= [" + name + "]");
        }

        value = value - removeNumber;

        return value;
    }

    public int get() {
        return value;
    }

    /**
     * Checks number, if the number is smaller than zero it gets removed
     *
     * @param removeNumber
     *
     * @return true or false
     */
    public boolean canRemove(int removeNumber) {
        if (value - removeNumber < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Attribute{" +
                       "name='" + name + '\'' +
                       ", value=" + value +
                       ", maxValue=" + maxValue +
                       ", baseValue=" + baseValue +
                       '}';
    }
}
