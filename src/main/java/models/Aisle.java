package models;
import LinkedList.DoublyLinkedList;


public class Aisle {
    private String aisleName;
    private int length;
    private int width;
    private String temperature;
    private DoublyLinkedList<Shelf> shelves;


    public Aisle(String aisleName, int length, int width, String temperature, DoublyLinkedList<Shelf> shelves) {
        this.aisleName = aisleName;
        this.length = length;
        this.width = width;
        this.temperature = temperature;
        this.shelves = new DoublyLinkedList<>();
    }
    public String getAisleName() {
        return aisleName;
    }
    public void setAisleName(String aisleName) {
        this.aisleName = aisleName;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public DoublyLinkedList<Shelf> getShelves() {
        return shelves;
    }
    public void setShelves(DoublyLinkedList<Shelf> shelves) {
        this.shelves = shelves;
    }
    public void addShelf(Shelf shelf) {
        shelves.insertAtTail(shelf);
    }

}
