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
        String errorMsg = "";
        if (firstnameField.getText().length() == 0 || firstnameField.getText() == null)
            errorMsg += "Нет имени! ";

        if (lastnameField.getText().length() == 0 || lastnameField.getText() == null)
            errorMsg += "Нет фамилии! ";

        if (cityField.getText().length() == 0 || cityField.getText() == null)
            errorMsg += "Нет города! ";

        if (streetField.getText().length() == 0 || streetField.getText() == null)
            errorMsg += "Нет улицы! ";

        if (birthdayField.getText().length() == 0 || streetField.getText() == null) {
            errorMsg += "Нет даты! ";
        } else {
            if (!DateUtil.validDate(birthdayField.getText()))
                errorMsg += "Ошибка ввода даты!";
        }

        if (errorMsg.length() == 0)
            return true;
        else
            return false;
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
