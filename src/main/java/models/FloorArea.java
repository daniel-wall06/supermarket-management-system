package models;

import LinkedList.DoublyLinkedList;
import LinkedList.Node;
import models.Aisle;

public class FloorArea {
    private String title;
    private String level;
    private DoublyLinkedList<Aisle> aisles;


    public FloorArea(String title, String level) {
        this.title = title;
        this.level = level;
        this.aisles = new DoublyLinkedList<>();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public DoublyLinkedList<Aisle> Aisle() {
        return aisles;
    }
    public void setAisle(DoublyLinkedList<Aisle> aisle) {
        this.aisles = aisle;
    }
    public void addAisle(Aisle aisle) {
        aisles.insertAtTail(aisle);
    }


}
