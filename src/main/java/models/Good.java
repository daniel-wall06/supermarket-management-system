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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantityToAdd() {
        return quantityToAdd;
    }
    public void setQuantityToAdd(int quantityToAdd) {
        this.quantityToAdd = quantityToAdd;
    }
    public String getTemperature() {
        return Temperature;
    }
    public void setTemperature(String temperature) {
        Temperature = temperature;
    }
    public String getPhotoURL() {
        return photoURL;
    }
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    public int getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getTotalPrice() {
        return unitPrice * quantityToAdd;
    }

    @Override
    public String toString() {
        return "Good{" +
                "description='" + description + '\'' +
                ", weight=" + weight +
                ", unitPrice=" + unitPrice +
                ", quantityToAdd=" + quantityToAdd +
                ", Temperature='" + Temperature + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
