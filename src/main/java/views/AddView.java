package views;

import controllers.AbrAddController;
import controllers.AbrSearchController;
import controllers.WindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Abbreviation;
import models.DepartmentModel;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddView implements Initializable {


    /**
     * loads all the fxml attributes
     */
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

    private final String fillInFields = "Vul alle velden goed in";
    private final String AbrAdded = "Afkorting succesvol toegevoegd";
    private final String notTheSameField = "Je mag niet 2 keer dezelfde afkorting invoeren";

    private String meaningCheck;
    private String tempMeaning;

    /**
     * the view of the abbreviation search window and the first window that's loaded upon start
     *
     * @author Martin
     */

    WindowController windowController = new WindowController();
    AbrAddController abrAddController = new AbrAddController();
    AbrSearchController abrSearchController = new AbrSearchController();


    ArrayList<DepartmentModel> DepartmentArray = abrSearchController.getAllDepartments();
    ObservableList<String> options = FXCollections.observableArrayList();


    /**
     * in the making of the screen load the combobox with items
     *
     * @param url            link of the fxml pane
     * @param resourceBundle
     * @author Martin
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (DepartmentModel department : DepartmentArray) {
            options.add(department.getName());
        }
        ChooseDep.setItems(options);


    }

    /**
     * loads in the adminpage
     *
     * @author Martin
     */
    public void loadAdminPage() {
        windowController.showWindow("LoginPage", "Inlog admin");

    }

    /**
     * gets the input of the filled in fields to add the abbreviation
     *
     * @param
     * @author Martin
     */
    public void getInput() throws Exception {

        Abbreviation abbreviation = new Abbreviation();
        abbreviation.setLetters(AbbreviationLetters.getText());
        abbreviation.setMeaning(AbbreviationMeaning.getText());
        abbreviation.setDepartment(ChooseDep.getValue());
        abbreviation.setLikes(0);
        tempMeaning = AbbreviationMeaning.getText();

        if (meaningCheck != null && meaningCheck.equals(tempMeaning)){
            StatusText.setText(notTheSameField);
            System.out.println("zm r");
        }else{
            checkInput(abbreviation);
        }


    }

    /**
     * checks if the input is you gave is not empty then sends the abrreviation to the controller
     *
     * @author Martin
     */
    public void checkInput(Abbreviation abbreviation) throws Exception {

        if (abbreviation.getLetters().isEmpty() || abbreviation.getMeaning().isEmpty() || abbreviation.getDepartment() == null) {
            StatusText.setText(fillInFields);

        } else {
            StatusText.setText(AbrAdded);
            abrAddController.createAbbreviation(abbreviation);
            disable(AbrToApp, 1000);
            System.out.println("betekenis vorige: " + meaningCheck);
            System.out.println("betekenis huidige: " + abbreviation.getMeaning());
            meaningCheck = abbreviation.getMeaning();


        }
    }
    /**
     * interacts with the controller to giva a abbreviation a like
     *
     * @param id id of the abbreviation
     * @author Martin
     */
    public void likeAbbreviation(Long id) throws Exception {
        abrAddController.giveLike(id);

    }

    /**
     * interacts with the controller to giva a abbreviation a dislike
     *
     * @param id id of the abbreviation
     * @author Martin
     */
    public void dislikeAbbreviation(Long id) throws Exception {
        abrAddController.giveDislike(id);

    }

    /**
     * interacts with the controller to delete a abbreviation by id
     *
     * @param id id of the abbreviation
     * @author Martin
     */
    public void removeAbbreviation(Long id) throws Exception {
        abrAddController.delete(id);
    }

    static void disable(Button b, final long ms) {
        b.setDisable(true);
        new SwingWorker() {
            @Override protected Object doInBackground() throws Exception {
                Thread.sleep(ms);
                return null;
            }
            @Override protected void done() {
                b.setDisable(false);
            }
        }.execute();
    }


}
