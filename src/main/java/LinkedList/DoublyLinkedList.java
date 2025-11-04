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
    private int size;



    /**
     * Checks if the list is empty
     *
     * @return true if there are no elements, otherwise false
     */
    public boolean isEmpty() {
        return head == null; //checks if the list is empty, if the head is null list is empty
    }

    public int getSize() {
        return size;
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
        size++;



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
        size++;

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
    /*public void deleteNode(Node<T> node){
        if(node == null || isEmpty()){return;}
        Node<T> prev = node.getPrevious();
        Node<T> next = node.getNext();

        if(prev == null){
            head = next;
        }
        else{
            prev.setNext(next);
        }

        if(next == null){
            tail = prev;
        }
        else{
            next.setPrevious(prev);
        }
        node.setNext(null);
        node.setPrevious(null);

    }*/
    public void deleteNode(Node<T> node){
        if(isEmpty() || node == null) return;
        if(node == head){
            head = head.getNext();
            if(head != null) head.setPrevious(null);
        }
        else if(node == tail){
            tail = tail.getPrevious();
            if(tail != null) tail.setNext(null);
        }
        else{
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
        size--;



    }
    public void deleteByValue(T value) {
        if (isEmpty()) return;

        Node<T> current = head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                deleteNode(current); // reuse existing logic
                return;
            }
            current = current.getNext();
        }

    }
    // TODO: Add search functionality
 
    //FIXME: NEED TO FIX TRAVERSAL METHOD
    public String forwardTraversal(){
        if(isEmpty()) return "";

        String result = "";
        Node<T> current = head;
        while (current != null) {
            result += current.getValue().toString() + " \n";
            current = current.getNext();
        }
        return result;

    }










}
