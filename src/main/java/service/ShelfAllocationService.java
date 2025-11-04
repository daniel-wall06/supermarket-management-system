package service;

import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import models.*;

public class ShelfAllocationService {

    public Shelf findBestShelfForGood(Good good, DoublyLinkedList<FloorArea> floorAreas) {
        if (good == null || floorAreas == null) {
            return null;
        }


        Shelf existingShelf = findShelfWithExistingGood(good, floorAreas);
        if (existingShelf != null) {
            System.out.println("Smart Add: Found existing good location");
            return existingShelf;
        }

        // Strategy 2: Find shelf with matching temperature and similar goods
        Shelf similarShelf = findShelfWithSimilarGoods(good, floorAreas);
        if (similarShelf != null) {
            System.out.println("Smart Add: Found shelf with similar goods");
            return similarShelf;
        }

        // Strategy 3: Find any shelf with matching temperature
        Shelf tempShelf = findShelfWithMatchingTemperature(good.getTemperature(), floorAreas);
        if (tempShelf != null) {
            System.out.println("Smart Add: Found shelf with matching temperature");
            return tempShelf;
        }

        // Strategy 4: Find first available shelf
        Shelf firstShelf = findFirstAvailableShelf(floorAreas);
        if (firstShelf != null) {
            System.out.println("Smart Add: Using first available shelf");
            return firstShelf;
        }

        System.out.println("Smart Add: No suitable shelf found");
        return null;
    }

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
                    // Check if this shelf has the exact same good
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

    private Shelf findShelfWithSimilarGoods(Good newGood, DoublyLinkedList<FloorArea> floorAreas) {
        Node<FloorArea> floorNode = floorAreas.getHead();
        while (floorNode != null) {
            FloorArea floorArea = floorNode.getValue();
            Node<Aisle> aisleNode = floorArea.getAisles().getHead();

            while (aisleNode != null) {
                Aisle aisle = aisleNode.getValue();
                // Check if aisle temperature matches
                if (aisle.getTemperature().equalsIgnoreCase(newGood.getTemperature())) {
                    Node<Shelf> shelfNode = aisle.getShelves().getHead();

                    while (shelfNode != null) {
                        Shelf shelf = shelfNode.getValue();
                        // Check if shelf has goods with similar description
                        if (hasSimilarGoods(shelf, newGood.getDescription())) {
                            return shelf;
                        }
                        shelfNode = shelfNode.getNext();
                    }
                }
                aisleNode = aisleNode.getNext();
            }
            floorNode = floorNode.getNext();
        }
        return null;
    }

    private boolean hasSimilarGoods(Shelf shelf, String description) {
        if (description == null || description.trim().isEmpty()) {
            return false;
        }

        String[] keywords = description.toLowerCase().split(" ");
        Node<Good> goodNode = shelf.getGoodsList().getHead();

        while (goodNode != null) {
            Good existingGood = goodNode.getValue();
            String existingDesc = existingGood.getDescription().toLowerCase();

            for (String keyword : keywords) {
                if (keyword.length() > 3 && existingDesc.contains(keyword)) {
                    return true;
                }
            }
            goodNode = goodNode.getNext();
        }
        return false;
    }

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