package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoodTest {
    private Good good;

    @BeforeEach
    void setUp() {
        good = new Good("Apple", 0.5, 10, "Room", "apple.jpg", 1.5);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Apple", good.getDescription());
        assertEquals(0.5, good.getWeight(), 0.01);
        assertEquals(10, good.getQuantityToAdd());
        assertEquals("Room", good.getTemperature());
        assertEquals("apple.jpg", good.getPhotoURL());
        assertEquals(1.5, good.getUnitPrice(), 0.01);
    }

    @Test
    void testSetters() {
        good.setDescription("Red Apple");
        good.setWeight(1);
        good.setQuantityToAdd(15);
        good.setTemperature("Refrigerated");
        good.setPhotoURL("red_apple.jpg");
        good.setUnitPrice(2.0);

        assertEquals("Red Apple", good.getDescription());
        assertEquals(1.0, good.getWeight(), 0.01);
        assertEquals(15, good.getQuantityToAdd());
        assertEquals("Refrigerated", good.getTemperature());
        assertEquals("red_apple.jpg", good.getPhotoURL());
        assertEquals(2.0, good.getUnitPrice(), 0.01);
    }

    @Test
    void testGetTotalPrice() {

        assertEquals(15.0, good.getTotalPrice(), 0.01);


        good.setQuantityToAdd(5);
        assertEquals(7.5, good.getTotalPrice(), 0.01);

        good.setQuantityToAdd(0);
        assertEquals(0.0, good.getTotalPrice(), 0.01);

        good.setQuantityToAdd(100);
        good.setUnitPrice(0.75);
        assertEquals(75.0, good.getTotalPrice(), 0.01);
    }

    @Test
    void testToString() {
        String result = good.toString();

        assertTrue(result.contains("Apple"));
        assertTrue(result.contains("0.5g/ml"));
        assertTrue(result.contains("10 units"));
        assertTrue(result.contains("@ €1.50"));
        assertTrue(result.contains("Temp: Room"));
        assertTrue(result.contains("Image: apple.jpg"));

        Good singleGood = new Good("Banana", 0.3, 1, "Room", "", 2.0);
        result = singleGood.toString();
        assertTrue(result.contains("1 unit"));
        assertFalse(result.contains("Image:"));
    }

    @Test
    void testToStringWithNoPhoto() {
        Good noPhotoGood = new Good("Orange", 0.4, 5, "Room", "", 1.2);
        String result = noPhotoGood.toString();

        assertTrue(result.contains("Orange"));
        assertTrue(result.contains("0.4g/ml"));
        assertTrue(result.contains("5 units"));
        assertTrue(result.contains("@ €1.20"));
        assertTrue(result.contains("Temp: Room"));
        assertFalse(result.contains("Image:")); // Should not show image section
    }


    @Test
    void testZeroPrice() {
        Good freeGood = new Good("Sample", 0.5, 10, "Room", "", 0.0);

        assertEquals(0.0, freeGood.getUnitPrice(), 0.01);
        assertEquals(0.0, freeGood.getTotalPrice(), 0.01);

        String result = freeGood.toString();
        assertTrue(result.contains("@ €0.00"));
    }

    @Test
    void testNegativeValues() {

        Good negativePriceGood = new Good("Clearance", 1.0, 5, "Room", "", -2.0);
        assertEquals(-2.0, negativePriceGood.getUnitPrice(), 0.01);
        assertEquals(-10.0, negativePriceGood.getTotalPrice(), 0.01);


        Good negativeQuantityGood = new Good("Return", 1.0, -3, "Room", "", 5.0);
        assertEquals(-3, negativeQuantityGood.getQuantityToAdd());
        assertEquals(-15.0, negativeQuantityGood.getTotalPrice(), 0.01);
    }

    @Test
    void testNullValues() {
        Good nullGood = new Good(null, 1.0, 5, null, null, 10.0);

        assertNull(nullGood.getDescription());
        assertNull(nullGood.getTemperature());
        assertNull(nullGood.getPhotoURL());
        assertEquals(1.0, nullGood.getWeight(), 0.01);
        assertEquals(5, nullGood.getQuantityToAdd());
        assertEquals(10.0, nullGood.getUnitPrice(), 0.01);

        String result = nullGood.toString();
        assertNotNull(result);
    }

    @Test
    void testEquality() {
        Good good1 = new Good("Apple", 0.5, 10, "Room", "apple.jpg", 1.5);
        Good good2 = new Good("Apple", 0.5, 10, "Room", "apple.jpg", 1.5);
        Good good3 = new Good("Banana", 0.3, 5, "Room", "", 2.0);

        assertNotSame(good1, good2);
        assertNotSame(good1, good3);

        Good good4 = new Good("apple", 0.5, 10, "Room", "", 1.5); // lowercase
        assertNotEquals("Apple", good4.getDescription()); // Different case

        Good good5 = new Good("Apple", 0.6, 10, "Room", "", 1.5);
        assertEquals("Apple", good5.getDescription());
        assertNotEquals(0.5, good5.getWeight(), 0.01);
    }
}