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
import models.Abbreviation;
import models.Department;

import javax.swing.*;
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

    @FXML
    private Button inputButton;


    private String meaningCheck;
    private int exponentialInt = 1;
    private int multiplyInt = 1;

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
        String tempMeaning = meaningTextField.getText();

        if (meaningCheck != null && meaningCheck.equals(tempMeaning)) {

            statusLabel.setText("Je mag niet 2 keer dezelfde afkorting invoeren");
            return;
        }

        if (!filledInInfo(letters, meaning, departmentId))
            return;

        // Create a new abbreviation
        Abbreviation abbreviation = new Abbreviation(departmentId, letters, meaning, 0);
        abbreviationController.create(abbreviation);
        statusLabel.setText("Afkorting toegevoegd!");
        meaningCheck = abbreviation.getMeaning();

        disableButtonAndUpdateTime();
    }

    public void disableButtonAndUpdateTime() {
        disable(inputButton, multiplyInt * 2000L);
        updateWaitInt(exponentialInt);
        exponentialInt += 1;
    }

    private void putDepartmentNamesInCombo() {
        ObservableList<String> departmentNames = FXCollections.observableArrayList(getDepartmentNames());

        if (!departmentNames.isEmpty()) {
            departmentComboBox.setItems(departmentNames);
            departmentComboBox.getSelectionModel().select(0);
        }
    }

    public boolean filledInInfo(String letters, String meaning, long departmentId) {
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

    /**
     * Disables the "invoer" button for a specific amount of time
     *
     * @param b  the button you want to disable
     * @param ms the amount of milleseconds you want to disable the button
     * @author Martin
     */
    static void disable(Button b, final long ms) {
        if (b != null) {                                //voor unit test
            b.setDisable(true);
            new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    Thread.sleep(ms);
                    return null;
                }

                @Override
                protected void done() {
                    b.setDisable(false);
                }
            }.execute();
        }
    }

    /**
     * sum to calculate exponential grow and assign it to a integer
     *
     * @param exInt is the exponential grow factor
     * @author Martin
     */
    public int updateWaitInt(int exInt) {
        multiplyInt = (int) Math.pow(2, exInt);
        return multiplyInt;

    }
}
