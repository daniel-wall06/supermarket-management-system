package LinkedList;

public class DoublyLinkedList{
    private Node head;
    private Node tail;

    public boolean isEmpty(){
        return head == null; //checks if the list is empty, if the head is null list is empty
    }

    public void insertAtHead(Node data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            newNode.setNext(head);
            head = newNode;
        }


    }

    public void insertAtTail(Node data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            tail.setNext(newNode);
            tail = newNode;
        }
    }


}
