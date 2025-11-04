package models;
import linkedlist.DoublyLinkedList;
import linkedlist.Node;
import java.io.Serializable;

/**
 *
 */
public class Shelf implements Serializable {
    private static final long serialVersionUID = 1L;
    private int shelfNumber;
    private DoublyLinkedList<Good> goodsList;
    private double totalValue = 0;
    private Aisle parentAisle;

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

    public double getTotalValue() {
        double total = 0.0;
        Node<Good> current = goodsList.getHead();
        while (current != null) {
            Good good = current.getValue();
            total += good.getUnitPrice() * good.getQuantityToAdd();
            current = current.getNext();
        }
        return total;
    }
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;

    }


    public void addGood(Good newGood) {
        Node<Good> current = goodsList.getHead();

        while (current != null) {
            Good existing = current.getValue();

            if (existing.getDescription().equalsIgnoreCase(newGood.getDescription()) &&
                    existing.getWeight() == newGood.getWeight()) {


                existing.setQuantityToAdd(
                        existing.getQuantityToAdd() + newGood.getQuantityToAdd()
                );


                existing.setUnitPrice(newGood.getUnitPrice());
                existing.setPhotoURL(newGood.getPhotoURL());

                double addedValue = newGood.getUnitPrice() * newGood.getQuantityToAdd();
                totalValue += addedValue;
                if (parentAisle != null) parentAisle.updateTotalValue(addedValue);

                return;
            }

            current = current.getNext();
        }


        goodsList.insertAtTail(newGood);

        double addedValue = newGood.getTotalPrice();
        totalValue += addedValue;
        if (parentAisle != null) parentAisle.updateTotalValue(addedValue);
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
    public void removeGood(Good target) {
        Node<Good> current = goodsList.getHead();

        while (current != null) {
            Good g = current.getValue();

            if (g.getDescription().equalsIgnoreCase(target.getDescription()) &&
                    g.getWeight() == target.getWeight()) {

                double removedValue = g.getUnitPrice() * g.getQuantityToAdd();
                totalValue -= removedValue;

                goodsList.deleteByValue(g);

                if (parentAisle != null) parentAisle.updateTotalValue(-removedValue);

                return;
            }

            current = current.getNext();
        }
    }




    public void removeNumOfGoods(Good target, int quantityToRemove) {
        Node<Good> current = goodsList.getHead();
        while (current != null) {
            Good g = current.getValue();

            if (g.getDescription().equalsIgnoreCase(target.getDescription()) &&
                    g.getWeight() == target.getWeight()) {

                if (quantityToRemove >= g.getQuantityToAdd()) {
                    // Remove entire item
                    double removedValue = g.getUnitPrice() * g.getQuantityToAdd();
                    totalValue -= removedValue;
                    goodsList.deleteByValue(g);
                    if (parentAisle != null) parentAisle.updateTotalValue(-removedValue);
                } else {
                    // Reduce quantity only
                    g.setQuantityToAdd(g.getQuantityToAdd() - quantityToRemove);
                    double removedValue = g.getUnitPrice() * quantityToRemove;
                    totalValue -= removedValue;
                    if (parentAisle != null) parentAisle.updateTotalValue(-removedValue);
                }

                return;
            }

            current = current.getNext();
        }
    }
    public Good findGood(String description, double weight) {
        Node<Good> current = goodsList.getHead();
        while (current != null) {
            Good g = current.getValue();
            if (g.getDescription().equalsIgnoreCase(description) &&
                    g.getWeight() == weight) {
                return g;
            }
            current = current.getNext();
        }
        return null;
    }


    public void resetGoods() {
        goodsList.resetAll();
        if (parentAisle != null) {
            parentAisle.updateTotalValue(-totalValue);
        }
        totalValue = 0;
    }

    @Override
    public String toString() {
        return "Shelf " + shelfNumber + " (" + goodsList.getSize() + " goods, Total: €"
                + String.format("%.2f", totalValue) + ")";
    }



    public void setParentAisle(Aisle aisle) {
        parentAisle = aisle;
    }
    public Aisle getParentAisle() {
        return parentAisle;
    }

}

