package views;

import controllers.GameController;
import controllers.WindowController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.Abbreviation;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameView implements Initializable {
    private final WindowController windowController;
    private final GameController gameController;

    private Timer gameTimer;
    private int interval = 0;

    private final StringProperty secondsProperty = new SimpleStringProperty();
    private final StringProperty answerProperty = new SimpleStringProperty();

    private final ArrayList<Abbreviation> abbreviations;

    @FXML
    private AnchorPane buttonContainer;

    @FXML
    private Button startButton, stopButton;

    @FXML
    private Label timerLabel, answerLabel;

    public GameView() {
        gameController = new GameController();
        windowController = new WindowController();
        abbreviations = gameController.getAllAbbreviations();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerLabel.textProperty().bind(secondsProperty);
        secondsProperty.setValue("60 sec");
        answerLabel.textProperty().bind(answerProperty);

        setupWindowHideEvent();
    }

    private void loadAbbreviation() {

    }

    private void setupWindowHideEvent() {
        windowController.getWindow().setOnHiding(windowEvent -> {
            stopGame();
        });
    }

    private void stopGame() {
        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer.purge();
        }
    }

    private void gameTick() {
        interval--;
        Platform.runLater(() -> secondsProperty.setValue(interval + " sec"));

        if (interval < 1)
            stopGame();
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                gameTick();
            }
        };
    }

    private void startGame() {
        interval = 5;
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(getTimerTask(), 1000, 1000);
    }

//    private void randomiseButtons(Abbreviation abbreviation) {
//        ArrayList<Button> buttons = gameController.getButtonsFromClassName(buttonContainer, "gamebtn");
//        ArrayList<String> randomStrings = gameController.getStringsFromAbbreviation(abbreviation, buttons.size());
//
//        if (buttons.isEmpty() || randomStrings.isEmpty())
//            return;
//    }

    private void toggleStartStop() {
        startButton.setDisable(!startButton.isDisable());
        stopButton.setDisable(!stopButton.isDisable());
    }

    @FXML
    private void gameButtonClicked(ActionEvent event) {
        Button gameButton = (Button) event.getSource();
        String letter = gameButton.getText();

        Platform.runLater(() -> answerProperty.setValue(answerProperty.getValue() + letter));
    }

    @FXML
    private void startButtonClicked(ActionEvent event) {
        toggleStartStop();
        startGame();
    }

    @FXML
    private void stopButtonClicked(ActionEvent event) {
        toggleStartStop();
        stopGame();
    }
}