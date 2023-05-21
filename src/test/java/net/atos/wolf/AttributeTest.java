package net.atos.wolf;

import net.atos.wolf.services.character.Attribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeTest {

    @Test
    public void testConstructorName() {
        Attribute attribute = new Attribute("Gold");
        Assertions.assertEquals("Gold", attribute.getName());

    }

    @Test
    public void testConstructorNameNullOrEmptyOrBlank() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute("   ");
        });
    }

    @Test
    public void testAddValue() {
        Attribute attribute = new Attribute("Gold", 5);
        Assertions.assertEquals(5, attribute.getValue());
    }

    @Test
    public void testAddNegativeValue() {
        Attribute attribute = new Attribute("Gold");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            attribute.add(-1);
        });
    }

    @Test
    public void testRemoveValue() {
        Attribute attribute = new Attribute("Gold", 5);
        attribute.remove(2);
        Assertions.assertEquals(3, attribute.getValue());
    }

    @Test
    public void testRemoveNegativeValue() {
        Attribute attribute = new Attribute("Gold", 5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            attribute.remove(-2);

        });
    }

    @Test
    public void testRemoveResultLessThanZero() {
        Attribute attribute = new Attribute("Gold", 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            attribute.remove(6);

        });
    }

    @Test
    public void testCanRemove() {
        Attribute attribute = new Attribute("Gold", 5);
        Assertions.assertEquals(false, attribute.canRemove(6));
        Assertions.assertEquals(true, attribute.canRemove(4));
    }

    @Test
    public void testToString() {
        Attribute attribute = new Attribute("Gold", 5);

        System.out.println(attribute);
    }


}
