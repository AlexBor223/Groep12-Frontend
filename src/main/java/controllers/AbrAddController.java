package controllers;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Abbreviation;
import models.NewAbbreviation;

import java.net.URL;
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
    private Text StatusText;

    NewAbbreviation abbreviation = new NewAbbreviation();
    WindowController windowController = new WindowController();

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

    public void loadAdminPage (){

        windowController.showWindow("LoginPage", "Inlog admin");

    }

    public void getInput(javafx.event.ActionEvent actionEvent) {
        String AbbreviationL = AbbreviationLetters.getText();
        String Meaning = AbbreviationMeaning.getText();
        String Department = ChooseDep.getValue();



        abbreviation.setLetters(AbbreviationL);
        abbreviation.setMeaning(Meaning);
        abbreviation.setDepartment(Department);
        abbreviation.setLikes(0);


        System.out.printf(abbreviation.getMeaning() + abbreviation.getDepartment() + abbreviation.getLetters());

        checkInput();
    }

   public void checkInput(){
        if(abbreviation.getLetters().isEmpty() || abbreviation.getMeaning().isEmpty()  || abbreviation.getDepartment() == null){
            StatusText.setText(fillInFields);

        }else {

            StatusText.setText(AbrAdded);




        }



   }


}
