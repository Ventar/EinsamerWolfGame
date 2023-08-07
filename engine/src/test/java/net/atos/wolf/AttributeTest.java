package net.atos.wolf;

import net.atos.wolf.services.character.Attribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeTest {

    @Test
    public void testConstructor() {
        Attribute attribute = new Attribute("Gold", 10, 5);
        Assertions.assertEquals("Gold", attribute.name());
        Assertions.assertEquals(10, attribute.maxValue());
        Assertions.assertEquals(5, attribute.baseValue());
    }

    @Test
    public void testConstructorIllegalValues() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute(null, 10, 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute("", 10, 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute("   ", 10, 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute("Gold", 0, 10);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Attribute("Gold", 5, 10);
        });
    }

    @Test
    public void testAdd() {
        Attribute gold = new Attribute("Gold", 20, 10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gold.add(-1);
        });

        Assertions.assertEquals(20, gold.add(21));

        Attribute endurance = new Attribute("Endurance", 10, 5);
        Assertions.assertEquals(1, endurance.add(1));
    }

    //
    @Test
    public void testRemoveAndGet() {
        Attribute attribute = new Attribute("Gold", 20, 10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            attribute.remove(-1);
        });

        Attribute attribute1 = new Attribute("Silver", 20, 5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            attribute1.remove(2);
        });


        Attribute attribute2 = new Attribute("Bronze", 10, 5);
        attribute2.add(5);
        Assertions.assertEquals(3, attribute2.remove(2));
        Assertions.assertEquals(3, attribute2.get());
    }

    @Test
    public void testCanRemove() {
        Attribute attribute = new Attribute("Gold", 10, 5);
        Assertions.assertEquals(false, attribute.canRemove(2));
        attribute.add(3);
        Assertions.assertEquals(true, attribute.canRemove(2));

    }
}
