package pers.zcc.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty firstName = new SimpleStringProperty("");

    private final StringProperty lastName = new SimpleStringProperty("");

    private final StringProperty street = new SimpleStringProperty("");

    private final StringProperty postCode = new SimpleStringProperty("");

    private final StringProperty city = new SimpleStringProperty("");

    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();

    public Person() {
        super();
    }

    public Person(String firstName, String lastName) {
        super();
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public Person(String firstName, String lastName, String street, String postCode, String city, LocalDate birthday) {
        super();
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.street.set(street);
        this.postCode.set(postCode);
        this.city.set(city);
        this.birthday.set(birthday);
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getStreet() {
        return street;
    }

    public StringProperty getPostCode() {
        return postCode;
    }

    public StringProperty getCity() {
        return city;
    }

    public ObjectProperty<LocalDate> getBirthday() {
        return birthday;
    }

    public String getFirstNameStr() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastNameStr() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getStreetStr() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getPostCodeStr() {
        return postCode.get();
    }

    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }

    public String getCityStr() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.setValue(city);
    }

    public String getBirthdayStr() {
        return birthday.get().toString();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

}
