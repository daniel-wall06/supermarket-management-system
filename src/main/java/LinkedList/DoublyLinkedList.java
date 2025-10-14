package LinkedList;

/**
 * Doubly Linked List implementation
 * <p>
 * Each node inside the list has a reference to the previous and next node.
 * </p>
 * @param <T> value stored in the list
 */

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * Checks if the list is empty
     *
     * @return true if there are no elements, otherwise false
     */
    public boolean isEmpty() {
        return head == null; //checks if the list is empty, if the head is null list is empty
    }


    /**
     * Inserts a new element at the beginning of the list.
     * @param data the data to insert into the list
     */
    public void insertAtHead(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }


    }

    /**
     * Inserts a new element at the end of the list
     * @param data the data to insert into the list
     */
    public void insertAtTail(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
    }

    /**
     * Returns the first node in the list
     * @return the head (first) node, null if the list is empty
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Returns the last node in the list
     * @return the tail (last) node, null if list is empty
     */
    public Node<T> getTail() {
        return tail;
    }
    // TODO: Add delete functionality
    // TODO: Add search functionality
    // TODO: Add traversal methods
    // TODO: Add size tracking for quantities







}
