package com.github.aarexer.address.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {
    private static final Logger logger = LogManager.getLogger();

    private Stage stage;
    @FXML
    private Label messageLabel;
    @FXML
    private Button dialogButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogButton.setText("Ok");
    }

    @FXML
    private void buttonClicked() {
        stage.close();
    }

    public void setDialogWindow(Stage stage) {
        this.stage = stage;
    }

    public void setMessageLabel(String message) {
        messageLabel.setText(message);
    }
}
