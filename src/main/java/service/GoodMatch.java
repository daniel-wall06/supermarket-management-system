package service;

import models.*;
/**
 * Represents a found good with its complete location in the supermarket.
 * Used to display search results with hierarchical location information.
 */
public class GoodMatch {
    private final Good good;
    private final FloorArea floorArea;
    private final Aisle aisle;
    private final Shelf shelf;

    public GoodMatch(Good good, FloorArea floorArea, Aisle aisle, Shelf shelf) {
        this.good = good;
        this.floorArea = floorArea;
        this.aisle = aisle;
        this.shelf = shelf;
    }


    public Good getGood() { return good; }
    public FloorArea getFloorArea() { return floorArea; }
    public Aisle getAisle() { return aisle; }
    public Shelf getShelf() { return shelf; }

    public String format() {
        return good.getDescription() + " (" + good.getWeight() + ")\n" +
                "   Location: " + floorArea.getTitle() + " -> " +
                aisle.getAisleName() + " -> Shelf " + shelf.getShelfNumber() + "\n" +
                "   Quantity: " + good.getQuantityToAdd() +
                " | Price: €" + String.format("%.2f", good.getTotalPrice());
    }
}