package controllers;




import dao.AbbreviationDao;
import dao.DepartmentDao;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Abbreviation;
import models.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * controls the view for the abbreviation search window
 */

public class AbrSearchController {

        private ArrayList<DepartmentModel> DepartmentArray;

    /**
     * information needed for loading things into view
     */

        private String AbbreviationBoxStylingId = "abbreviationBox";
        private String PngLocation = "/images/%s.png";
        private String AbbreviationStylingId = "abbreviation";
        private String DepartmentStylingId = "department";

    /**
     * Dao set-up
     */

        private AbbreviationDao abrDao = new AbbreviationDao();
        private DepartmentDao depDao = new DepartmentDao();

    /**
     * stored queries
     */
        private String SearchedAbr;
        private String SearchedDepartment;



    /**
     * like and dislike handlers
     */

    private EventHandler<MouseEvent> like = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Button clickedButton = (Button) event.getSource();
                long id = Long.parseLong((String) clickedButton.getParent().getProperties().get("abrId"));
                try {
                    abrDao.LikeAbbreviation(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        };

        private EventHandler<MouseEvent> dislike = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Button clickedButton = (Button) event.getSource();
                long id = Long.parseLong((String) clickedButton.getParent().getProperties().get("abrId"));
                try {
                    abrDao.DislikeAbbreviation(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

    /**
     * checks if Search String is empty
     * @return A boolean representing the search state
     */
    public boolean noSearch() {
            return SearchedAbr.isEmpty();
        }

    /**
     * creates a list of abbreviation boxes based on server side filtered abbreviations
     * @return list of abbreviation boxes
     */
    public ArrayList<AnchorPane> getAbbreviationBoxes() {
            ArrayList<Abbreviation> localAbr = getAbbreviations();
            ArrayList<AnchorPane> abbreviationBoxes = new ArrayList<AnchorPane>();
            try {
                if (localAbr != null) {
                    for (Abbreviation abr : localAbr) {
                        if (abr.isApproved()) {
                            abbreviationBoxes.add(createAbbreviationBox(abr.getLetters() + "  " + abr.getMeaning(),  getNameById(Long.parseLong(abr.getDepartment())), abr.getId(), false));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return abbreviationBoxes;
        }

    /**
     * creates a list of new abbreviation boxes based on server side filtered abbreviations
     * @return list of abbreviation boxes
     */
        public ArrayList<AnchorPane> getNewAbbreviationBoxes() {
            ArrayList<Abbreviation> localAbr = getAbbreviations();
            ArrayList<AnchorPane> abbreviationBoxes = new ArrayList<AnchorPane>();
            try {
                if (localAbr != null) {
                    for (Abbreviation abr : localAbr) {
                        if (!abr.isApproved()) {
                            abbreviationBoxes.add(createAbbreviationBox(abr.getLetters() + "  " + abr.getMeaning(), getNameById(Long.parseLong(abr.getDepartment())), abr.getId(), true));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return abbreviationBoxes;
        }

    /**
     * updates the search querty string
     * @param Abr the string value that's being searched on
     */
    public void updateAbr(String Abr) {
            SearchedAbr = Abr;
        }

    /**
     * gets a list of filtered abbreviations from the dao
     * @return an array of filtered abbreviations
     */
    public ArrayList<Abbreviation> getAbbreviations() {
            return (ArrayList<Abbreviation>) abrDao.searchAbbreviations(SearchedAbr, SearchedDepartment);
        }

    /**
     * gives a like to a abbreviation based on ID
     * @param id the id of the abbreviation given a like
     * @return if it succeeds or not
     * @throws Exception if the http request fails
     */
    public Boolean likeAbbreviation(long id) throws Exception {
            return abrDao.LikeAbbreviation(id);
        }


    /**
     * creates a abbreviation box from information about the abbreviation
     * @param AbbreviationMeaning meaning of the abbreviation
     * @param department the department of the abbreviation
     * @param AbrId the id of the abbreviation
     * @param isNew wether the id is new or not
     * @return an AnchorPane containing the abbreviation box
     */
    public AnchorPane createAbbreviationBox(String AbbreviationMeaning, String department, long AbrId, boolean isNew) {
            AnchorPane abbreviationBox = createBaseAbbreviationBox(AbbreviationMeaning, department, AbrId);
            if (isNew) {
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
            }

            return abbreviationBox;
        }

    /**
     * creates a like button for abbreviation box based on wether it's a like or dislike button
     * @param kind what kind of button it is
     * @return a like or dislike button
     */

    public Button CreateLikeButton(String kind) {
            Button likeButton = new Button();

            //makes button black
            Shadow color = new Shadow();
            color.setColor(Color.BLACK);
            color.setRadius(0);

            likeButton.setEffect(color);

            BackgroundImage Image = new BackgroundImage(new Image(String.format(PngLocation, kind), 30, 30, false, true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            likeButton.setBackground(new Background(Image));

            likeButton.setId(kind);

            return likeButton;
        }

    /**
     * creates the base for an abbreviation box containing the base information
     * @param abbreviationMeaning the meaning of an abbreviation
     * @param department the department of an abbreviation
     * @param abrId the id of an abbreviation
     * @return a basic abbreviation box
     */
        public AnchorPane createBaseAbbreviationBox(String abbreviationMeaning, String department, long abrId) {
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

    /**
     * gets a list of all departments from the back-end
     * @return a list of all departments
     */
    public ArrayList<DepartmentModel> getAllDepartments() {
            DepartmentArray =depDao.GetAllDepartments();

            return DepartmentArray;
        }

        public void updateDep(String department) {
            SearchedDepartment = department;
        }

    /**
     * creates a list of all the names out of a department list
     * @param Departments a list of the departments that need a name
     * @return a list of all names
     */
    private  List<String> depListToNameList(List<DepartmentModel> Departments){
        List<String> newList = new ArrayList<String>(Departments.size());

        for (DepartmentModel department : Departments) {
            newList.add(department.getName());
        }
        return newList;
    }

    /**
     * gets the name of a department by it's id
     * @param id the id of which the department name is needed
     * @return the department name
     */
    private String getNameById(long id){
            String returnValue = "error";

            for(int i=0; i< DepartmentArray.size();i++){
                if(DepartmentArray.get(i).getId() == id){
                    returnValue = DepartmentArray.get(i).getName();
                }
            }

            return returnValue;
    }


    }
