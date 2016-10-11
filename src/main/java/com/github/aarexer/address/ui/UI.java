package com.github.aarexer.address.ui;

import com.github.aarexer.address.MainApp;
import com.github.aarexer.address.controller.ErrorController;
import com.github.aarexer.address.controller.PersonEditDialogController;
import com.github.aarexer.address.controller.StatisticBarController;
import com.github.aarexer.address.model.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UI {
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

    public static boolean showEditDialog(Person person, Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/PersonEditDialog.fxml"));

            AnchorPane editDialog = loader.load();
            Scene scene = new Scene(editDialog);

            Stage editDialogStage = new Stage();

            PersonEditDialogController controller = loader.getController();
            controller.setStage(editDialogStage);

            controller.setPerson(person);
            editDialogStage.setScene(scene);
            editDialogStage.setTitle("Добавление персонажа");
            editDialogStage.initOwner(primaryStage);
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.setResizable(false);
            editDialogStage.showAndWait();

            return controller.getOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void showStatisticBar(Stage primaryStage, List<Person> personData) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/StatisticBar.fxml"));
            AnchorPane statBarPane = loader.load();
            StatisticBarController statBarController = loader.getController();
            statBarController.setDataToChart(personData);
            Scene scene = new Scene(statBarPane);

            Stage statStage = new Stage();

            statStage.initOwner(primaryStage);
            statStage.setScene(scene);
            statStage.setTitle("Статистика!");
            statStage.initModality(Modality.WINDOW_MODAL);
            statStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
