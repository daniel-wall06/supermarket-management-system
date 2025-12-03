package util;

import models.*;

/**
 * Configure the format of each model to be displayed in the treeview
 */
public class GoodDisplayFormatter {

    public static String formatGoodDisplay(Good good) {
        return "Good - " + good.getDescription() +
                " | weight - (" + good.getWeight() + ") - Qty: " + good.getQuantityToAdd() +
                " | Individual Price - €" + good.getUnitPrice() +
                " | Temp - " + good.getTemperature() +
                " | Image URL - " + good.getPhotoURL() +
                " | Price of all Goods - €" + good.getTotalPrice();
    }

    public static String formatShelfDisplay(Shelf shelf) {
        double totalValue = shelf.getTotalValue();
        return String.format("Shelf %d (Value: €%.2f)",
                shelf.getShelfNumber(),
                totalValue);
    }
    public static String formatAisleDisplay(Aisle aisle) {
        double totalValue = aisle.getTotalValue();
        return String.format("%s (Total Value: €%.2f) | Temp - " + aisle.getTemperature() + " | Aisle Dimensions (LxW) - " + aisle.getLength() + " x " + aisle.getWidth(),
                aisle.getAisleName(),
                totalValue);
    }

    public static String formatFloorAreaDisplay(FloorArea floorArea) {
        double totalValue = floorArea.getTotalValue();
        return String.format("%s (Total Value: €%.2f) | Level - " + floorArea.getLevel(),
                floorArea.getTitle(),
                totalValue);
    }
}