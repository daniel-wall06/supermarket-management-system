package service;

import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import models.*;

/**
 * Search for a specified good
 */
public class GoodSearch {
    /**
     * Search through each floorArea then aisle and finally shelf to find matching.
     * @param searchTerm search query input from user
     * @param floorAreas doubly link list of floorareas
     * @return the result of the search
     */
    public SearchResult searchGoods(String searchTerm, DoublyLinkedList<FloorArea> floorAreas) {
        SearchResult result = new SearchResult(searchTerm);

        if (floorAreas == null || searchTerm == null || searchTerm.trim().isEmpty()) {
            return result;
        }
        Node<FloorArea> floorNode = floorAreas.getHead();
        while (floorNode != null) {
            FloorArea floorArea = floorNode.getValue();
            Node<Aisle> aisleNode = floorArea.getAisles().getHead();

            while (aisleNode != null) {
                Aisle aisle = aisleNode.getValue();
                Node<Shelf> shelfNode = aisle.getShelves().getHead();

                while (shelfNode != null) {
                    Shelf shelf = shelfNode.getValue();
                    searchShelf(shelf, floorArea, aisle, searchTerm, result);
                    shelfNode = shelfNode.getNext();
                }
                aisleNode = aisleNode.getNext();
            }
            floorNode = floorNode.getNext();
        }

        return result;
    }

    /**
     * Search through a specified shelf to find a good
     */
    private void searchShelf(Shelf shelf, FloorArea floorArea, Aisle aisle,
                             String searchTerm, SearchResult result) {
        Node<Good> goodNode = shelf.getGoodsList().getHead();

        while (goodNode != null) {
            Good good = goodNode.getValue();

            if (good.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                result.addMatch(new GoodMatch(good, floorArea, aisle, shelf));
            }
            goodNode = goodNode.getNext();
        }
    }
}