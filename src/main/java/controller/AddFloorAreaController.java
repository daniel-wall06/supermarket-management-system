package controller;

import linkedlist.DoublyLinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FloorArea;

/**
 * Controller for the addFloorArea view
 */
public class AddFloorAreaController {

    @FXML
    private TextField floorAreaTitle;

    @FXML
    private TextField floorAreaLevel;

    @FXML
    private Button submitFloorArea;

    private SupermarketController mainController;
    private DoublyLinkedList<FloorArea> floorAreaList;

    public void setMainController(SupermarketController mainController) {
        this.mainController = mainController;
    }
    public void setFloorAreaList(DoublyLinkedList<FloorArea> list) {
        this.floorAreaList = list;
    }

    @FXML
    private void submitFloorArea(javafx.event.ActionEvent event) {
        String title = floorAreaTitle.getText().trim();
        String level = floorAreaLevel.getText().trim();
        if (!title.isEmpty() && !level.isEmpty()&& mainController != null) {
            FloorArea newArea = new FloorArea(title, level);

            mainController.addFloorAreaToTree(newArea);
        }

        Stage stage = (Stage) submitFloorArea.getScene().getWindow();
        stage.close();
    }
}
