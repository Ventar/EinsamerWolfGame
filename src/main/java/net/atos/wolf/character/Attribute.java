package net.atos.wolf.character;

import lombok.Getter;
import lombok.Setter;

/**
 * Container class to manage the value of an attribute that is part of the {@link Character}.
 *
 * @author Noel Masur, Julius Reismann
 * @since 2023-05-04
 */
@Getter
public class Attribute {

    /**
     * The name of the managed attribute. At the moment we have gold, food, combatStrength and endurance. Can be used to identify the attribute during logging.
     */
    private String name = null;

    /**
     * The current value of the attribute.
     */
    private int value = 0;

    /**
     * Creates a new instance of this class
     *
     * @param name the name of the attibute that is managed
     * @throws IllegalArgumentException in case the passed name is {@code null} or empty / blank
     */
    public Attribute(String name) {

        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name of an attribute cannot be null or empty");
        }

        this.name = name;
    }

    /**
     * Creates new instance for value and name
     *
     * @param name
     * @param value
     */
    public Attribute(String name, int value) {
        this(name);
        add(value);
    }

    /**
     * Increases the managed value of the attribute by the passed number. Only positive values are allowed.
     *
     * @param addNumber the value to add
     * @return the new value
     * @throws IllegalArgumentException in case the passed value is negative
     */
    public int add(int addNumber) {

        if (addNumber < 0) {
            throw new IllegalArgumentException("The added value has to be positive for attribute ::= [" + name + "]");
        }

        value = value + addNumber;

        return value;

    }

    /**
     * Checks if action results in negative numbers, if numbers gets negative it gets removed
     *
     * @param removeNumber
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

    /**
     * Checks number, if the number is smaller than zero it gets removed
     *
     * @param removeNumber
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
                '}';
    }

    public static void main(String[] args) {

    }


}
