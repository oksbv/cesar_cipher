package com.application.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeSceneController implements Initializable {
    @FXML
    public ToggleGroup toggleButtons;
    public ToggleButton btnEncrypt;
    public ToggleButton btnDecrypt;
    private AnchorPane view;
    @FXML
    private BorderPane borderPane;

    public void handleBtnDecrypt(ActionEvent event) throws IOException {
        view = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("/com/application/scene-decrypt.fxml")));
        borderPane.setCenter(view);
    }

    public void handleBtnEncrypt(ActionEvent event) throws IOException {
        view = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("/com/application/scene-encrypt.fxml")));
        borderPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            view = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/com/application/scene-decrypt.fxml")));
            borderPane.setCenter(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

