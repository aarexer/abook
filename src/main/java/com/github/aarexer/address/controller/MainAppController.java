package com.github.aarexer.address.controller;

import com.github.aarexer.address.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable
{

    private MainApp mainiApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void exitButton()
    {
        Platform.exit();
    }

    public void setMainiApp(MainApp app)
    {
        mainiApp = app;
    }

    @FXML
    private void newButton()
    {
        mainiApp.getPersonData().clear();
        mainiApp.setPersonPath(null);
    }

    @FXML
    private void loadButton()
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(extensionFilter);

        File file = fileChooser.showOpenDialog(mainiApp.getPrimaryStage());

        if (file != null)
            mainiApp.loadDataFromFile(file);
    }

    @FXML
    private void saveButton()
    {
        File file = mainiApp.getPersonPath();
        if (file != null)
            mainiApp.saveDataToFile(file);
        else
            saveAS();

    }

    private void saveAS()
    {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(extensionFilter);

        File file = fileChooser.showOpenDialog(mainiApp.getPrimaryStage());

        if (file != null)
        {
            if (!file.getPath().endsWith(".xml"))
            {
                file = new File(file.getPath() + ".xml");
            }
            mainiApp.saveDataToFile(file);
        }
    }

    @FXML
    private void showStatistic()
    {
        mainiApp.showStatisticBar();
    }
}
