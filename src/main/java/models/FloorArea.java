package models;

import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import java.io.Serializable;

/**
 * Floor Area model of a supermarket that contains multiple aisles.
 */
public class FloorArea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String level;
    private DoublyLinkedList<Aisle> aisles;
    private DoublyLinkedList<FloorArea> floorAreas;
    private double totalValue = 0;

    /**
     * Constructs a new floor area with the specified title and level.
     * @param title name of floor area (e.g. Household, Fruit and Veg)
     * @param level the level of the floor area (e.g. Ground Floor)
     */
    public FloorArea(String title, String level) {
        this.title = title;
        this.level = level;
        this.aisles = new DoublyLinkedList<>();
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

    public DoublyLinkedList<FloorArea> getFloorAreas() {
        return floorAreas;
    }
    public void setFloorAreas(DoublyLinkedList<FloorArea> floorAreas) {
        this.floorAreas = floorAreas;
    }

    public double getTotalValue() {
        double total = 0.0;
        Node<Aisle> current = aisles.getHead();
        while (current != null) {
            total += current.getValue().getTotalValue();
            current = current.getNext();
        }
        return total;
    }
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
    public void updateTotalValue(double valueChange) {
        totalValue += valueChange;
    }


    public void addFloorArea(FloorArea floorArea) {
        floorAreas.insertAtTail(floorArea);
    }


    /**
     * Adds a new aisle to the end of the aisle list.
     * @param aisle the object Aisle to be added to the end of the list
     */
    public void addAisle(Aisle aisle) {
        aisle.setParentFloorArea(this);
        aisles.insertAtTail(aisle);
        updateTotalValue(aisle.getTotalValue());
    }

    @Override
    public String toString() {
        return title + " (" + level + ") - " + aisles.getSize() + " aisle(s), Total Value: €"
                + String.format("%.2f", totalValue);
    }




}
