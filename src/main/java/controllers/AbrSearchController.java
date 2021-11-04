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

    public class AbrSearchController {

        private String AbbreviationBoxStylingId = "abbreviationBox";
        private String PngLocation = "/images/%s.png";
        private String AbbreviationStylingId = "abbreviation";
        private String DepartmentStylingId = "department";

        private AbbreviationDao abrDao = new AbbreviationDao();
        private DepartmentDao depDao = new DepartmentDao();

        private String SearchedAbr;
        private String SearchedDepartment;

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

        public boolean noSearch() {
            return SearchedAbr.isEmpty();
        }

        public ArrayList<AnchorPane> getAbbreviationBoxes() {
            ArrayList<Abbreviation> localAbr = getAbbreviations();
            ArrayList<AnchorPane> abbreviationBoxes = new ArrayList<AnchorPane>();
            try {
                if (localAbr != null) {
                    for (Abbreviation abr : localAbr) {
                        if (abr.isApproved()) {
                            abbreviationBoxes.add(insertAbbreviation(abr.getLetters() + "  " + abr.getMeaning(), abr.getDepartment(), abr.getId(), false));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return abbreviationBoxes;
        }


        public ArrayList<AnchorPane> getNewAbbreviationBoxes() {
            ArrayList<Abbreviation> localAbr = getAbbreviations();
            ArrayList<AnchorPane> abbreviationBoxes = new ArrayList<AnchorPane>();
            try {
                if (localAbr != null) {
                    for (Abbreviation abr : localAbr) {
                        if (!abr.isApproved()) {
                            abbreviationBoxes.add(insertAbbreviation(abr.getLetters() + "  " + abr.getMeaning(), abr.getDepartment(), abr.getId(), true));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return abbreviationBoxes;
        }

        public void updateAbr(String Abr) {
            SearchedAbr = Abr;
        }

        public ArrayList<Abbreviation> getAbbreviations() {
            return abrDao.searchAbbreviations(SearchedAbr, SearchedDepartment);
        }

        public Boolean likeAbbreviation(long id) throws Exception {
            return abrDao.LikeAbbreviation(id);
        }

        public AnchorPane insertAbbreviation(String AbbreviationMeaning, String department, long AbrId, boolean isNew) {
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

        public ArrayList<DepartmentModel> getAllDepartments() {
            return new ArrayList<DepartmentModel>();
        }

        public void updateDep(String department) {
            SearchedDepartment = department;
        }

    }
