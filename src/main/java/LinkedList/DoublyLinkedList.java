package LinkedList;

public class DoublyLinkedList<T>{
    private Node<T> head;
    private Node<T> tail;

    public boolean isEmpty(){
        return head == null; //checks if the list is empty, if the head is null list is empty
    }

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

    public Node<T> getHead() {
        return head;
    }




}
