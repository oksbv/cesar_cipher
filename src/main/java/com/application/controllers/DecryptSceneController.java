package com.application.controllers;
import com.application.utils.Alerts;
import com.application.utils.Constant;
import com.application.encrypt_decrypt.DecryptText;
import com.application.utils.DecryptionNotCompletedException;
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


public class DecryptSceneController {
    private Path path;
    @FXML
    public Button btnChooseFile;
    @FXML
    public Button btnSubmit;
    @FXML
    public Button btnExport;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField directory;
    @FXML
    private TextField keyField;
    FileChooser fc = new FileChooser();

    public void handleBtnChooseFile(ActionEvent event) {
        directory.setText(null);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(Constant.FILE_EXTENSION_ALL.getText(),
                Constant.FILE_EXTENSION_TXT.getText()));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            directory.appendText(file.getAbsolutePath());
        }
    }

    public void handleBtnSubmit(ActionEvent event) {
        path = Path.of(directory.getText());
        int decryptionKey = KeyEncoderAndDecoder.decodeKey(keyField.getText());
        if (Objects.equals(keyField.getText(), "")) {
            try {
                textArea.appendText(DecryptText.bruteforceDecrypt(path).toString());
            } catch (DecryptionNotCompletedException e) {
                Alerts.sendErrorDecryptionNotCompleted();
            } catch (Exception e) {
                Alerts.sendErrorFileNotFound();
            }
        } else if (decryptionKey != -1) {
            try {
                textArea.appendText(DecryptText.decrypt(path, decryptionKey).toString());
            } catch (Exception e) {
                Alerts.sendErrorFileOrKeyNotFound();
            }
        } else {
            Alerts.sendErrorFileOrKeyNotFound();
            textArea.setText(null);
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


