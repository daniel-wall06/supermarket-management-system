package persistence;

import linkedlist.DoublyLinkedList;
import models.FloorArea;
import models.Aisle;
import models.Shelf;

public class LoadedData {
    public DoublyLinkedList<FloorArea> floorAreas = new DoublyLinkedList<>();
    public DoublyLinkedList<Aisle> aisles = new DoublyLinkedList<>();
    public DoublyLinkedList<Shelf> shelves = new DoublyLinkedList<>();

    public boolean hasData() {
        return floorAreas.getHead() != null;
    }
}