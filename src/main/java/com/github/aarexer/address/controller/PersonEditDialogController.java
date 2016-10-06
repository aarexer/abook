package com.github.aarexer.address.controller;

import com.github.aarexer.address.MainApp;
import com.github.aarexer.address.model.Person;
import com.github.aarexer.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonEditDialogController implements Initializable {
    private Stage stage;
    private Person person;
    private MainApp mainApp;
    private boolean okClicked = false;

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField birthdayField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void okClicked() {
        if (isValid()) {
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            person.setName(firstnameField.getText());
            person.setSurname(lastnameField.getText());
            person.setCity(cityField.getText());
            person.setStreet(streetField.getText());
            okClicked = true;
            stage.close();
        }
    }

    @FXML
    private void cancelClicked() {
        stage.close();
    }

    public boolean getOkClicked() {
        return okClicked;
    }

    private boolean isValid() {
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();
        String city = cityField.getText();
        String street = streetField.getText();
        String birthday = birthdayField.getText();

        return !(isEmptyOrNull(firstName) ||
                isEmptyOrNull(lastName) ||
                isEmptyOrNull(city) ||
                isEmptyOrNull(street) ||
                isEmptyOrNull(birthday)) && DateUtil.validDate(birthday);
    }

    private boolean isEmptyOrNull(String field) {
        return field == null || field.trim().isEmpty();
    }

    public void setPerson(Person person) {
        this.person = person;
        firstnameField.setText(person.getName());
        lastnameField.setText(person.getSurname());
        cityField.setText(person.getCity());
        streetField.setText(person.getStreet());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
