package views;

import controllers.AbbreviationController;
import controllers.DepartmentController;
import dao.AbbreviationDao;
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

public class DeleteView implements Initializable {

    private final AbbreviationController abbreviationController;
    private final DepartmentController departmentController;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private VBox abbreviationsContainer;

    @FXML
    private TextField searchTextField;

    public DeleteView() {
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
        String searchString = searchTextField.getText();
        String departmentName = filterComboBox.getValue();
        ArrayList<Abbreviation> abbreviations = abbreviationController.getFiltered(searchString, departmentName);

        for (Abbreviation abbreviation : abbreviations) {
            AnchorPane box = createAbbreviationBox(abbreviation);
            abbreviationsContainer.getChildren().add(box);
        }
    }


    ArrayList<Long> selectedAbbreviations = new ArrayList<>();
    AbbreviationDao abbreviationDao  = new AbbreviationDao();

    public void addAbbreviation(Long id){
        selectedAbbreviations.add(id);
    }

    public void removeAbbreviation(Long id){
        selectedAbbreviations.remove(id);
    }

    public void deleteAbbreviations(){
        for(int i=0; i<selectedAbbreviations.size(); i++){
            abbreviationDao.deleteAbbreviationById(i);
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


        Button delete = new Button();
        delete.getStyleClass().addAll("abbricon", "abbrlike");
        delete.setPrefSize(30, 30);
        delete.setLayoutX(300);
        delete.setLayoutY(10);
        delete.setOnMouseClicked(e -> addAbbreviation(abbreviation.getId()));
        box.getChildren().addAll(letters, meaning, delete);

        return box;
    }




}
