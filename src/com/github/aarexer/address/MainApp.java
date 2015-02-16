package com.github.aarexer.address;

import com.github.aarexer.address.model.Person;
import com.github.aarexer.address.view.ErrorController;
import com.github.aarexer.address.view.PersonController;
import com.github.aarexer.address.view.PersonEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application
{

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp()
    {
        personData.add(new Person("Кучук", "Александр"));
        personData.add(new Person("Шеин", "Артем"));
        personData.add(new Person("Романов", "Роман"));
        personData.add(new Person("Ланкин", "Геннадий"));
        personData.add(new Person("Терентьев", "Максим"));
        personData.add(new Person("Тосаков", "Константин"));
        personData.add(new Person("Пинчук", "Денис"));
        personData.add(new Person("Тарасов", "Максим"));
        personData.add(new Person("Скороход", "Сергей"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Address Book");

        initRootlayout();

        showPersonOverview();

    }

    public void initRootlayout()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = fxmlLoader.load();

            rootLayout.setCenter(personOverview);

            PersonController personController = fxmlLoader.getController();
            personController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showError(String msg)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ErrorMessage.fxml"));
            AnchorPane errorMsg = loader.load();
            ErrorController errorController = loader.getController();

            errorController.setMessageLabel(msg);


            Scene scene = new Scene(errorMsg);

            Stage dialogStage = new Stage();

            errorController.setDialogWindow(dialogStage);

            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Ошибка!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showEditDialog(Person person)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));

            AnchorPane editDialog = loader.load();
            Scene scene = new Scene(editDialog);

            Stage editDialogStage = new Stage();

            PersonEditDialogController controller = loader.getController();
            controller.setStage(editDialogStage);
            controller.setPerson(person);
            controller.setMainApp(this);

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

    public ObservableList<Person> getPersonData()
    {
        return personData;
    }

    public static void main(String[] args)
    {
        launch();
    }
}
