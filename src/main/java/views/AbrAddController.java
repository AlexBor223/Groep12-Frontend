package views;

import dao.AbbreviationDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Abbreviation;

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

    Abbreviation abbreviation = new Abbreviation();
    WindowController windowController = new WindowController();
    AbbreviationDao abbreviationDao = new AbbreviationDao();

    ObjectMapper objectMapper = new ObjectMapper();


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

        try {
            abbreviationDao.getAllAbbreviations();  //voor testen
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {           //voor testen
//            likeAbbreviation(1L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public void loadAdminPage (){
        windowController.showWindow("LoginPage", "Inlog admin");

    }

    public void getInput(javafx.event.ActionEvent actionEvent) throws Exception {
        String AbbreviationL = AbbreviationLetters.getText();
        String Meaning = AbbreviationMeaning.getText();
        String Department = ChooseDep.getValue();

        abbreviation.setLetters(AbbreviationL);
        abbreviation.setMeaning(Meaning);
        abbreviation.setDepartment(Department);
        abbreviation.setLikes(0);


        checkInput();
    }

   public void checkInput() throws Exception {

        if(abbreviation.getLetters().isEmpty() || abbreviation.getMeaning().isEmpty()  || abbreviation.getDepartment() == null){
            StatusText.setText(fillInFields);

        }else {

            StatusText.setText(AbrAdded);
//            String jsonStr = objectMapper.writeValueAsString(abbreviation);
//            System.out.println(jsonStr);  print input van abbreviation uit
            abbreviationDao.updateAbbreviation(abbreviation);


        }


//        removeAbbreviation(3L);
//        disLikeAbbreviation(1L); //Voor testen


   }

   public void likeAbbreviation(Long id) throws Exception {
    abbreviationDao.LikeAbbreviation(id);

   }

   public void dislikeAbbreviation(Long id) throws Exception {
    abbreviationDao.DislikeAbbreviation(id);

   }

   public void removeAbbreviation(Long id) throws Exception {
        abbreviationDao.deleteAbbreviation(id);
   }


}
