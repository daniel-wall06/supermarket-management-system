package models;

import java.io.Serializable;

/**
 *
 */
public class Good implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;
    private double weight;
    private double unitPrice;
    private int quantityToAdd;
    private String Temperature;
    private String photoURL;

    public Good(String description, double weight, int quantityToAdd, String temperature, String photoURL, double unitPrice) {
        this.description = description;
        this.weight = weight;
        this.quantityToAdd = quantityToAdd;
        this.Temperature = temperature;
        this.photoURL = photoURL;
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getWeight() {
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
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getTotalPrice() {
        return unitPrice * quantityToAdd;
    }

    @Override
    public String toString() {
        return description + " (" + weight + "g/ml)" +
                " - " + quantityToAdd + " unit" + (quantityToAdd > 1 ? "s" : "") +
                " @ €" + String.format("%.2f", unitPrice) +
                " | Temp: " + Temperature +
                (photoURL != null && !photoURL.isEmpty() ? " | Image: " + photoURL : "");
    }

}
