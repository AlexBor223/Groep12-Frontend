package controllers;

import Dao.AbbreviationDao;
import Dao.DepartmentDao;
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
import models.Abbreviation;
import models.DepartmentModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class AbrSearchController implements Initializable {

    @FXML private TextField input;
    @FXML private ComboBox<String> comboBox;

    private final String AbbreviationStylingId = "abbreviation";
    private final String DepartmentStylingId = "department";
    private final String AbbreviationBoxStylingId= "abbreviationBox";

    private AbbreviationDao abrDao = new AbbreviationDao();
    private DepartmentDao depDao = new DepartmentDao();

    private String SearchedID;
    private String SearchedDepartment;


    @FXML private VBox abbreviations;
    @FXML private VBox newAbbreviations;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input.requestFocus();
        ArrayList<DepartmentModel> DepartmentArray = depDao.GetAllDepartments();
        ObservableList<String> options = FXCollections.observableArrayList();
//        for (DepartmentModel department:DepartmentArray){
//            options.add(department.getName());
//        }

        comboBox.setItems(options);

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchedID = newValue;
            updateAbbreviationBoxes();
       });
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SearchedDepartment = newValue;
            updateAbbreviationBoxes();
        });

    }

    public void updateAbbreviationBoxes(){
        abbreviations.getChildren().clear();
        newAbbreviations.getChildren().clear();
        ArrayList<Abbreviation> localAbr= getAbbreviations();
        if(localAbr!=null) {
            for (Abbreviation abr : localAbr) {
                System.out.println(abr.getDepartment());
                insertAbbreviation(abr.getLetters() + "  " + abr.getMeaning(), abr.getDepartment(), (int) abr.getId());
            }
        }
    }

    public ArrayList<Abbreviation> getAbbreviations(){

        return abrDao.searchAbbreviations(SearchedID, SearchedDepartment);
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
