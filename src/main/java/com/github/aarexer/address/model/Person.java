package com.github.aarexer.address.model;

import com.github.aarexer.address.util.DateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Person {
    private static final Logger logger = LogManager.getLogger();

    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty city;
    private SimpleStringProperty street;
    private ObjectProperty<LocalDate> birthday;

    public Person() {
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
        this.city = new SimpleStringProperty("");
        this.street = new SimpleStringProperty("");
        this.birthday = new SimpleObjectProperty<>(LocalDate.now());
    }

    public Person(String name, String surname, String city, String street, LocalDate birthday) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.city = new SimpleStringProperty(city);
        this.street = new SimpleStringProperty(street);
        this.birthday = new SimpleObjectProperty<>(birthday);
    }

    public String getName() {
        return name.getValue();
    }

    public String getSurname() {
        return surname.getValue();
    }

    public String getCity() {
        return city.getValue();
    }

    public String getStreet() {
        return street.getValue();
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.getValue();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }

    public void setCity(String city) {
        this.city.setValue(city);
    }

    public void setStreet(String street) {
        this.street.setValue(street);
    }

    public void setBirthday(LocalDate date) {
        this.birthday.setValue(date);
    }

}
