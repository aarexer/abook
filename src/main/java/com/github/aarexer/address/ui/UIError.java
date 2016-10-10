package com.github.aarexer.address.ui;

import com.github.aarexer.address.MainApp;
import com.github.aarexer.address.controller.ErrorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class UIError {
    private static Logger logger = LogManager.getLogger();

    public static void showError(String errorMsg, Stage owner) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/ErrorMessage.fxml"));
            AnchorPane errorPane = loader.load();

            ErrorController errorController = loader.getController();
            errorController.setMessageLabel(errorMsg);

            Scene scene = new Scene(errorPane);

            Stage dialogStage = new Stage();

            dialogStage.setResizable(false);
            dialogStage.initOwner(owner);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Ошибка!");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            errorController.setDialogWindow(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            logger.error("Can't find or open /view/ErrorMessage.fxml, cause: {}", e);
        }
    }
}
