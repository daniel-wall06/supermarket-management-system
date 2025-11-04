package models;
import LinkedList.DoublyLinkedList;
import LinkedList.Node;

/**
 *
 */
public class Shelf {
    private int shelfNumber;
    private DoublyLinkedList<Good> goodsList;

    /**
     *
     * @param shelfNumber
     */
    public Shelf(int shelfNumber) {
        this.shelfNumber = shelfNumber;
        this.goodsList = new DoublyLinkedList<>();

    }

    /**
     *
     * @return
     */
    public int getShelfNumber() {
        return shelfNumber;
    }

    /**
     *
     * @return
     */
    public DoublyLinkedList<Good> getGoodsList() {
        return goodsList;
    }

    /**
     *
     * @param goodsList
     */
    public void setGoodsList(DoublyLinkedList<Good> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     *
     * @param shelfNumber
     */
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    // TODO: Update method to check if the goods details are already stored etc
    public void addGood(Good good) {
        goodsList.insertAtTail(good);
    }
    //FIXME: FIX THIS
    public String allShelfItems() {
        String shelfString = "";
        if(goodsList != null && !goodsList.isEmpty()) {
            Node<Good> current = goodsList.getHead();
            while(current != null) {
                Good good = current.getValue();
                shelfString += good.toString() + "\n";
                current = current.getNext();
            }
        }
        else{
            shelfString += "No goods on the shelf \n";
        }

        shelfString += "Shelf Number: " + shelfNumber
                + " | Total Goods: " + (goodsList != null ? goodsList.getSize() : 0)
                + "\n";
        return shelfString;
    }


}
