package models;
import LinkedList.DoublyLinkedList;

/**
 *
 */
public class Good {
    private String description;
    private int weight;
    private int unitPrice;
    private int quantityToAdd;
    private String Temperature;
    private String photoURL;

    public Good(String description, int weight, int quantityToAdd, String temperature, String photoURL) {
        this.description = description;
        this.weight = weight;
        this.quantityToAdd = quantityToAdd;
        this.Temperature = temperature;
        this.photoURL = photoURL;
    }


}
