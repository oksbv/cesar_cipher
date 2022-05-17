package com.application.utils;
import javafx.scene.control.Alert;
public class Alerts {
    public static Alert infoAlert = new Alert(Alert.AlertType.WARNING);
//    public static final String FILE_NOT_FOUND = "File does not exists";
//    public static final String FILE_OR_KEY_NOT_FOUND = "File or Key does not exists";

    public static void sendErrorFileNotFound() {
        infoAlert.setTitle("");
        infoAlert.setContentText(Constant.FILE_NOT_FOUND.getText());
        infoAlert.getOwner();
        infoAlert.showAndWait();
    }

    public static void sendErrorFileOrKeyNotFound() {
        infoAlert.setTitle("");
        infoAlert.setContentText(Constant.FILE_OR_KEY_NOT_FOUND.getText());
        infoAlert.getOwner();
        infoAlert.showAndWait();
    }
    public static void sendErrorNothingToExport() {
        infoAlert.setTitle("");
        infoAlert.setContentText(Constant.NOTHING_TO_EXPORT.getText());
        infoAlert.getOwner();
        infoAlert.showAndWait();
    }
    public static void sendErrorDecryptionNotCompleted() {
        infoAlert.setTitle("");
        infoAlert.setContentText(Constant.DECRYPTION_NOT_COMPLETED.getText());
        infoAlert.getOwner();
        infoAlert.showAndWait();
    }
}
