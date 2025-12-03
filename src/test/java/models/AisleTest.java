package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AisleTest {
    private Aisle aisle;

    @BeforeEach
    void setUp() {
        aisle = new Aisle("Produce", 10, 5, "Room");
    }

    @Test
    void testConstuctorAndGetters() {
        assertEquals("Produce", aisle.getAisleName());
        assertEquals(10, aisle.getLength());
        assertEquals(5, aisle.getWidth());
        assertEquals("Room", aisle.getTemperature());
        assertNotNull(aisle.getShelves());
        assertTrue(aisle.getShelves().isEmpty());
    }

    @Test
    void testSetters() {
        aisle.setAisleName("Dairy");
        aisle.setLength(15);
        aisle.setWidth(6);
        aisle.setTemperature("Refrigerated");

        assertEquals("Dairy", aisle.getAisleName());
        assertEquals(15, aisle.getLength());
        assertEquals(6, aisle.getWidth());
        assertEquals("Refrigerated", aisle.getTemperature());
    }

    @Test
    void testAddShelf() {
        Shelf shelf = new Shelf(1);
        aisle.addShelf(shelf);

        assertEquals(1, aisle.getShelves().getSize());
        assertEquals(shelf, aisle.getShelves().getHead().getValue());
        assertEquals(aisle, shelf.getParentAisle());
    }

    @Test
    void testGetValueOnEmptyAisle() {
        assertEquals(0.0, aisle.getTotalValue(), 0.01);
    }

    @Test
    void testGetTotalValueWithGoods() {
        Shelf shelf1 = new Shelf(1);
        Shelf shelf2 = new Shelf(2);

        shelf1.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        shelf2.addGood(new Good("Banana", 0.3, 5, "Room", "", 2.0));

        aisle.addShelf(shelf1);
        aisle.addShelf(shelf2);

        assertEquals(25.0, aisle.getTotalValue(), 0.01);
    }

    @Test
    void testUpdateTotalValue() {
        Shelf shelf = new Shelf(1);
        Good good = new Good("Test", 1.0, 10, "Room", "", 5.0);
        shelf.addGood(good);

        aisle.addShelf(shelf);
        assertEquals(50.0, aisle.getTotalValue(), 0.01);
    }

    @Test
    void testParentFloorArea() {
        FloorArea floorArea = new FloorArea("Grocery", "Ground");
        aisle.setParentFloorArea(floorArea);

        assertEquals(floorArea, aisle.getParentFloorArea());
        assertEquals("Grocery", aisle.getParentFloorArea().getTitle());
    }

    @Test
    void testResetShelves() {
        Shelf shelf = new Shelf(1);
        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        aisle.addShelf(shelf);

        assertEquals(15.0, aisle.getTotalValue(), 0.01);

        aisle.resetShelves();
        assertEquals(0.0, aisle.getTotalValue(), 0.01);
        assertEquals(1, aisle.getShelves().getSize()); // Shelf still exists, just empty
    }

    @Test
    void testToString() {
        String result = aisle.toString();
        assertTrue(result.contains("Produce"));
        assertTrue(result.contains("[Room]"));
        assertTrue(result.contains("0 shelf(s)"));
        assertTrue(result.contains("Total Value: €0.00"));


        Shelf shelf = new Shelf(1);
        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        aisle.addShelf(shelf);

        result = aisle.toString();
        assertTrue(result.contains("1 shelf(s)"));
        assertTrue(result.contains("Total Value: €15.00"));
    }

    @Test
    void testListAllShelves() {

        String result = aisle.listAllShelves();
        assertTrue(result.contains("No shelves on this aisle"));
        assertTrue(result.contains("Aisle Name: Produce"));
        assertTrue(result.contains("Total Shelves: 0"));


        Shelf shelf = new Shelf(1);
        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        aisle.addShelf(shelf);

        result = aisle.listAllShelves();
        assertTrue(result.contains("Total Shelves: 1"));
    }
}