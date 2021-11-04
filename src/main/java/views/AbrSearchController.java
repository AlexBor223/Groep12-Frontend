package views;

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

public class AbrSearchController implements Initializable {

    @FXML private TextField input;
    @FXML private ComboBox<String> comboBox;

    private final String AbbreviationStylingId = "abbreviation";
    private final String DepartmentStylingId = "department";
    private final String AbbreviationBoxStylingId= "abbreviationBox";
    private final String PngLocation = "images/%s.png";

    private AbbreviationDao abrDao = new AbbreviationDao();
    private DepartmentDao depDao = new DepartmentDao();

    private String SearchedID;
    private String SearchedDepartment;

    private EventHandler<MouseEvent> like = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Button clickedButton = (Button) event.getSource();
            System.out.println(clickedButton.getParent().getProperties().get("abrId"));
            long id = Long.parseLong((String)clickedButton.getParent().getProperties().get("abrId"));
            try {
                abrDao.LikeAbbreviation(id);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    };

    private EventHandler<MouseEvent> dislike = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Button clickedButton = (Button) event.getSource();
            long id = Long.parseLong((String)clickedButton.getParent().getProperties().get("abrId"));
            try {
                abrDao.DislikeAbbreviation(id);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    };


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
        //listens for change in query
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchedID = newValue;
            updateAbbreviationBoxes();
       });

        //listens for change in department
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SearchedDepartment = newValue;

            updateAbbreviationBoxes();
        });

    }
    //gives out information to either newAbbreviation boxes or abbreviation box
    public void updateAbbreviationBoxes(){
        abbreviations.getChildren().clear();
        newAbbreviations.getChildren().clear();
        ArrayList<Abbreviation> localAbr= getAbbreviations();
        try {
            if (localAbr != null) {
                for (Abbreviation abr : localAbr) {
                    if (abr.isApproved()) {
                        insertAbbreviation(abr.getLetters() + "  " + abr.getMeaning(), abr.getDepartment(), abr.getId());
                    } else {
                        insertNewAbbreviation(abr.getLetters() + "  " + abr.getMeaning(), abr.getDepartment(), abr.getId());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Abbreviation> getAbbreviations(){
        return abrDao.searchAbbreviations(SearchedID, SearchedDepartment);
    }

    public void insertAbbreviation(String AbbreviationMeaning, String department, long AbrId){
        AnchorPane abbreviationBox = createBaseAbbreviationBox(AbbreviationMeaning, department, AbrId);
        abbreviations.getChildren().add(abbreviationBox);
    }

    public void insertNewAbbreviation(String AbbreviationMeaning, String department, long AbrId) throws Exception{
        AnchorPane abbreviationBox = createBaseAbbreviationBox(AbbreviationMeaning, department, AbrId);
        Button likeButton = CreateLikeButton("like");
        Button dislikeButton = CreateLikeButton("dislike");

        //adding buttons to abbreviation box
        abbreviationBox.getChildren().add(likeButton);
        abbreviationBox.getChildren().add(dislikeButton);

        //positioning in abbreviation box
        likeButton.setLayoutX(320);
        likeButton.setLayoutY(9);

        //positioning in abbreviation box
        dislikeButton.setLayoutX(360);
        dislikeButton.setLayoutY(11);

        //set triggers for likes and dislikes
        likeButton.setOnMouseClicked(like);
        dislikeButton.setOnMouseClicked(dislike);

        newAbbreviations.getChildren().add(abbreviationBox);
    }

    public Button CreateLikeButton(String kind){
        Button likeButton = new Button();

        //makes button black
        Shadow color = new Shadow();
        color.setColor(Color.BLACK);
        color.setRadius(0);

        likeButton.setEffect(color);

        BackgroundImage Image= new BackgroundImage(new Image(String.format(PngLocation, kind),30,30,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        likeButton.setBackground(new Background(Image));

        likeButton.setId(kind);

        return likeButton;
    }


    public AnchorPane createBaseAbbreviationBox(String abbreviationMeaning, String department, long abrId){
        AnchorPane abbreviationBox = new AnchorPane();
        Text Meaning = new Text();
        Text Department = new Text();

        Meaning.setText(abbreviationMeaning);
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
        abbreviationBox.getProperties().put("abrId", String.valueOf(abrId));

        abbreviationBox.getChildren().add(Meaning);
        abbreviationBox.getChildren().add(Department);

        return abbreviationBox;
    }

}
