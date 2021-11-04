package views;

import Controllers.AbrSearchController;
import Dao.AbbreviationDao;
import Dao.DepartmentDao;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import models.Abbreviation;
import models.DepartmentModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbrSearchView implements Initializable {

    @FXML
    private TextField input;
    @FXML
    private ComboBox<String> comboBox;

    private AbrSearchController controller = new AbrSearchController();

    @FXML
    private VBox abbreviations;
    @FXML
    private VBox newAbbreviations;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input.requestFocus();
        ArrayList<DepartmentModel> DepartmentArray = controller.getAllDepartments();
        ObservableList<String> options = FXCollections.observableArrayList();

//        for (DepartmentModel department:DepartmentArray){
//            options.add(department.getName());
//        }

        comboBox.setItems(options);

        //listens for change in query
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.updateAbr(newValue);
            updateAbbreviationBoxes();
        });

        //listens for change in department
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            controller.updateDep(newValue);
            updateAbbreviationBoxes();
        });
    }

    private void updateAbbreviationBoxes() {
        abbreviations.getChildren().clear();
        newAbbreviations.getChildren().clear();
        if (controller.noSearch()) {
            return;
        }
        for (AnchorPane abbreviationBox : controller.getNewAbbreviationBoxes()) {
            newAbbreviations.getChildren().add(abbreviationBox);
        }
        for (AnchorPane abbreviationBox : controller.getAbbreviationBoxes()) {
            abbreviations.getChildren().add(abbreviationBox);
        }
    }

}
