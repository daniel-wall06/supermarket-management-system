package controller;

import linkedlist.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Aisle;
import models.FloorArea;

public class AddAisleController {

    private SupermarketController mainController;

    @FXML
    private MenuItem aisleFrozen;

    @FXML
    private MenuItem aisleRefrigerated;

    @FXML
    private MenuItem aisleUnrefrigerated;

    @FXML
    private MenuButton aisleTemperature;

    @FXML
    private TextField aisleLength;

    @FXML
    private TextField aisleName;

    @FXML
    private TextField aisleWidth;

    @FXML
    private Button submitAisle;

    private DoublyLinkedList<Aisle> aislesList;

    private String selectedTemperature;

    private FloorArea parentFloorArea;

    public void setParentFloorArea(FloorArea floorArea) {
        this.parentFloorArea = floorArea;
    }

    @FXML
    public void initialize() {
        // Set event handlers for menu items
        aisleFrozen.setOnAction(e -> selectTemperature("Frozen"));
        aisleRefrigerated.setOnAction(e -> selectTemperature("Refrigerated"));
        aisleUnrefrigerated.setOnAction(e -> selectTemperature("Unrefrigerated"));
    }

    private void selectTemperature(String temperature) {
        selectedTemperature = temperature;
        aisleTemperature.setText(temperature); // update MenuButton text
    }

    @FXML
    void submitAisle(ActionEvent event) {

        // Validate name
        if (aisleName.getText().isEmpty()) {
            showError("Please enter an aisle name.");
            return;
        }

        // Validate temperature selection
        if (selectedTemperature == null) {
            showError("Please select an aisle temperature.");
            return;
        }

        // Validate and parse length
        int length;
        try {
            length = Integer.parseInt(aisleLength.getText());
            if (length <= 0) {
                showError("Aisle length must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Aisle length must be a valid number.");
            return;
        }

        // Validate and parse width
        int width;
        try {
            width = Integer.parseInt(aisleWidth.getText());
            if (width <= 0) {
                showError("Aisle width must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Aisle width must be a valid number.");
            return;
        }

        // Validate parent association
        if (parentFloorArea == null) {
            showError("No parent floor area selected! Please select a FloorArea first.");
            return;
        }

        // Create new aisle
        Aisle newAisle = new Aisle(aisleName.getText(), length, width, selectedTemperature);
        parentFloorArea.addAisle(newAisle);

        // Update tree
        if (mainController != null) {
            mainController.addAisleToTree(newAisle, parentFloorArea);
        }

        // Close popup
        Stage stage = (Stage) submitAisle.getScene().getWindow();
        stage.close();
    }





    public void setAisleList(DoublyLinkedList<Aisle> list) {
        this.aislesList = list;
    }

    public void setMainController(SupermarketController supermarketController) {
        this.mainController = supermarketController;
    }
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
