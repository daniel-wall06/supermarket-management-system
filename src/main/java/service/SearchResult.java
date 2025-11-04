package service;

import linkedlist.DoublyLinkedList;
import linkedlist.Node;

public class SearchResult {
    private final String searchTerm;
    private final DoublyLinkedList<GoodMatch> matches = new DoublyLinkedList<>();

    public SearchResult(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void addMatch(GoodMatch match) {
        matches.insertAtTail(match);
    }

    public int getTotalFound() {
        return matches.getSize();
    }

    public DoublyLinkedList<GoodMatch> getMatches() {
        return matches;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public boolean isEmpty() {
        return matches.isEmpty();
    }

    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Search Results for: \"").append(searchTerm).append("\"\n\n");

        if (matches.isEmpty()) {
            sb.append("No products found matching your search.");
        } else {
            sb.append("Found ").append(matches.getSize()).append(" product(s)\n\n");

            // Iterate using nodes
            Node<GoodMatch> current = matches.getHead();
            int index = 1;
            while (current != null) {
                GoodMatch match = current.getValue();
                sb.append(index).append(". ").append(match.format()).append("\n\n");
                current = current.getNext();
                index++;
            }
        }
        return sb.toString();
    }
}