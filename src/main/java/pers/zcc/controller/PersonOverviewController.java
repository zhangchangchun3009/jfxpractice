package pers.zcc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pers.zcc.model.Person;

public class PersonOverviewController {

    @FXML
    TableView<Person> personTable;

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
    private Label postalCodeLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label birthdayLabel;

    Stage primaryStage;

    List<Person> personList = new ArrayList<>();

    PersonEditController personEditController;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setPersonEditController(PersonEditController personEditController) {
        this.personEditController = personEditController;
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        personList.add(new Person("xiao", "ming", "a", "027", "wh", LocalDate.parse("2000-03-14")));
        personList.add(new Person("li", "hua", "b", "027", "wh", LocalDate.parse("2000-05-14")));
        personList.add(new Person("a", "fa", "c", "027", "wh", LocalDate.parse("2000-08-23")));
        ObservableList<Person> defaultData = FXCollections.observableList(personList);
        personTable.setItems(defaultData);
        showPersonDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showPersonDetails(newValue);
        });
    }

    public void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstNameStr());
            lastNameLabel.setText(person.getLastNameStr());
            streetLabel.setText(person.getStreetStr());
            postalCodeLabel.setText(person.getPostCodeStr());
            cityLabel.setText(person.getCityStr());
            birthdayLabel.setText(person.getBirthdayStr());
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    public void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            showNotSelectedWarning();
        }
    }

    private void showNotSelectedWarning() {
        Alert a = new Alert(Alert.AlertType.WARNING, "Please select a person in the table.", ButtonType.CLOSE);
        a.show();
    }

    @FXML
    public void newPersonDialog() {
        showEditPersonDialog(null, 0);
    }

    @FXML
    public void editPersonDialog() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            showEditPersonDialog(selectedPerson, 1);
        } else {
            showNotSelectedWarning();
        }
    }

    private void showEditPersonDialog(Person selectedPerson, int op) {
        FXMLLoader personEditLoader = new FXMLLoader(getClass().getResource("/PersonEditDialog.fxml"));
        try {
            AnchorPane personEdit = personEditLoader.load();
            PersonEditController personEditController = personEditLoader.getController();
            personEditController.setPersonOverviewController(this);
            personEditController.setOperation(op);
            personEditController.setSelectedPerson(selectedPerson);
            personEditController.showPersonDetails(selectedPerson);
            this.setPersonEditController(personEditController);
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(personEdit);
            dialogStage.setScene(scene);
            personEditController.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
