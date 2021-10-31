package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class AbrSearchController implements Initializable {

    @FXML private TextField input;
    @FXML private ComboBox<String> comboBox;

    private final String AbbreviationStylingId = "abbreviation";
    private final String DepartmentStylingId = "department";
    private final String AbbreviationBoxStylingId= "abbreviationBox";

    ObservableList<String> Departments =
            FXCollections.observableArrayList(
                    "ict",
                    "politie",
                    "buitenlandse zaken"
            );

    @FXML private VBox abbreviations;
    @FXML private VBox newAbbreviations;

    @Override

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input.requestFocus();
        comboBox.setItems(Departments);
        String[] comboBoxOptions = {"ict","politie","buitenlandse zaken"};




        input.textProperty().addListener((observable, oldValue, newValue) -> {
            abbreviations.getChildren().clear();
            newAbbreviations.getChildren().clear();
            if(!newValue.isEmpty()){
                String[] testAbr = newValue.split("");
                for (int i = 0; i < testAbr.length; i++) {
                    insertAbbreviation(testAbr[i], "test", i);
                }
                for (int i = 0; i < testAbr.length; i++) {
                    InsertNewAbbreviation(testAbr[i], "test", i);
                }
            }
       });

    }

    public void insertAbbreviation(String AbbreviationMeaning, String department, int AbrId){
        AnchorPane abbreviationBox = createBaseAbbreviationBox(AbbreviationMeaning, department, AbrId);

        abbreviations.getChildren().add(abbreviationBox);
    }

    public void InsertNewAbbreviation(String AbbreviationMeaning, String department, int AbrId){
        AnchorPane abbreviationBox = createBaseAbbreviationBox(AbbreviationMeaning, department, AbrId);

        newAbbreviations.getChildren().add(abbreviationBox);
    }

    public AnchorPane createBaseAbbreviationBox(String AbbreviationMeaning, String department, int AbrId){
        AnchorPane abbreviationBox = new AnchorPane();
        Text Meaning = new Text();
        Text Department = new Text();

        Meaning.setText(AbbreviationMeaning);
        Department.setText(department);

        abbreviationBox.setPrefHeight(30);
        abbreviationBox.setPrefWidth(200);

        Meaning.setLayoutX(25);
        Meaning.setLayoutY(25);

        Department.setLayoutX(35);
        Department.setLayoutY(45);

        abbreviationBox.setId(AbbreviationBoxStylingId);
        Meaning.setId(AbbreviationStylingId);
        Department.setId(DepartmentStylingId);

        abbreviationBox.getChildren().add(Meaning);
        abbreviationBox.getChildren().add(Department);

        return abbreviationBox;
    }
}
