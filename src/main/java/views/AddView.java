package views;

import controllers.AbbreviationController;
import controllers.DepartmentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Abbreviation;
import models.Department;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddView implements Initializable {
    private final AbbreviationController abbreviationController;
    private final DepartmentController departmentController;
    private ArrayList<Department> departments;

    @FXML
    private TextField lettersTextField, meaningTextField;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private Label statusLabel;

    public AddView() {
        abbreviationController = new AbbreviationController();
        departmentController = new DepartmentController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departments = departmentController.getAllDepartments();
        putDepartmentNamesInCombo();
    }

    @FXML
    private void addButtonClicked() {
        String letters = lettersTextField.getText().strip();
        String meaning = meaningTextField.getText().strip();
        long departmentId = getDepartmentId();

        if (!filledInInfo(letters, meaning, departmentId))
            return;

        // Create a new abbreviation
        Abbreviation abbreviation = new Abbreviation(departmentId, letters, meaning, 0);
        abbreviationController.create(abbreviation);
        statusLabel.setText("Afkorting toegevoegd!");
    }

    private void putDepartmentNamesInCombo() {
        ObservableList<String> departmentNames = FXCollections.observableArrayList(getDepartmentNames());

        if (!departmentNames.isEmpty()) {
            departmentComboBox.setItems(departmentNames);
            departmentComboBox.getSelectionModel().select(0);
        }
    }

    private boolean filledInInfo(String letters, String meaning, long departmentId) {
        if (letters.isBlank() && meaning.isBlank()) {
            statusLabel.setText("Vul de velden in!");
            return false;
        }

        if (letters.isBlank()) {
            statusLabel.setText("Voer de letters in!");
            return false;
        }

        if (meaning.isBlank()) {
            statusLabel.setText("Voer de betekenis in!");
            return false;
        }

        if (departmentId == -1) {
            // Only possible when there's no connection or an error occurred
            statusLabel.setText("Er is een fout opgetreden!");
            return false;
        }

        return true;
    }

    private ArrayList<String> getDepartmentNames() {
        ArrayList<String> departmentNames = new ArrayList<>();
        departments.forEach((Department department) -> departmentNames.add(department.getName()));
        return departmentNames;
    }

    private long getDepartmentId() {
        String name = departmentComboBox.getValue();

        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return department.getId();
            }
        }

        return -1;
    }
}
