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

public class MainView implements Initializable {
    private final AbbreviationController abbreviationController;
    private final DepartmentController departmentController;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private VBox abbreviationsContainer, newAbbreviationsContainer;

    @FXML
    private TextField searchTextField;

    public MainView() {
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
        String letters = searchTextField.getText();
        String departmentName = filterComboBox.getValue();

        ArrayList<Abbreviation> abbreviations = abbreviationController.filter(letters, departmentName);
        clearAbbreviationBoxes();
        createAbbreviationBoxes(abbreviations);
    }

    private void clearAbbreviationBoxes() {
        abbreviationsContainer.getChildren().clear();
        newAbbreviationsContainer.getChildren().clear();
    }

    private void createAbbreviationBoxes(ArrayList<Abbreviation> abbreviations) {
        for (Abbreviation abbreviation : abbreviations) {
            AnchorPane box = createAbbreviationBox(abbreviation);

            if (abbreviation.isApproved()) {
                abbreviationsContainer.getChildren().add(box);
            } else {
                newAbbreviationsContainer.getChildren().add(box);
            }
        }
    }

    private void putDepartmentNamesInCombo() {
        ObservableList<String> departmentNames = FXCollections.observableArrayList(
                departmentController.getAllDepartmentNames());

        if (!departmentNames.isEmpty()) {
            filterComboBox.setItems(departmentNames);
            filterComboBox.getSelectionModel().select(0);
        }
    }

    private AnchorPane createAbbreviationBox(Abbreviation abbreviation) {
        AnchorPane box = new AnchorPane();
        box.setPrefSize(340, 40);
        box.getStyleClass().add("abbrbox");

        Label letters = new Label(abbreviation.getLetters());
        letters.getStyleClass().add("abbrletters");
        letters.setLayoutX(20);
        letters.setLayoutY(5);

        Label meaning = new Label(abbreviation.getMeaning());
        meaning.setLayoutX(20);
        meaning.setLayoutY(25);

        if (abbreviation.isApproved()) {
            box.getChildren().addAll(letters, meaning);
        } else {
            Button like = new Button();
            like.getStyleClass().addAll("abbricon", "abbrlike");
            like.setPrefSize(30, 30);
            like.setLayoutX(250);
            like.setLayoutY(10);
            like.setOnMouseClicked(e -> {
                abbreviationController.like(abbreviation.getId());
                searchButtonClicked();
            });

            Button dislike = new Button();
            dislike.getStyleClass().addAll("abbricon", "abbrlike");
            dislike.setPrefSize(30, 30);
            dislike.setRotate(180);
            dislike.setLayoutX(290);
            dislike.setLayoutY(10);
            dislike.setOnMouseClicked(e -> {
                abbreviationController.dislike(abbreviation.getId());
                searchButtonClicked();
            });

            box.getChildren().addAll(letters, meaning, like, dislike);
        }

        return box;
    }
}
