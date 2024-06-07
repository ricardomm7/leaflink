package pt.ipp.isep.dei.project.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import pt.ipp.isep.dei.project.Main;

import java.util.Objects;

public abstract class ShowError {
    public static void showAlert(String title, String message, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        // Carrega o CSS
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(Main.class.getResource("alert-style.css")).toExternalForm()
        );
        dialogPane.getStyleClass().add("custom-alert");

        alert.showAndWait();
    }

    public static void showAlertConfirm(String title, String message, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        // Carrega o CSS
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(Main.class.getResource("alert-style.css")).toExternalForm()
        );
        dialogPane.getStyleClass().add("custom-alert");

        alert.showAndWait();
    }
}
