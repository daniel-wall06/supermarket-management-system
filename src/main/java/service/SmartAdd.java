package service;

import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import models.*;

/**
 * Smart Add finds a suitable shelf for a given good
 */
public class SmartAdd {

    public Shelf findBestShelfForGood(Good good, DoublyLinkedList<FloorArea> floorAreas) {
        if (good == null || floorAreas == null) {
            return null;
        }
        /**
         * Find Shelves that already have goods
         */
        Shelf existingShelf = findShelfWithExistingGood(good, floorAreas);
        if (existingShelf != null) {
            System.out.println("Smart Add: Found existing good location");
            return existingShelf;
        }

        /**
         * Find shelves that have matching temperatures to the good
         */
        Shelf tempShelf = findShelfWithMatchingTemperature(good.getTemperature(), floorAreas);
        if (tempShelf != null) {
            System.out.println("Smart Add: Found shelf with matching temperature");
            return tempShelf;
        }

        /**
         * If none of the above are found, find the first available shelf
         */
        Shelf firstShelf = findFirstAvailableShelf(floorAreas);
        if (firstShelf != null) {
            System.out.println("Smart Add: Using first available shelf");
            return firstShelf;
        }
        /**
         * If no shelf is found at all
         */
        System.out.println("Smart Add: No suitable shelf found");
        return null;
    }

    /**
     * Check all the shelves
     * @param newGood good to be added
     * @param floorAreas doublylinkedlist of all floor areas
     * @return a shelf if one is found, null otherwise
     */
    private Shelf findShelfWithExistingGood(Good newGood, DoublyLinkedList<FloorArea> floorAreas) {
        Node<FloorArea> floorNode = floorAreas.getHead();
        while (floorNode != null) {
            FloorArea floorArea = floorNode.getValue();
            Node<Aisle> aisleNode = floorArea.getAisles().getHead();
            while (aisleNode != null) {
                Aisle aisle = aisleNode.getValue();
                Node<Shelf> shelfNode = aisle.getShelves().getHead();
                while (shelfNode != null) {
                    Shelf shelf = shelfNode.getValue();
                    Good existing = shelf.findGood(newGood.getDescription(), newGood.getWeight());
                    if (existing != null) {
                        return shelf;
                    }
                    shelfNode = shelfNode.getNext();
                }
                aisleNode = aisleNode.getNext();
            }
            floorNode = floorNode.getNext();
        }
        return null;
    }

    /**
     * Check all the shelves that contains same temperature goods
     *
     */

    private Shelf findShelfWithMatchingTemperature(String temperature, DoublyLinkedList<FloorArea> floorAreas) {
        Node<FloorArea> floorNode = floorAreas.getHead();
        while (floorNode != null) {
            FloorArea floorArea = floorNode.getValue();
            Node<Aisle> aisleNode = floorArea.getAisles().getHead();

            while (aisleNode != null) {
                Aisle aisle = aisleNode.getValue();
                if (aisle.getTemperature().equalsIgnoreCase(temperature)) {
                    Node<Shelf> shelfNode = aisle.getShelves().getHead();
                    if (shelfNode != null) {
                        return shelfNode.getValue();
                    }
                }
                aisleNode = aisleNode.getNext();
            }
            floorNode = floorNode.getNext();
        }
        return null;
    }

    /**
     * Find the first available shelf
     * @param floorAreas doublylinkedlist of all floorareas
     * @return first available shelf
     */
    private Shelf findFirstAvailableShelf(DoublyLinkedList<FloorArea> floorAreas) {
        Node<FloorArea> floorNode = floorAreas.getHead();
        while (floorNode != null) {
            FloorArea floorArea = floorNode.getValue();
            Node<Aisle> aisleNode = floorArea.getAisles().getHead();

            while (aisleNode != null) {
                Aisle aisle = aisleNode.getValue();
                Node<Shelf> shelfNode = aisle.getShelves().getHead();

                if (shelfNode != null) {
                    return shelfNode.getValue();
                }
                aisleNode = aisleNode.getNext();
            }
            floorNode = floorNode.getNext();
        }
        return null;
    }
}