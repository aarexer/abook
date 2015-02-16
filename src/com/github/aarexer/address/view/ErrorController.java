package com.github.aarexer.address.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rexer on 15.02.15.
 */


public class ErrorController implements Initializable
{

    private Stage stage;

    @FXML
    private Label messageLabel;

    @FXML
    private Button dialogButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogButton.setText("Ok");
    }

    public void setDialogWindow(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    private void buttonClicked()
    {
        stage.close();
    }

    public void setMessageLabel(String message)
    {
        messageLabel.setText(message);
    }
}
