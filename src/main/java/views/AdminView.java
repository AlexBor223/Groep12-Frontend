package views;

import controllers.AbbreviationController;
import controllers.DepartmentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import models.Department;
import services.HttpService;
import services.LoginService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminView implements Initializable {
    private final AbbreviationController abbreviationController;
    private final DepartmentController departmentController;
    private final HttpService httpService;
    private final LoginService loginService;
    private String url;
    private ArrayList<Department> departments;
    private boolean approved;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private VBox abbreviationsContainer;

    @FXML
    private TextField searchTextField, departmentLettersTextField, departmentNameTextField;

    @FXML
    private Label departmentStatusLabel;

    @FXML
    private TextField lettersTextField, meaningTextField;

    @FXML
    private ComboBox<String> departmentComboBoxAbbreviations;

    @FXML
    private Label statusLabel;

    public AdminView() {
        abbreviationController = new AbbreviationController();
        departmentController = new DepartmentController();
        httpService = HttpService.getInstance();
        loginService = LoginService.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departments = departmentController.getAllDepartments();
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
        String letters = searchTextField.getText();
        String departmentName = filterComboBox.getValue();

        ArrayList<Abbreviation> abbreviations = abbreviationController.filter(letters, departmentName);
        clearAbbreviationBoxes();
        createAbbreviationBoxes(abbreviations);
    }

    @FXML
    private void addAbbreviationButtonClicked() {
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

    @FXML
    public void addDepartmentAdminButtonClicked(ActionEvent actionEvent) {
        String letters = departmentLettersTextField.getText();
        String meaning = departmentNameTextField.getText();

        if (!filledInDepartmentInfo(letters, meaning))
            return;

        // TODO: Add department to backend & refresh
        LoginService loginService = LoginService.getInstance();
        if (loginService.getAccessToken() != null) {
            try {
                url = "/api/departments";
                Department params = new Department(letters, meaning);
                httpService.postResponse(url, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // To make sure the adminpanel has the latest departments
        putDepartmentNamesInCombo();
    }

    @FXML
    private void addDepartmentButtonClicked() {
        String name = departmentNameTextField.getText();
        String letters = departmentLettersTextField.getText();

        if (!filledInDepartmentInfo(letters, name))
            return;

        // TODO: Add department to backend & refresh
        LoginService loginService = LoginService.getInstance();
        if (loginService.getAccessToken() != null) {
            try {
                url = "/api/departments";
                Department params = new Department(name, letters);
                httpService.postResponse(url, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // To make sure the adminpanel has the latest departments
        putDepartmentNamesInCombo();
    }

    private void putDepartmentNamesInCombo() {
        ObservableList<String> departmentNames = FXCollections.observableArrayList(
                departmentController.getAllDepartmentNames());

        if (!departmentNames.isEmpty()) {
            filterComboBox.setItems(departmentNames);
            departmentComboBoxAbbreviations.setItems(departmentNames);
            filterComboBox.getSelectionModel().select(0);
            departmentComboBoxAbbreviations.getSelectionModel().select(0);
        }
    }

    @FXML
    private void addAbbreviationButtonClickedAdmin() {
        String letters = lettersTextField.getText().strip();
        String meaning = meaningTextField.getText().strip();
        long departmentId = getDepartmentId();
        if (loginService.getAccessToken()!=null){
            approved = true;
        } else {
            statusLabel.setText("Log opnieuw in");
            approved = false;
        }

        if (!filledInInfo(letters, meaning, departmentId))
            return;

        // Create a new abbreviation
        Abbreviation abbreviation = new Abbreviation(departmentId, letters, meaning, 0, approved);
        abbreviationController.create(abbreviation);
        statusLabel.setText("Afkorting toegevoegd!");

        searchButtonClicked();
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

    private void editButtonClicked(Abbreviation abbreviation) {
        Abbreviation editAbbreviation = abbreviationController.clone(abbreviation);
        EditPopupView editPopup = new EditPopupView(editAbbreviation);
        editPopup.setWindowTitle(String.format("Bewerken Afkorting [%s]", abbreviation.getLetters()));
        editPopup.showAndWait();

        if (!editAbbreviation.equals(abbreviation) && editPopup.getClickedSave()) {
            abbreviationController.update(editAbbreviation);

            searchButtonClicked();
        }
    }

    private void removeButtonClicked(Abbreviation abbreviation) {
        DeletePopupView deletePopup = new DeletePopupView();
        deletePopup.setWindowTitle(String.format("Verwijder Afkorting [%s]", abbreviation.getLetters()));
        deletePopup.showAndWait();

        if (deletePopup.getClickedDelete())
            abbreviationController.delete(abbreviation.getId());

        searchButtonClicked();
    }

    private void clearAbbreviationBoxes() {
        abbreviationsContainer.getChildren().clear();
    }

    private void createAbbreviationBoxes(ArrayList<Abbreviation> abbreviations) {
        for (Abbreviation abbreviation : abbreviations) {
            AnchorPane box = createAbbreviationBox(abbreviation);
            abbreviationsContainer.getChildren().add(box);
        }
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
        edit.setOnMouseClicked(e -> editButtonClicked(abbreviation));

        Button remove = new Button();
        remove.getStyleClass().addAll("abbricon", "abbrremove");
        remove.setPrefSize(30, 30);
        remove.setLayoutX(250);
        remove.setLayoutY(10);
        remove.setOnMouseClicked(e -> removeButtonClicked(abbreviation));

        box.getChildren().addAll(letters, meaning, edit, remove);

        return box;
    }

    private long getDepartmentId() {
        String name = departmentComboBoxAbbreviations.getValue();

        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return department.getId();
            }
        }

        return -1;
    }
}
