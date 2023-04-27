package pers.zcc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pers.zcc.model.Person;

public class PersonEditController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField birthdayField;

    PersonOverviewController personOverviewController;

    Person selectedPerson;

    int operation;

    Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController) {
        this.personOverviewController = personOverviewController;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    @FXML
    private void initialize() {
    }

    public void showPersonDetails(Person person) {
        if (person != null) {
            firstNameField.setText(person.getFirstNameStr());
            lastNameField.setText(person.getLastNameStr());
            streetField.setText(person.getStreetStr());
            postalCodeField.setText(person.getPostCodeStr());
            cityField.setText(person.getCityStr());
            birthdayField.setText(person.getBirthdayStr());
        } else {
            firstNameField.setText("");
            lastNameField.setText("");
            streetField.setText("");
            postalCodeField.setText("");
            cityField.setText("");
            birthdayField.setText("");
        }
    }

    @FXML
    public void handleOK() {
        String errMsg = isInputValid();
        if (errMsg == null || "".equals(errMsg)) {
            if (operation == 0) {
                Person person = new Person(firstNameField.getText(), lastNameField.getText(), streetField.getText(),
                        postalCodeField.getText(), cityField.getText(), LocalDate.parse(birthdayField.getText()));
                personOverviewController.personTable.getItems().add(person);
            } else if (operation == 1) {
                selectedPerson.setFirstName(firstNameField.getText());
                selectedPerson.setLastName(lastNameField.getText());
                selectedPerson.setCity(cityField.getText());
                selectedPerson.setStreet(streetField.getText());
                selectedPerson.setPostCode(postalCodeField.getText());
                selectedPerson.setBirthday(LocalDate.parse(birthdayField.getText()));
                personOverviewController.showPersonDetails(selectedPerson);
            }
            dialogStage.close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, errMsg, ButtonType.CLOSE);
            a.show();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    private String isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            try {
                sdf.parse(birthdayField.getText());
            } catch (ParseException e) {
                errorMessage += "No valid birthday. Use the format yyyy-MM-dd!\n";
            }
        }
        return errorMessage;
    }

}
