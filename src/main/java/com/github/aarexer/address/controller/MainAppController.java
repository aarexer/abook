package com.github.aarexer.address.controller;

import com.github.aarexer.address.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    private static final Logger logger = LogManager.getLogger();

    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void exitButton() {
        Platform.exit();
    }

    @FXML
    private void newButton() {
        mainApp.getPersonData().clear();
        mainApp.setPersonPath(null);
    }

    @FXML
    private void loadButton() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(extensionFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadDataFromFile(file);
        }
    }

    @FXML
    private void saveButton() {
        File file = mainApp.getPersonPath();
        if (file != null) {
            mainApp.saveDataToFile(file);
        } else {
            saveAS();
        }
    }

    @FXML
    private void showStatistic() {
        mainApp.showStatisticBar();
    }

    private void saveAS() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(extensionFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveDataToFile(file);
        }
    }

    public void setMainApp(MainApp app) {
        mainApp = app;
    }
}
