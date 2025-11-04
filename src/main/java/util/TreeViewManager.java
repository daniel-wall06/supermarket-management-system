package util;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import models.*;
import controller.SupermarketController;

public class TreeViewManager {
    private TreeView<String> treeView;
    private SupermarketController controller; // Add reference to controller

    // Update constructor to accept controller
    public TreeViewManager(TreeView<String> treeView, SupermarketController controller) {
        this.treeView = treeView;
        this.controller = controller;
        initializeTree();
    }

    private void initializeTree() {
        // Calculate supermarket total using controller
        double supermarketTotal = controller.getTotalSupermarketValue();
        String rootText = String.format("Supermarket (Total Value: €%.2f)", supermarketTotal);
        TreeItem<String> root = new TreeItem<>(rootText);
        root.setExpanded(true);
        treeView.setRoot(root);
    }

    public static class ObjectTreeItem extends TreeItem<String> {
        private Object storedObject;

        public ObjectTreeItem(String value) {
            super(value);
        }

        public Object getStoredObject() {
            return storedObject;
        }

        public void setStoredObject(Object storedObject) {
            this.storedObject = storedObject;
        }
    }

    public void addFloorArea(FloorArea floorArea) {
        String display = GoodDisplayFormatter.formatFloorAreaDisplay(floorArea);
        ObjectTreeItem newItem = new ObjectTreeItem(display);
        newItem.setStoredObject(floorArea);
        treeView.getRoot().getChildren().add(newItem);
        updateSupermarketTotal(); // Update root after adding floor area
    }

    public void addAisle(Aisle aisle, FloorArea parentFloorArea) {
        TreeItem<String> parentItem = findTreeItemForFloorArea(parentFloorArea);
        if (parentItem != null) {
            String display = GoodDisplayFormatter.formatAisleDisplay(aisle);
            ObjectTreeItem aisleItem = new ObjectTreeItem(display);
            aisleItem.setStoredObject(aisle);
            parentItem.getChildren().add(aisleItem);
            parentItem.setExpanded(true);

            // Update the floor area display to reflect new total value
            updateFloorAreaDisplay(parentFloorArea);
        }
    }

    public void addShelf(Shelf shelf, Aisle parentAisle) {
        TreeItem<String> aisleItem = findTreeItemForAisle(parentAisle);
        if (aisleItem != null) {
            String display = GoodDisplayFormatter.formatShelfDisplay(shelf);
            ObjectTreeItem shelfItem = new ObjectTreeItem(display);
            shelfItem.setStoredObject(shelf);
            aisleItem.getChildren().add(shelfItem);
            aisleItem.setExpanded(true);

            // Update the aisle display to reflect new total value
            updateAisleDisplay(parentAisle);
        }
    }

    public void addGood(Good good, Shelf parentShelf) {
        TreeItem<String> shelfItem = findTreeItemForShelf(parentShelf);
        if (shelfItem != null) {
            // Check if good already exists
            TreeItem<String> existingItem = findExistingGood(shelfItem, good);

            if (existingItem != null) {
                ((ObjectTreeItem) existingItem).setStoredObject(good);
                existingItem.setValue(GoodDisplayFormatter.formatGoodDisplay(good));
            } else {
                ObjectTreeItem goodItem = new ObjectTreeItem(GoodDisplayFormatter.formatGoodDisplay(good));
                goodItem.setStoredObject(good);
                shelfItem.getChildren().add(goodItem);
            }

            // Update shelf display
            shelfItem.setValue(GoodDisplayFormatter.formatShelfDisplay(parentShelf));
            shelfItem.setExpanded(true);

            // Update the parent aisle and floor area
            updateParentDisplays(parentShelf);
        }
    }

    private void updateParentDisplays(Shelf shelf) {
        // Update aisle display
        Aisle parentAisle = shelf.getParentAisle();
        if (parentAisle != null) {
            updateAisleDisplay(parentAisle);
        }
    }

    private void updateAisleDisplay(Aisle aisle) {
        TreeItem<String> aisleItem = findTreeItemForAisle(aisle);
        if (aisleItem != null) {
            aisleItem.setValue(GoodDisplayFormatter.formatAisleDisplay(aisle));

            // Update parent floor area
            FloorArea parentFloorArea = aisle.getParentFloorArea();
            if (parentFloorArea != null) {
                updateFloorAreaDisplay(parentFloorArea);
            }
        }
    }

    private void updateFloorAreaDisplay(FloorArea floorArea) {
        TreeItem<String> floorItem = findTreeItemForFloorArea(floorArea);
        if (floorItem != null) {
            floorItem.setValue(GoodDisplayFormatter.formatFloorAreaDisplay(floorArea));
        }
        updateSupermarketTotal(); // Also update supermarket total
    }

    // Add method to update supermarket total in root
    public void updateSupermarketTotal() {
        if (controller != null) {
            double supermarketTotal = controller.getTotalSupermarketValue();
            String rootText = String.format("Supermarket (Total Value: €%.2f)", supermarketTotal);
            treeView.getRoot().setValue(rootText);
        }
    }

    private TreeItem<String> findExistingGood(TreeItem<String> shelfItem, Good newGood) {
        for (TreeItem<String> child : shelfItem.getChildren()) {
            if (child instanceof ObjectTreeItem) {
                Object storedObject = ((ObjectTreeItem) child).getStoredObject();
                if (storedObject instanceof Good) {
                    Good existingGood = (Good) storedObject;
                    if (existingGood.getDescription().equals(newGood.getDescription()) &&
                            existingGood.getWeight() == newGood.getWeight()) {
                        return child;
                    }
                }
            }
        }
        return null;
    }

    private TreeItem<String> findTreeItemForFloorArea(FloorArea floorArea) {
        for (TreeItem<String> item : treeView.getRoot().getChildren()) {
            if (item instanceof ObjectTreeItem) {
                Object storedObject = ((ObjectTreeItem) item).getStoredObject();
                if (storedObject instanceof FloorArea && storedObject.equals(floorArea)) {
                    return item;
                }
            }
        }
        return null;
    }

    private TreeItem<String> findTreeItemForAisle(Aisle aisle) {
        for (TreeItem<String> floorAreaItem : treeView.getRoot().getChildren()) {
            for (TreeItem<String> aisleItem : floorAreaItem.getChildren()) {
                if (aisleItem instanceof ObjectTreeItem) {
                    Object storedObject = ((ObjectTreeItem) aisleItem).getStoredObject();
                    if (storedObject instanceof Aisle && storedObject.equals(aisle)) {
                        return aisleItem;
                    }
                }
            }
        }
        return null;
    }

    private TreeItem<String> findTreeItemForShelf(Shelf shelf) {
        for (TreeItem<String> floorAreaItem : treeView.getRoot().getChildren()) {
            for (TreeItem<String> aisleItem : floorAreaItem.getChildren()) {
                for (TreeItem<String> shelfItem : aisleItem.getChildren()) {
                    if (shelfItem instanceof ObjectTreeItem) {
                        Object storedObject = ((ObjectTreeItem) shelfItem).getStoredObject();
                        if (storedObject instanceof Shelf && storedObject.equals(shelf)) {
                            return shelfItem;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void clearTree() {
        treeView.getRoot().getChildren().clear();
    }

    public TreeItem<String> getSelectedItem() {
        return treeView.getSelectionModel().getSelectedItem();
    }
}