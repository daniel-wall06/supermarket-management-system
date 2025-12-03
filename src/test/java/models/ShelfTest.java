package models;

import linkedlist.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {
    private Shelf shelf;
    private Aisle aisle;

    @BeforeEach
    void setUp() {
        aisle = new Aisle("Produce", 10, 5, "Room");
        shelf = new Shelf(1);
        shelf.setParentAisle(aisle);
    }

    @Test
    void testConstructor() {
        assertEquals(1, shelf.getShelfNumber());
        assertNotNull(shelf.getGoodsList());
        assertTrue(shelf.getGoodsList().isEmpty());
        assertEquals(aisle, shelf.getParentAisle());
    }

    @Test
    void testAddGood() {
        Good apple = new Good("Apple", 0.5, 10, "Room", "", 1.5);
        shelf.addGood(apple);

        assertEquals(1, shelf.getGoodsList().getSize());
        assertEquals(15.0, shelf.getTotalValue(), 0.01);
    }

    @Test
    void testAddQuantityToExistingGood() {
        Good apple1 = new Good("Apple", 0.5, 10, "Room", "", 1.5);
        Good apple2 = new Good("Apple", 0.5, 5, "Room", "", 1.5);

        shelf.addGood(apple1);
        shelf.addGood(apple2);

        assertEquals(1, shelf.getGoodsList().getSize());
        assertEquals(22.5, shelf.getTotalValue(), 0.01);
    }

    @Test
    void testRemoveGood() {
        Good apple = new Good("Apple", 0.5, 10, "Room", "", 1.5);
        Good banana = new Good("Banana", 0.3, 5, "Room", "", 2.0);

        shelf.addGood(apple);
        shelf.addGood(banana);
        assertEquals(2, shelf.getGoodsList().getSize());
        assertEquals(25.0, shelf.getTotalValue(), 0.01);

        shelf.removeGood(apple);
        assertEquals(1, shelf.getGoodsList().getSize());
        assertEquals(10.0, shelf.getTotalValue(), 0.01);
    }

    @Test
    void testRemoveNumOfGoods() {
        Good apple = new Good("Apple", 0.5, 10, "Room", "", 1.5);
        shelf.addGood(apple);

        shelf.removeNumOfGoods(apple, 3);
        assertEquals(1, shelf.getGoodsList().getSize());
        assertEquals(10.5, shelf.getTotalValue(), 0.01);


        shelf.removeNumOfGoods(apple, 7);
        assertTrue(shelf.getGoodsList().isEmpty());
        assertEquals(0.0, shelf.getTotalValue(), 0.01);
    }

    @Test
    void testGetTotalValue() {
        assertEquals(0.0, shelf.getTotalValue(), 0.01);

        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        shelf.addGood(new Good("Banana", 0.3, 5, "Room", "", 2.0));

        assertEquals(25.0, shelf.getTotalValue(), 0.01);
    }

    @Test
    void testFindGood() {
        Good apple = new Good("Apple", 0.5, 10, "Room", "", 1.5);
        Good banana = new Good("Banana", 0.3, 5, "Room", "", 2.0);

        shelf.addGood(apple);
        shelf.addGood(banana);

        Good found = shelf.findGood("Apple", 0.5);
        assertNotNull(found);
        assertEquals("Apple", found.getDescription());
        assertEquals(0.5, found.getWeight(), 0.01);

        Good notFound = shelf.findGood("Orange", 0.5);
        assertNull(notFound);
    }

    @Test
    void testResetGoods() {
        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        shelf.addGood(new Good("Banana", 0.3, 5, "Room", "", 2.0));

        assertEquals(2, shelf.getGoodsList().getSize());
        assertEquals(25.0, shelf.getTotalValue(), 0.01);

        shelf.resetGoods();
        assertTrue(shelf.getGoodsList().isEmpty());
        assertEquals(0.0, shelf.getTotalValue(), 0.01);
    }

    @Test
    void testToString() {
        String result = shelf.toString();
        assertTrue(result.contains("Shelf 1"));
        assertTrue(result.contains("0 goods"));
        assertTrue(result.contains("Total: €0.00"));

        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        result = shelf.toString();
        assertTrue(result.contains("1 goods"));
        assertTrue(result.contains("Total: €15.00"));
    }

    @Test
    void testAllShelfItems() {

        String result = shelf.allShelfItems();
        assertTrue(result.contains("No goods on the shelf"));
        assertTrue(result.contains("Shelf Number: 1"));
        assertTrue(result.contains("Total Goods: 0"));

        shelf.addGood(new Good("Apple", 0.5, 10, "Room", "", 1.5));
        shelf.addGood(new Good("Banana", 0.3, 5, "Room", "", 2.0));

        result = shelf.allShelfItems();
        assertTrue(result.contains("Shelf Number: 1"));
        assertTrue(result.contains("Total Goods: 2"));

    }
}