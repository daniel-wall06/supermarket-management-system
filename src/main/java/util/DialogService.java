package util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class DialogService {

    public static void showWarning(String title, String message) {
        showAlert(Alert.AlertType.WARNING, title, message);
    }

    public static void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    public static void showSuccess(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Success", message);
    }

    public static Optional<String> showTextInputDialog(String title, String header, String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return dialog.showAndWait();
    }

    public static Optional<String> showRemoveQuantityDialog(String goodName, int availableQuantity) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Remove Quantity");
        dialog.setHeaderText("Remove: " + goodName);
        dialog.setContentText("Enter quantity to remove (Available: " + availableQuantity + "):");
        return dialog.showAndWait();
    }

    private static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}