package com.github.aarexer.address.view;

import com.github.aarexer.address.MainApp;
import com.github.aarexer.address.model.Person;
import com.github.aarexer.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rexer on 14.02.15.
 */


public class PersonController implements Initializable
{
    private MainApp mainApp;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label streetLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label birthdayLabel;


    @FXML
    private void handleDeleteButton()
    {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            tableView.getItems().remove(selectedIndex);
        }
        else {
            mainApp.showError("Нет доступной записи!");
        }
    }

    @FXML
    private void handleNewButton()
    {
        Person person = new Person();
        if (mainApp.showEditDialog(person))
            mainApp.getPersonData().add(person);
    }

    @FXML
    private void handleEditButton()
    {
        Person person = tableView.getSelectionModel().selectedItemProperty().get();

        if (person == null)
            mainApp.showError("Вы никого не выбрали!");

        mainApp.showEditDialog(person);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        showPersonDetails(null);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
        tableView.setItems(mainApp.getPersonData());
    }

    public void showPersonDetails(Person person)
    {
        if (person != null)
        {
            firstNameLabel.setText(person.getName());
            lastNameLabel.setText(person.getSurname());
            cityLabel.setText(person.getCity());
            streetLabel.setText(person.getStreet());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        }
        else
        {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            cityLabel.setText("");
            streetLabel.setText("");
            birthdayLabel.setText("");
        }
    }



}
