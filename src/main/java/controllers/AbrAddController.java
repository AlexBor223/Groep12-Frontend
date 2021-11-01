package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import models.Abbreviation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbrAddController implements Initializable {

    @FXML
    private ComboBox<String> ChooseDep;
    @FXML
    private TextField AbbreviationLetters;
    @FXML
    private TextField AbbreviationMeaning;
    @FXML
    private Button AbrToApp;
    @FXML
    private Label LabelAdd;

    ArrayList<String> abbreviation = new ArrayList<>();


    ObservableList<String> Departments =
            FXCollections.observableArrayList(
                    "Politie",
                    "Justitie",
                    "Mark rutte",
                    "Hugo"
            );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChooseDep.setItems(Departments);

    }


    public void getInput(javafx.event.ActionEvent actionEvent) {
        String AbbreviationL = AbbreviationLetters.getText();
        String Meaning = AbbreviationMeaning.getText();
        String Department = ChooseDep.getValue();



        abbreviation.add(AbbreviationL);
        abbreviation.add(Meaning);
        abbreviation.add(Department);


        System.out.printf(abbreviation.toString());

    }



}
