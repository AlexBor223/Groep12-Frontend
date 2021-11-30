package views;

import controllers.AbbreviationController;
import controllers.DepartmentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Abbreviation;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminView implements Initializable {
    private final AbbreviationController abbreviationController;
    private final DepartmentController departmentController;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private VBox abbreviationsContainer;

    @FXML
    private TextField searchTextField, departmentLettersTextField, departmentNameTextField;

    @FXML
    private Label departmentStatusLabel;

    public AdminView() {
        abbreviationController = new AbbreviationController();
        departmentController = new DepartmentController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        putDepartmentNamesInCombo();
        searchButtonClicked();
    }

    @FXML
    private void searchOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchButtonClicked();
        }
    }

    @FXML
    private void searchButtonClicked() {
        String searchString = searchTextField.getText();
        String departmentName = filterComboBox.getValue();
        ArrayList<Abbreviation> abbreviations = abbreviationController.getAll();

        for (Abbreviation abbreviation : abbreviations) {
            AnchorPane box = createAbbreviationBox(abbreviation);
            abbreviationsContainer.getChildren().add(box);
        }
    }

    @FXML
    private void addDepartmentButtonClicked() {
        String letters = departmentLettersTextField.getText();
        String meaning = departmentNameTextField.getText();

        if (!filledInDepartmentInfo(letters, meaning))
            return;

        // To make sure the adminpanel has the latest departments
        putDepartmentNamesInCombo();

        // TODO: Add department to backend & refresh
    }

    private void putDepartmentNamesInCombo() {
        ObservableList<String> departmentNames = FXCollections.observableArrayList(
                departmentController.getAllDepartmentNames());

        if (!departmentNames.isEmpty()) {
            filterComboBox.setItems(departmentNames);
            filterComboBox.getSelectionModel().select(0);
        }
    }

    private boolean filledInDepartmentInfo(String letters, String meaning) {
        if (letters.isBlank() && meaning.isBlank()) {
            departmentStatusLabel.setText("Vul de velden in!");
            return false;
        }

        if (letters.isBlank()) {
            departmentStatusLabel.setText("Voer de letters in!");
            return false;
        }

        if (meaning.isBlank()) {
            departmentStatusLabel.setText("Voer de naam in!");
            return false;
        }

        return true;
    }

    private AnchorPane createAbbreviationBox(Abbreviation abbreviation) {
        AnchorPane box = new AnchorPane();
        box.setPrefSize(340, 40);

        if (abbreviation.isApproved()) {
            box.getStyleClass().add("abbrbox");
        } else {
            box.getStyleClass().add("unapprovedbox");
        }

        Label letters = new Label(abbreviation.getLetters());
        letters.getStyleClass().add("abbrletters");
        letters.setLayoutX(20);
        letters.setLayoutY(5);

        Label meaning = new Label(abbreviation.getMeaning());
        meaning.setLayoutX(20);
        meaning.setLayoutY(25);

        Button edit = new Button();
        edit.getStyleClass().addAll("abbricon", "abbredit");
        edit.setPrefSize(30, 30);
        edit.setLayoutX(290);
        edit.setLayoutY(10);
        // TODO: Implement setOnMouseClicked and show edit popup

        Button remove = new Button();
        remove.getStyleClass().addAll("abbricon", "abbrremove");
        remove.setPrefSize(30, 30);
        remove.setLayoutX(250);
        remove.setLayoutY(10);
        // TODO: Implement setOnMouseClicked and show confirmation popup

        box.getChildren().addAll(letters, meaning, edit, remove);

        return box;
    }
}
