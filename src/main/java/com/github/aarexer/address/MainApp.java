package com.github.aarexer.address;

import com.github.aarexer.address.controller.MainAppController;
import com.github.aarexer.address.controller.PersonController;
import com.github.aarexer.address.controller.PersonEditDialogController;
import com.github.aarexer.address.controller.StatisticBarController;
import com.github.aarexer.address.model.Person;
import com.github.aarexer.address.model.PersonDataWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    private static final Logger logger = LogManager.getLogger();

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Address Book");

        logger.info("Starting application...");
        initRootLayout();
        logger.info("Application started.");
    }

    private void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);

            MainAppController controller = fxmlLoader.getController();
            controller.setMainApp(this);

            primaryStage.getIcons().add(new Image("/images/icon.png"));
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (IOException e) {
            logger.error("Can't load resource {}, cause: {}!", "/view/RootLayout.fxml", e);
            throw new RuntimeException("Error while loading resource.", e);
        }

        //after init main window
        showPersonOverview();

        File file = getLastOpenedPersonsFile();
        if (file != null)
            loadDataFromFile(file);
    }

    private void showPersonOverview() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("/view/PersonOverview.fxml"));
            AnchorPane personOverview = fxmlLoader.load();

            rootLayout.setCenter(personOverview);

            PersonController personController = fxmlLoader.getController();
            personController.setMainApp(this);

        } catch (IOException e) {
            logger.error("Can't load resource {}, cause: {}!", "/view/PersonOverview.fxml", e);
            throw new RuntimeException("Error while loading resource.", e);
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Try to get last opened file.
     * @return File if file with persons was opened in past, otherwise - null.
     */
    public File getLastOpenedPersonsFile() {
        Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
        String filepath = preferences.get("filepath", null);
        if (filepath != null) {
            return new File(filepath);
        } else {
            return null;
        }
    }

    public void setPersonPath(File file) {
        Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            preferences.put("filepath", file.getPath());
            primaryStage.setTitle("AddressBook " + file.getName());
        } else {
            preferences.remove("filepath");
            primaryStage.setTitle("Addressbook");
        }
    }

    public void saveDataToFile(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonDataWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonDataWrapper personDataWrapper = new PersonDataWrapper();
            personDataWrapper.setPersons(personData);

            marshaller.marshal(personDataWrapper, file);

            setPersonPath(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonDataWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            PersonDataWrapper personDataWrapper = (PersonDataWrapper) unmarshaller.unmarshal(file);
            personData.clear();
            personData.addAll(personDataWrapper.getPersons());

            setPersonPath(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showStatisticBar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/StatisticBar.fxml"));
            AnchorPane statBarPane = loader.load();
            StatisticBarController statBarController = loader.getController();
            statBarController.setMainApp(this);
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
