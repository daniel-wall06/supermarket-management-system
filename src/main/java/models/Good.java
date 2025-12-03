package models;

import java.io.Serializable;

/**
 *Good model of a good inside a shelf
 */
public class Good   {
    private String description;
    private double weight;
    private double unitPrice;
    private int quantityToAdd;
    private String Temperature;
    private String photoURL;

    /**
     * Constructs a new good with the specified fields
     * @param description description of the good
     * @param weight the weight of the good
     * @param quantityToAdd number of goods to be added to the shelf
     * @param temperature the temperature the good should be stored
     * @param photoURL url of image of the good
     * @param unitPrice price of the good
     */
    public Good(String description, double weight, int quantityToAdd, String temperature, String photoURL, double unitPrice) {
        this.description = description;
        this.weight = weight;
        this.quantityToAdd = quantityToAdd;
        this.Temperature = temperature;
        this.photoURL = photoURL;
        this.unitPrice = unitPrice;
    }

    /**
     * Getters and Setters for all fields
     */
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

    /**
     * ToString using the good fields
     * @return string of the good
     */
    @Override
    public String toString() {
        return description + " (" + weight + "g/ml)" +
                " - " + quantityToAdd + " unit" + (quantityToAdd > 1 ? "s" : "") +
                " @ €" + String.format("%.2f", unitPrice) +
                " | Temp: " + Temperature +
                (photoURL != null && !photoURL.isEmpty() ? " | Image: " + photoURL : "");
    }

}
