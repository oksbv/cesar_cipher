package com.application.controllers;
import com.application.utils.Alerts;
import com.application.utils.Constant;
import com.application.encrypt_decrypt.EncryptText;
import com.application.utils.KeyEncoderAndDecoder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Path;
import java.util.Objects;

public class EncryptSceneController {
    private Path path;
    @FXML
    public Button btnExport;
    @FXML
    public Button btnSubmit;
    @FXML
    public Button btnChooseFile;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField directory;
    @FXML
    private TextField keyField;
    FileChooser fc = new FileChooser();

    public void handleBtnChooseFile(ActionEvent event) {
        directory.setText(null);
        textArea.setText(null);
        keyField.setText(null);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(Constant.FILE_EXTENSION_ALL.getText(),
                Constant.FILE_EXTENSION_TXT.getText()));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            directory.appendText(file.getAbsolutePath());
        }
    }

    public void handleBtnSubmit(ActionEvent event) {
        path = Path.of(directory.getText());
        try {
            textArea.appendText(EncryptText.encrypt(path));
            keyField.setText(KeyEncoderAndDecoder.encodeKey());
        } catch (Exception e) {
            Alerts.sendErrorFileNotFound();
        }
    }

    public void handleBtnExport() {
        File outputFile = fc.showSaveDialog(new Stage());
        if (Objects.equals(textArea.getText(), "")) {
            Alerts.sendErrorNothingToExport();
            return;
        }
        if (outputFile != null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(outputFile)))) {
                bufferedWriter.write(textArea.getText());
            } catch (IOException e) {
                Alerts.sendErrorFileNotFound();
            }
        }
    }
}
