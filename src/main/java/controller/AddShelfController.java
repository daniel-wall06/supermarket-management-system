package controller;

import linkedlist.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Aisle;
import models.Shelf;

public class AddShelfController {

    @FXML
    private TextField shelfNum;

    @FXML
    private Button submitShelf;

    private DoublyLinkedList<Shelf> shelfList;

    private SupermarketController mainController;

    private Aisle parentAisle;

    @FXML
    void submitShelf(ActionEvent event) {
        if (shelfNum.getText().isEmpty()) {
            showError("Please enter a shelf number.");
            return;
        }

        Integer num;
        try {
            num = Integer.valueOf(shelfNum.getText());
        } catch (NumberFormatException e) {
            showError("Shelf number must be a valid number.");
            return;
        }

        if (num <= 0) {
            showError("Shelf number must be greater than zero.");
            return;
        }

        if (parentAisle == null) {
            showError("No parent aisle assigned! Select an aisle first.");
            return;
        }

        Shelf newShelf = new Shelf(num);
        parentAisle.addShelf(newShelf);

        if (mainController != null) {
            mainController.addShelfToTree(newShelf, parentAisle);
        }

        Stage stage = (Stage) submitShelf.getScene().getWindow();
        stage.close();
    }


    public void setMainController(SupermarketController supermarketController) {
        this.mainController = supermarketController;
    }

    public void setShelfList(DoublyLinkedList<Shelf> shelfList) {
        this.shelfList=shelfList;
    }

    public void setParentAisle(Aisle parentAisle) {
        this.parentAisle=parentAisle;
    }
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
