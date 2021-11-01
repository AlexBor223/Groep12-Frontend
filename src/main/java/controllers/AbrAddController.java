package controllers;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
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
    @FXML
    private Text StatusText;

    ArrayList<String> abbreviation = new ArrayList<>();

    private String noAbbreviation = "Geef een afkorting mee";
    private String noExplanation = "Geef een uitleg van je afkorting mee";
    private String selectDepartment = "Selecteerd u eerst een afdeling";
    private String fillInFields = "Vul alle velden goed in";
    private String AbrAdded = "Afkorting succesvol toegevoegd";

    Gson gson = new Gson();


    ObservableList<String> Departments =
            FXCollections.observableArrayList(
                    "Ministerie van Algemene Zaken",
                    "Ministerie van Binnenlandse Zaken en Koninkrijksrelaties",
                    "Ministerie van Buitenlandse Zaken",
                    "Ministerie van Defensie",
                    "Ministerie van Economische Zaken en Klimaat",
                    "Ministerie van Financiën",
                    "Ministerie van Infrastructuur en Waterstaat",
                    "Ministerie van Justitie en Veiligheid",
                    "Ministerie van Landbouw, Natuur en Voedselkwaliteit",
                    "Ministerie van Onderwijs, Cultuur en Wetenschap",
                    "Ministerie van Sociale Zaken en Werkgelegenheid",
                    "Ministerie van Volksgezondheid, Welzijn en Sport"


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

        checkInput();
    }

   public void checkInput(){
        if(abbreviation.get(0).isEmpty() || abbreviation.get(1).isEmpty()  || abbreviation.get(2) == null){
            StatusText.setText(fillInFields);

        }else {
            StatusText.setText(AbrAdded);

            // abbreviation word naar de backend gestuurd

        }

        abbreviation.clear();

   }


}
