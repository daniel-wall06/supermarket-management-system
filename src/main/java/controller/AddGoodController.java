package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Good;
import models.Shelf;

/**
 * Add Good Controller for the addGood view
 */
public class AddGoodController {

    @FXML private TextField goodName;
    @FXML private TextField goodSize;
    @FXML private TextField unitPrice;
    @FXML private TextField quantityGood;
    @FXML private MenuButton goodTemperature;
    @FXML private TextField goodImageURL;
    @FXML private Button submitGood;

    private Shelf parentShelf;
    private SupermarketController mainController;
    private boolean smartAddMode = false;
    private Stage stage;

    private String selectedTemperature = "Unrefrigerated";

    @FXML
    public void initialize() {
        setupTemperatureMenu();
    }

    private void setupTemperatureMenu() {

        for (MenuItem item : goodTemperature.getItems()) {
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    selectedTemperature = item.getText();
                    goodTemperature.setText("Temperature: " + selectedTemperature);
                }
            });
        }
        goodTemperature.setText("Temperature: " + selectedTemperature);
    }

    public void setParentShelf(Shelf parentShelf) {
        this.parentShelf = parentShelf;
    }

    public void setMainController(SupermarketController mainController) {
        this.mainController = mainController;
    }

    public void enableSmartAddMode() {
        this.smartAddMode = true;
        submitGood.setText("Smart Add");
    }

    @FXML
    public void submitGood(ActionEvent event) {
        try {
            if (stage == null) {
                stage = (Stage) submitGood.getScene().getWindow();
            }
            if (!validateInputs()) {
                return;
            }
            Good good = createGoodFromInputs();
            if (smartAddMode) {
                performSmartAdd(good);
            } else {
                performNormalAdd(good);
            }

        } catch (Exception e) {
            showError("Error adding good: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void performSmartAdd(Good good) {
        Shelf bestShelf = mainController.findBestShelfForGood(good);
        if (bestShelf != null) {
            bestShelf.addGood(good);
            mainController.addGoodToTree(good, bestShelf);
            showSuccess("Good smart added to:\n" +
                    "Shelf " + bestShelf.getShelfNumber() + " in " +
                    bestShelf.getParentAisle().getAisleName() + " -> " +
                    bestShelf.getParentAisle().getParentFloorArea().getTitle());

            stage.close();
        } else {
            showError("No suitable shelf found for this good.\n" +
                    "Please create an appropriate aisle/shelf first.");
        }
    }

    private void performNormalAdd(Good good) {
        if (parentShelf == null) {
            showError("No shelf selected for normal add.");
            return;
        }

        parentShelf.addGood(good);
        mainController.addGoodToTree(good, parentShelf);
        showSuccess("Good added successfully to Shelf " + parentShelf.getShelfNumber());
        stage.close();
    }

    /**
     * Using the inputs create a new Good to be used in either add Good or smart Add.
     * @return
     */
    private Good createGoodFromInputs() {
        String description = goodName.getText().trim();
        double weight = Double.parseDouble(goodSize.getText().trim());
        int quantity = Integer.parseInt(quantityGood.getText().trim());
        String temperature = selectedTemperature;
        String photoURL = goodImageURL.getText().trim();
        double unitPriceValue = Double.parseDouble(unitPrice.getText().trim());

        return new Good(description, weight, quantity, temperature, photoURL, unitPriceValue);
    }

    /**
     * Validate inputs to ensure correct values are inputted
     * @return boolean value true or false
     */
    private boolean validateInputs() {
        if (goodName.getText().trim().isEmpty()) {
            showError("Please enter a description.");
            return false;
        }
        try {
            double weight = Double.parseDouble(goodSize.getText().trim());
            if (weight <= 0) {
                showError("Weight must be positive.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid weight.");
            return false;
        }

        try {
            int quantity = Integer.parseInt(quantityGood.getText().trim());
            if (quantity <= 0) {
                showError("Quantity must be positive.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid quantity.");
            return false;
        }

        try {
            double unitPriceValue = Double.parseDouble(unitPrice.getText().trim());
            if (unitPriceValue <= 0) {
                showError("Unit price must be positive.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid unit price.");
            return false;
        }

        return true;
    }

    /**
     * Shows error message
     * @param message
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Show success message when good is added
     * @param message
     */
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}