package models;
import LinkedList.DoublyLinkedList;


public class Shelf {
    private int shelfNumber;
    private DoublyLinkedList<Good> goodsList;

    public Shelf(int shelfNumber, DoublyLinkedList<Good> goodsList) {
        this.shelfNumber = shelfNumber;
        this.goodsList = goodsList;
    }
    public int getShelfNumber() {
        return shelfNumber;
    }
    public DoublyLinkedList<Good> getGoodsList() {
        return goodsList;
    }
    public void setGoodsList(DoublyLinkedList<Good> goodsList) {
        this.goodsList = goodsList;
    }
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }
    // TODO: Update method to check if the goods details are already stored etc
    public void addGood(Good good) {
        goodsList.insertAtTail(good);
    }


}
