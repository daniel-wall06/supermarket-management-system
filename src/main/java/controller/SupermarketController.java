package controller;

import linkedlist.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;
import org.example.Main;
import linkedlist.Node;
import service.GoodSearchService;
import service.ShelfAllocationService;
import persistence.SupermarkeSaveLoad;
import persistence.LoadedData;
import util.TreeViewManager;
import util.DialogService;
import util.GoodDisplayFormatter;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SupermarketController {

    @FXML
    private TreeView<String> treeView;
    @FXML
    private VBox sidebar;

    private static final Logger logger = Logger.getLogger(SupermarketController.class.getName());

    private DoublyLinkedList<FloorArea> floorAreaList = new DoublyLinkedList<>();
    private DoublyLinkedList<Aisle> aisleList = new DoublyLinkedList<>();
    private DoublyLinkedList<Shelf> shelfList = new DoublyLinkedList<>();

    // Service instances
    private GoodSearchService searchService = new GoodSearchService();
    private ShelfAllocationService allocationService = new ShelfAllocationService();
    private SupermarkeSaveLoad dataStore = new SupermarkeSaveLoad();

    // Utility instances
    private TreeViewManager treeViewManager;

    @FXML
    private void initialize() {
        treeViewManager = new TreeViewManager(treeView, this); // Pass 'this' to constructor
    }

    public DoublyLinkedList<FloorArea> getFloorAreaList() {
        return floorAreaList;
    }

    public TreeView<String> getTreeView() {
        return treeView;
    }

    public void setFloorAreaList(DoublyLinkedList<FloorArea> list) {
        this.floorAreaList = list;
    }

    public void setAisleList(DoublyLinkedList<Aisle> list) {
        this.aisleList = list;
    }

    public void setShelfList(DoublyLinkedList<Shelf> list) {
        this.shelfList = list;
    }

    // Open AddFloorArea window
    @FXML
    public void addFloorArea(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/SuperMarketSystem/addFloorArea.fxml"));
            Scene scene = new Scene(loader.load());

            AddFloorAreaController popupController = loader.getController();
            popupController.setMainController(this);
            popupController.setFloorAreaList(this.floorAreaList);

            Stage popupStage = new Stage();
            popupStage.setTitle("Add Floor Area");
            popupStage.setScene(scene);
            popupStage.setWidth(500);
            popupStage.setHeight(300);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(Main.primaryStage);
            popupStage.showAndWait();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something Went Wrong", e);
        }
    }

    public void addFloorAreaToTree(FloorArea floorArea) {
        treeViewManager.addFloorArea(floorArea);
        floorAreaList.insertAtTail(floorArea);
    }

    public void addAisleToTree(Aisle aisle, FloorArea parentFloorArea) {
        treeViewManager.addAisle(aisle, parentFloorArea);
        aisleList.insertAtTail(aisle);
    }

    public void addShelfToTree(Shelf shelf, Aisle parentAisle) {
        treeViewManager.addShelf(shelf, parentAisle);
        shelfList.insertAtTail(shelf);
    }

    @FXML
    public void addAisle(ActionEvent actionEvent) {
        try {
            TreeViewManager.ObjectTreeItem selectedItem = (TreeViewManager.ObjectTreeItem) treeViewManager.getSelectedItem();
            if (selectedItem == null || selectedItem == treeView.getRoot()) {
                DialogService.showWarning("No Floor Area Selected", "You must select a FloorArea before adding an aisle.");
                return;
            }

            if (!(selectedItem instanceof TreeViewManager.ObjectTreeItem)) {
                DialogService.showWarning("Invalid Selection", "Please select a FloorArea to add an aisle to.");
                return;
            }

            Object storedObject = selectedItem.getStoredObject();
            if (!(storedObject instanceof FloorArea)) {
                DialogService.showWarning("Invalid Selection", "Please select a FloorArea to add an aisle to.");
                return;
            }

            FloorArea parentFloorArea = (FloorArea) storedObject;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/SuperMarketSystem/addAisle.fxml"));
            Scene scene = new Scene(loader.load());

            AddAisleController popupController = loader.getController();
            popupController.setMainController(this);
            popupController.setAisleList(this.aisleList);
            popupController.setParentFloorArea(parentFloorArea);

            Stage popupStage = new Stage();
            popupStage.setTitle("Add an Aisle");
            popupStage.setScene(scene);
            popupStage.setWidth(500);
            popupStage.setHeight(400);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(Main.primaryStage);
            popupStage.showAndWait();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something Went Wrong", e);
        }
    }

    @FXML
    public void addShelf(ActionEvent actionEvent) {
        try {
            TreeViewManager.ObjectTreeItem selectedItem = (TreeViewManager.ObjectTreeItem) treeViewManager.getSelectedItem();
            if (selectedItem == null || selectedItem == treeView.getRoot()) {
                DialogService.showWarning("No Aisle Selected", "You must select an Aisle before adding a shelf.");
                return;
            }

            if (!(selectedItem instanceof TreeViewManager.ObjectTreeItem)) {
                DialogService.showWarning("Invalid Selection", "Please select an Aisle to add a shelf to.");
                return;
            }

            Object storedObject = selectedItem.getStoredObject();
            if (!(storedObject instanceof Aisle)) {
                DialogService.showWarning("Invalid Selection", "Please select an Aisle to add a shelf to.");
                return;
            }

            Aisle parentAisle = (Aisle) storedObject;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/SuperMarketSystem/addShelf.fxml"));
            Scene scene = new Scene(loader.load());

            AddShelfController popupController = loader.getController();
            popupController.setMainController(this);
            popupController.setShelfList(this.shelfList);
            popupController.setParentAisle(parentAisle);

            Stage popupStage = new Stage();
            popupStage.setTitle("Add a Shelf");
            popupStage.setScene(scene);
            popupStage.setWidth(300);
            popupStage.setHeight(200);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(Main.primaryStage);
            popupStage.showAndWait();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something Went Wrong", e);
        }
    }

    public void addGoodToTree(Good good, Shelf parentShelf) {
        treeViewManager.addGood(good, parentShelf);
    }

    @FXML
    public void addGood(ActionEvent actionEvent) {
        try {
            TreeViewManager.ObjectTreeItem selectedItem = (TreeViewManager.ObjectTreeItem) treeViewManager.getSelectedItem();
            if (selectedItem == null || selectedItem == treeView.getRoot()) {
                DialogService.showWarning("No Shelf Selected", "You must select a Shelf before adding a Good.");
                return;
            }

            if (!(selectedItem instanceof TreeViewManager.ObjectTreeItem)) {
                DialogService.showWarning("Invalid Selection", "Please select a Shelf to add goods to.");
                return;
            }

            Object storedObject = selectedItem.getStoredObject();
            if (!(storedObject instanceof Shelf)) {
                DialogService.showWarning("Invalid Selection", "Please select a Shelf to add goods to.");
                return;
            }

            Shelf parentShelf = (Shelf) storedObject;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/SuperMarketSystem/addGood.fxml"));
            Scene scene = new Scene(loader.load());

            AddGoodController popupController = loader.getController();
            popupController.setParentShelf(parentShelf);
            popupController.setMainController(this);

            Stage popupStage = new Stage();
            popupStage.setTitle("Add a Good");
            popupStage.setScene(scene);
            popupStage.setWidth(400);
            popupStage.setHeight(575);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(Main.primaryStage);
            popupStage.showAndWait();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong while adding Good", e);
        }
    }

    @FXML
    public void removeGood(ActionEvent event) {
        TreeViewManager.ObjectTreeItem selected = (TreeViewManager.ObjectTreeItem) treeViewManager.getSelectedItem();

        if (selected == null || selected == treeView.getRoot()) {
            DialogService.showError("Please select a Good to remove.");
            return;
        }

        if (!(selected instanceof TreeViewManager.ObjectTreeItem)) {
            DialogService.showError("Please select a Good to remove.");
            return;
        }

        Object storedObject = selected.getStoredObject();
        if (!(storedObject instanceof Good)) {
            DialogService.showError("Please select a Good to remove.");
            return;
        }

        Good targetGood = (Good) storedObject;
        TreeItem<String> shelfItem = selected.getParent();

        if (!(shelfItem instanceof TreeViewManager.ObjectTreeItem)) {
            DialogService.showError("Invalid selection. Please select a Good.");
            return;
        }

        Object shelfObject = ((TreeViewManager.ObjectTreeItem) shelfItem).getStoredObject();
        if (!(shelfObject instanceof Shelf)) {
            DialogService.showError("Invalid selection. Please select a Good.");
            return;
        }

        Shelf shelf = (Shelf) shelfObject;

        Optional<String> result = DialogService.showRemoveQuantityDialog(targetGood.getDescription(), targetGood.getQuantityToAdd());
        if (!result.isPresent()) return;

        try {
            int qtyToRemove = Integer.parseInt(result.get());

            if (qtyToRemove <= 0) {
                DialogService.showError("Quantity must be positive.");
                return;
            }

            if (qtyToRemove > targetGood.getQuantityToAdd()) {
                DialogService.showError("Cannot remove more than available quantity. Available: " + targetGood.getQuantityToAdd());
                return;
            }

            if (qtyToRemove == targetGood.getQuantityToAdd()) {
                shelf.removeGood(targetGood);
                shelfItem.getChildren().remove(selected);
            } else {
                shelf.removeNumOfGoods(targetGood, qtyToRemove);
                if (targetGood.getQuantityToAdd() > 0) {
                    selected.setValue(GoodDisplayFormatter.formatGoodDisplay(targetGood));
                } else {
                    shelf.removeGood(targetGood);
                    shelfItem.getChildren().remove(selected);
                }
            }

            // Update shelf display (this will cascade to update aisle and floor area)
            ((TreeViewManager.ObjectTreeItem) shelfItem).setValue(GoodDisplayFormatter.formatShelfDisplay(shelf));

            DialogService.showSuccess("Successfully removed " + qtyToRemove + " of " + targetGood.getDescription());

        } catch (NumberFormatException e) {
            DialogService.showError("Please enter a valid number.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error removing good", e);
            DialogService.showError("An error occurred while removing the good.");
        }
    }

    @FXML
    public void searchGoods(ActionEvent actionEvent) {
        Optional<String> result = DialogService.showTextInputDialog("Search Goods", "Search for products", "Enter product name:");

        if (result.isPresent() && !result.get().trim().isEmpty()) {
            String searchTerm = result.get().trim().toLowerCase();
            performSearch(searchTerm);
        }
    }

    private void performSearch(String searchTerm) {
        String results = searchService.searchGoods(searchTerm, floorAreaList).formatForDisplay();
        showSearchResults(results);
    }

    private void showSearchResults(String results) {
        TextArea textArea = new TextArea(results);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(600, 400);

        Stage resultsStage = new Stage();
        resultsStage.setTitle("Search Results");
        resultsStage.setScene(new Scene(scrollPane));
        resultsStage.initModality(Modality.APPLICATION_MODAL);
        resultsStage.initOwner(Main.primaryStage);
        resultsStage.showAndWait();
    }

    @FXML
    public void smartAddGood(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/SuperMarketSystem/addGood.fxml"));
            Scene scene = new Scene(loader.load());

            AddGoodController popupController = loader.getController();
            popupController.setMainController(this);
            popupController.enableSmartAddMode();

            Stage popupStage = new Stage();
            popupStage.setTitle("Smart Add Good");
            popupStage.setScene(scene);
            popupStage.setWidth(400);
            popupStage.setHeight(575);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(Main.primaryStage);
            popupStage.showAndWait();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong with Smart Add", e);
            DialogService.showError("Error opening Smart Add: " + e.getMessage());
        }
    }

    // Method called by Smart Add to find the best location
    public Shelf findBestShelfForGood(Good good) {
        return allocationService.findBestShelfForGood(good, floorAreaList);
    }

    @FXML
    public void saveData(ActionEvent actionEvent) {
        saveData();
    }

    public void saveData() {
        try {
            dataStore.saveSupermarket(floorAreaList);
            DialogService.showSuccess("Data saved successfully! It will be available when you reopen the application.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving data", e);
            DialogService.showError("Error saving data: " + e.getMessage());
        }
    }

    @FXML
    public void loadData(ActionEvent actionEvent) {
        loadData();
    }

    public void loadData() {
        try {
            if (!dataStore.dataExists()) {
                DialogService.showError("No saved data found. Save some data first.");
                return;
            }

            // Clear existing data
            floorAreaList = new DoublyLinkedList<>();
            aisleList = new DoublyLinkedList<>();
            shelfList = new DoublyLinkedList<>();
            treeViewManager.clearTree();

            // Load data using the persistence class
            LoadedData loadedData = dataStore.loadSupermarket();

            // Update our lists with the loaded data
            this.floorAreaList = loadedData.floorAreas;
            this.aisleList = loadedData.aisles;
            this.shelfList = loadedData.shelves;

            // Rebuild the tree view from loaded data
            rebuildTreeView();

            DialogService.showSuccess("Data loaded successfully from previous session!");

        } catch (FileNotFoundException e) {
            DialogService.showError("No saved data found. Save some data first.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading data", e);
            DialogService.showError("Error loading data: " + e.getMessage());
        }
    }

    private void rebuildTreeView() {
        // Rebuild the tree from loaded data
        Node<FloorArea> floorNode = floorAreaList.getHead();
        while (floorNode != null) {
            FloorArea floorArea = floorNode.getValue();
            treeViewManager.addFloorArea(floorArea);

            // Rebuild aisles for this floor area
            Node<Aisle> aisleNode = floorArea.getAisles().getHead();
            while (aisleNode != null) {
                Aisle aisle = aisleNode.getValue();
                treeViewManager.addAisle(aisle, floorArea);

                // Rebuild shelves for this aisle
                Node<Shelf> shelfNode = aisle.getShelves().getHead();
                while (shelfNode != null) {
                    Shelf shelf = shelfNode.getValue();
                    treeViewManager.addShelf(shelf, aisle);

                    // Rebuild goods for this shelf
                    Node<Good> goodNode = shelf.getGoodsList().getHead();
                    while (goodNode != null) {
                        Good good = goodNode.getValue();
                        treeViewManager.addGood(good, shelf);
                        goodNode = goodNode.getNext();
                    }
                    shelfNode = shelfNode.getNext();
                }
                aisleNode = aisleNode.getNext();
            }
            floorNode = floorNode.getNext();
        }
    }
    public double getTotalSupermarketValue() {
        double total = 0.0;
        Node<FloorArea> current = floorAreaList.getHead();
        while (current != null) {
            total += current.getValue().getTotalValue();
            current = current.getNext();
        }
        return total;
    }
}