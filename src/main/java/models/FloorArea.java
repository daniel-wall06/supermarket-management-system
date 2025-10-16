package models;

import LinkedList.DoublyLinkedList;
import LinkedList.Node;
import models.Aisle;

/**
 * Floor Area model of a supermarket that contains multiple aisles.
 */
public class FloorArea {
    private String title;
    private String level;
    private DoublyLinkedList<Aisle> aisles;

    /**
     * Constructs a new floor area with the specified title and level.
     * @param title name of floor area (e.g. Household, Fruit and Veg)
     * @param level the level of the floor area (e.g. Ground Floor)
     */
    public FloorArea(String title, String level) {
        this.title = title;
        this.level = level;
    }

    /**
     * Returns the title of the floor area.
     * @return the title of floor area
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the floor area.
     * @param title title of floor area
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the level of the floor area.
     * @return level of floor area
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the level of the floor area.
     * @param level level of the floor area
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Returns the linked list of aisles.
     * @return the linked list aisles
     */
    public DoublyLinkedList<Aisle> getAisles() {
        return aisles;
    }

    /**
     * Sets the current list of aisles
     * @param aisle new DoublyLinkedList of aisles to set
     */
    public void setAisles(DoublyLinkedList<Aisle> aisle) {
        this.aisles = aisle;
    }

    /**
     * Adds a new aisle to the end of the ailse list.
     * @param aisle the object Aisle to be added to the end of the list
     */
    public void addAisle(Aisle aisle) {
        aisles.insertAtTail(aisle);
    }


}
