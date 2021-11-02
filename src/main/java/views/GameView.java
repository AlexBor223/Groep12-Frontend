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
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameView implements Initializable {
    private final WindowController windowController;
    private final GameController gameController;

    private Timer gameTimer;
    private final StringProperty secondsLeft = new SimpleStringProperty();

    private Abbreviation testAbbreviation;

    @FXML
    private AnchorPane buttonContainer;

    @FXML
    private Button startButton, stopButton;

    @FXML
    private Label timerLabel;

    public GameView() {
        gameController = new GameController();
        windowController = new WindowController();

        testAbbreviation = new Abbreviation(
                0,
                "ACM",
                "Autoriteit Consument en Markt",
                0
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerLabel.textProperty().bind(secondsLeft);

        setupWindowHideEvent();
    }

    private void setupWindowHideEvent() {
        windowController.getWindow().setOnHiding(windowEvent -> {
            stopGameTimer();
        });
    }

    private void stopGameTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer.purge();
        }
    }

    private void startGameTimer() {
        TimerTask timerTask = new TimerTask() {
            int countdown = 10;

            @Override
            public void run() {
                if (countdown > 0) {
                    countdown--;
                    Platform.runLater(() -> secondsLeft.setValue(countdown + " sec"));
                } else {
                    stopGameTimer();
                }
            }
        };

        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    private void toggleStartStop() {
        startButton.setDisable(!startButton.isDisable());
        stopButton.setDisable(!stopButton.isDisable());
    }

    @FXML
    private void gameButtonClicked(ActionEvent event) {
        Button gameButton = (Button) event.getSource();
        String letter = gameButton.getText();
    }

    @FXML
    private void startButtonClicked(ActionEvent event) {
        toggleStartStop();
    }

    @FXML
    private void stopButtonClicked(ActionEvent event) {
        toggleStartStop();
    }
}
