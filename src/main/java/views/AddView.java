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
    private int exponentialInt = 1;
    private int multiplyInt = 1;
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

        if (meaningCheck != null && meaningCheck.equals(tempMeaning)) {
            StatusText.setText(notTheSameField);

        } else {
            checkInput(abbreviation);
        }


    }

    /**
     * checks if the input is you gave is not empty then sends the abrreviation to the controller and gives confirmation about that
     * If all the fields are filled in correctly the methods setStatusTextAdded(),  abrAddController.createAbbreviation(abbreviation),
     * updateWaitInt(exponentialInt) and disable(AbrToApp, multiplyInt * 2000) are called it also returns true and asigns a value to meaningCheck
     * and gives the exponentialInt a + 1
     *
     * @author Martin
     */
    public boolean checkInput(Abbreviation abbreviation) throws Exception {

        if (abbreviation.getLetters().isEmpty() || abbreviation.getMeaning().isEmpty() || abbreviation.getDepartment() == null) {

            setStatusTextFillIn();
            return false;

        } else {
            setStatusTextAdded();

            abrAddController.createAbbreviation(abbreviation);

            disable(AbrToApp, multiplyInt * 2000);
            updateWaitInt(exponentialInt);
            exponentialInt += 1;

            meaningCheck = abbreviation.getMeaning();

            return true;

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
     * sets the text of StatusText to the value of AbrAdded if StatusText != null
     *
     * @author Martin
     */
    private void setStatusTextAdded() {
        if (StatusText != null) {           //voor unit test
            StatusText.setText(AbrAdded);
        }

    }

    /**
     * sets the text of StatusText to the value of fillInFields if StatusText != null
     *
     * @author Martin
     */
    private void setStatusTextFillIn() {
        if (StatusText != null) {
            StatusText.setText(fillInFields);
        }

    }

}
