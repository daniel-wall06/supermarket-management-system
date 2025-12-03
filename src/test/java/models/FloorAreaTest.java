package models;

import linkedlist.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloorAreaTest {
    private FloorArea floorArea;

    @BeforeEach
    void setUp() {
        floorArea = new FloorArea("Grocery", "Ground");
    }

    @Test
    void testConstructor() {
        assertEquals("Grocery", floorArea.getTitle());
        assertEquals("Ground", floorArea.getLevel());
        assertNotNull(floorArea.getAisles());
        assertTrue(floorArea.getAisles().isEmpty());
        assertEquals(0.0, floorArea.getTotalValue(), 0.01);
    }

    @Test
    void testSetters() {
        floorArea.setTitle("Produce Section");
        floorArea.setLevel("First Floor");

        assertEquals("Produce Section", floorArea.getTitle());
        assertEquals("First Floor", floorArea.getLevel());
    }

    @Test
    void testAddAisle() {
        Aisle aisle = new Aisle("Fruits", 10, 5, "Room");
        floorArea.addAisle(aisle);

        assertEquals(1, floorArea.getAisles().getSize());
        assertEquals(aisle, floorArea.getAisles().getHead().getValue());
        assertEquals(floorArea, aisle.getParentFloorArea());
    }

    @Test
    void testTotalValueWhenEmpty() {
        assertEquals(0.0, floorArea.getTotalValue(), 0.01);
    }

    @Test
    void testGetTotalValueWithAisles() {
        Aisle aisle1 = new Aisle("Fruits", 10, 5, "Room");
        Aisle aisle2 = new Aisle("Vegetables", 15, 6, "Room");

        Shelf shelf1 = new Shelf(1);
        shelf1.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        shelf1.addGood(new Good("Banana", 0.3, 5, "Room", "", 2.0));
        aisle1.addShelf(shelf1);

        Shelf shelf2 = new Shelf(1);
        shelf2.addGood(new Good("Carrot", 0.2, 12, "Room", "", 0.5));
        shelf2.addGood(new Good("Potato", 0.4, 10, "Room", "", 1.2));
        aisle2.addShelf(shelf2);

        floorArea.addAisle(aisle1);
        floorArea.addAisle(aisle2);

        assertEquals(43.0, floorArea.getTotalValue(), 0.01);
    }

    @Test
    void testUpdateTotalValue() {
        floorArea.updateTotalValue(50.0);
        floorArea.updateTotalValue(30.0);

        Aisle aisle = new Aisle("Test", 10, 5, "Room");
        Shelf shelf = new Shelf(1);
        shelf.addGood(new Good("Test", 1.0, 50, "Room", "", 1.0));
        aisle.addShelf(shelf);
        floorArea.addAisle(aisle);

        assertEquals(50.0, floorArea.getTotalValue(), 0.01);
    }

    @Test
    void testSetAisles() {
        DoublyLinkedList<Aisle> newAisles = new DoublyLinkedList<>();
        Aisle aisle1 = new Aisle("Fruits", 10, 5, "Room");
        Aisle aisle2 = new Aisle("Vegetables", 15, 6, "Room");

        newAisles.insertAtTail(aisle1);
        newAisles.insertAtTail(aisle2);

        floorArea.setAisles(newAisles);

        assertEquals(2, floorArea.getAisles().getSize());
        assertEquals(aisle1, floorArea.getAisles().getHead().getValue());
    }

    @Test
    void testAddFloorArea() {
        FloorArea subArea = new FloorArea("Dairy", "Section");
        floorArea.setFloorAreas(new DoublyLinkedList<>());
        floorArea.addFloorArea(subArea);

        assertEquals(1, floorArea.getFloorAreas().getSize());
        assertEquals(subArea, floorArea.getFloorAreas().getHead().getValue());
    }

    @Test
    void testToString() {
        String result = floorArea.toString();
        assertTrue(result.contains("Grocery"));
        assertTrue(result.contains("(Ground)"));
        assertTrue(result.contains("0 aisle(s)"));
        assertTrue(result.contains("Total Value: €0.00"));

        Aisle aisle = new Aisle("Fruits", 10, 5, "Room");
        Shelf shelf = new Shelf(1);
        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        aisle.addShelf(shelf);
        floorArea.addAisle(aisle);

        result = floorArea.toString();
        assertTrue(result.contains("1 aisle(s)"));
        assertTrue(result.contains("Total Value: €15.00"));
    }

    @Test
    void testTotalValue() {
        Aisle aisle1 = new Aisle("Fruits", 10, 5, "Room");
        Aisle aisle2 = new Aisle("Vegetables", 15, 6, "Room");

        Shelf shelf1 = new Shelf(1);
        shelf1.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        aisle1.addShelf(shelf1);

        Shelf shelf2 = new Shelf(1);
        shelf2.addGood(new Good("Carrot", 0.2, 20, "Room", "", 0.5));
        aisle2.addShelf(shelf2);

        floorArea.addAisle(aisle1);
        floorArea.addAisle(aisle2);

        double expectedTotal = 15.0 + 10.0;
        assertEquals(expectedTotal, floorArea.getTotalValue(), 0.01);

        String result = floorArea.toString();
        assertTrue(result.contains("Total Value: €25.00"));
    }
}