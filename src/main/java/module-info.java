module com.example.cipher {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.application;
    opens com.application to javafx.fxml;
    exports com.application.controllers;
    opens com.application.controllers to javafx.fxml;
    exports com.application.utils;
    opens com.application.utils to javafx.fxml;
    exports com.application.encrypt_decrypt;
    opens com.application.encrypt_decrypt to javafx.fxml;
}