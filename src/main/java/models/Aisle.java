package models;
import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import java.io.Serializable;

/**
 * Aisle model which is stored inside a floor area and contains a list of shelves.
 */
public class Aisle implements Serializable{
    private static final long serialVersionUID = 1L;
    private String aisleName;
    private int length;
    private int width;
    private String temperature;
    private DoublyLinkedList<Shelf> shelves;
    private double totalValue = 0;
    private FloorArea parentFloorArea;

    /**
     * Constructs an aisle with a name, dimensions, and temperature.
     * @param aisleName name of the aisle (e.g. Cheese/Bread)
     * @param length length of the aisle
     * @param width width of the aisle
     * @param temperature temperature of the aisle (e.g. Refrigerated, Frozen)
     */
    public Aisle(String aisleName, int length, int width, String temperature) {
        this.aisleName = aisleName;
        this.length = length;
        this.width = width;
        this.temperature = temperature;
        this.shelves = new DoublyLinkedList<>();


    }

    public Aisle(String trim, int length) {
    }

    /**
     * Returns the name of the current aisle.
     * @return name of aisle
     */
    public String getAisleName() {
        return aisleName;
    }

    /**
     * Sets the current aisle name.
     * @param aisleName aisle name to be used
     */
    public void setAisleName(String aisleName) {
        this.aisleName = aisleName;
    }

    /**
     * Return the length of the current aisle.
     * @return length of the current aisle
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the current aisle length.
     * @param length new length of aisle
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns the width of the current aisle.
     * @return width of the current aisle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the current aisle width
     * @param width new width of aisle
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the current aisle temperature.
     * @return temperature of current aisle (e.g. Refrigerated, Unrefrigerated, Frozen)
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Sets the current aisle temperature.
     * @param temperature new temperature of aisle
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * Returns the linked list of shelves.
     * @return the linked list of shelves
     */
    public DoublyLinkedList<Shelf> getShelves() {
        return shelves;
    }

    /**
     * Sets the current list of shelves.
     * @param shelves new DoublyLinkedList of shelves
     */
    public void setShelves(DoublyLinkedList<Shelf> shelves) {
        this.shelves = shelves;
    }

    public void setParentFloorArea(FloorArea floorArea) {
        this.parentFloorArea = floorArea;
    }
    public FloorArea getParentFloorArea() {
        return parentFloorArea;
    }

    /**
     * Add a new shelf to the end of the shelves linked list.
     * @param shelf the shelf to be added to the list.
     */
    public void addShelf(Shelf shelf) {
        shelf.setParentAisle(this);
        shelves.insertAtTail(shelf);
        updateTotalValue(shelf.getTotalValue());
    }
    public String listAllShelves(){
    String result = "";
        if(shelves != null && !shelves.isEmpty()) {
            Node<Shelf> current = shelves.getHead();
            while (current != null) {
                Shelf shelf = current.getValue();
                result += shelf.allShelfItems() + "\n";
                current = current.getNext();
            }
        }
        else{
            result += "No shelves on this aisle. \n";
        }
        result += "Aisle Name: " + aisleName
                + " | Total Shelves: " + shelves.getSize()
                + "\n";

        return result;
    }
    public void resetShelves() {
        Node<Shelf> current = shelves.getHead();
        while (current != null) {
            current.getValue().resetGoods();
            current = current.getNext();
        }
        totalValue = 0;
    }
    public void updateTotalValue(double valueChange) {
        totalValue += valueChange;
        if (parentFloorArea != null) {
            parentFloorArea.updateTotalValue(valueChange); // propagate up
        }
    }

    @Override
    public String toString() {
        return aisleName + " [" + temperature + "] - " + shelves.getSize() + " shelf(s), Total Value: €"
                + String.format("%.2f", totalValue);
    }


    public double getTotalValue() {
        double total = 0.0;
        Node<Shelf> current = shelves.getHead();
        while (current != null) {
            total += current.getValue().getTotalValue();
            current = current.getNext();
        }
        return total;
    }
}
