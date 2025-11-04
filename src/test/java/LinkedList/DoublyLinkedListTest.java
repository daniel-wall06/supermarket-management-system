package LinkedList;

import models.FloorArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @org.junit.jupiter.api.Test
    void insertAtHead() {
        DoublyLinkedList<FloorArea>  floorAreaLinkedList = new DoublyLinkedList<>();
        FloorArea dairy = new FloorArea("Dairy", "Ground Floor");
        FloorArea fruitAndVeg = new FloorArea("Fruit and Veg", "First Floor");

        floorAreaLinkedList.insertAtHead(dairy);
        assertFalse(floorAreaLinkedList.isEmpty());
        assertEquals(dairy, floorAreaLinkedList.getHead().getValue());

        floorAreaLinkedList.insertAtHead(fruitAndVeg);
        assertFalse(floorAreaLinkedList.isEmpty());
        assertEquals(fruitAndVeg, floorAreaLinkedList.getHead().getValue());
        assertNotEquals(dairy, floorAreaLinkedList.getHead().getValue());


    }

    @org.junit.jupiter.api.Test
    void insertAtTail() {
        DoublyLinkedList<FloorArea>  floorAreaLinkedList = new DoublyLinkedList<>();
        FloorArea dairy = new FloorArea("Dairy", "Ground Floor");
        FloorArea fruitAndVeg = new FloorArea("Fruit and Veg", "First Floor");

        floorAreaLinkedList.insertAtTail(dairy);
        assertFalse(floorAreaLinkedList.isEmpty());
        assertEquals(dairy, floorAreaLinkedList.getTail().getValue());

        floorAreaLinkedList.insertAtTail(fruitAndVeg);
        assertFalse(floorAreaLinkedList.isEmpty());
        assertEquals(fruitAndVeg, floorAreaLinkedList.getTail().getValue());
        assertNotEquals(dairy, floorAreaLinkedList.getTail().getValue());

    }

    @Test
    void isEmpty() {
        DoublyLinkedList<FloorArea>  floorAreaLinkedList = new DoublyLinkedList<>();
        FloorArea dairy = new FloorArea("Dairy", "Ground Floor");

        assertTrue(floorAreaLinkedList.isEmpty());

        floorAreaLinkedList.insertAtHead(dairy);
        assertFalse(floorAreaLinkedList.isEmpty());

    }

    @Test
    void singleNodeList() {
        DoublyLinkedList<FloorArea>  floorAreaLinkedList = new DoublyLinkedList<>();
        FloorArea dairy = new FloorArea("Dairy", "Ground Floor");

        floorAreaLinkedList.insertAtHead(dairy);

        assertEquals(dairy, floorAreaLinkedList.getHead().getValue());
        assertEquals(dairy, floorAreaLinkedList.getTail().getValue());
        assertSame(floorAreaLinkedList.getHead().getValue(), floorAreaLinkedList.getTail().getValue());
    }


    @Test
    void deleteHeadFromOneElementList() {
        DoublyLinkedList<FloorArea>  floorAreaLinkedList = new DoublyLinkedList<>();
        FloorArea dairy = new FloorArea("Dairy", "Ground Floor");


        floorAreaLinkedList.insertAtHead(dairy);
        floorAreaLinkedList.deleteByValue(dairy);
        assertNull(floorAreaLinkedList.getHead());
    }

    @Test
    void deleteNodeFromAnywhere(){
        DoublyLinkedList<FloorArea>  floorAreaLinkedList = new DoublyLinkedList<>();
        FloorArea dairy = new FloorArea("Dairy", "Ground Floor");
        FloorArea fruitAndVeg = new FloorArea("Fruit", "First Floor");
        FloorArea bread = new FloorArea("Bread", "Ground Floor");


        floorAreaLinkedList.insertAtHead(dairy);
        floorAreaLinkedList.insertAtHead(fruitAndVeg);
        floorAreaLinkedList.insertAtTail(bread);



        floorAreaLinkedList.deleteByValue(dairy);



    }
}