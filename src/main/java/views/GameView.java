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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView implements Initializable {
    // Controllers
    private final WindowController windowController;
    private final GameController gameController;

    // Game variables
    private Timer gameTimer;
    private int defaultInterval = 15;
    private int interval = 0;
    private String correctLetters;
    private ArrayList<Button> buttons;
    private ArrayList<String> randomStrings;

    // SimpleProperties
    private final StringProperty secondsProperty = new SimpleStringProperty();
    private final StringProperty meaningProperty = new SimpleStringProperty();
    private final StringProperty answerProperty = new SimpleStringProperty();
    private final StringProperty scoreProperty = new SimpleStringProperty();

    // Abbreviations
    private final ArrayList<Abbreviation> abbreviations;
    private ArrayList<Abbreviation> copyAbbreviations;

    @FXML
    private AnchorPane buttonContainer;

    @FXML
    private Button startButton, stopButton;

    @FXML
    private Label timerLabel, scoreLabel, meaningLabel, answerLabel;

    public GameView() {
        gameController = new GameController();
        windowController = new WindowController();

        abbreviations = gameController.getAllAbbreviations();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = gameController.getButtonsFromClassName(buttonContainer, "gamebtn");

        timerLabel.textProperty().bind(secondsProperty);
        answerLabel.textProperty().bind(answerProperty);
        meaningLabel.textProperty().bind(meaningProperty);
        scoreLabel.textProperty().bind(scoreProperty);

        resetGame();
        setupWindowHideEvent();
    }

    private void resetAbbreviations() {
        copyAbbreviations = new ArrayList<>();
        copyAbbreviations.addAll(abbreviations);
        Collections.shuffle(copyAbbreviations);
    }

    private void loadAbbreviation() {
        if (copyAbbreviations.isEmpty()) {
            showGamePopup();
            resetGame();
            return;
        }

        Abbreviation firstAbbr = copyAbbreviations.get(0);
        copyAbbreviations.remove(0);
        randomStrings = gameController.getStringsFromAbbreviation(firstAbbr, buttons.size());
        correctLetters = firstAbbr.getLetters();

//        Platform.runLater(() -> meaningProperty.setValue(firstAbbr.getMeaning()));
        meaningProperty.setValue(firstAbbr.getMeaning());
        randomiseButtons();
    }

    private void resetGame() {
        Platform.runLater(() -> {
            enableGameButtons(false);
            resetVariables();
            resetTimer();
            resetAbbreviations();
        });
    }

    private void resetVariables() {
        scoreProperty.setValue("Punten: 0");
        secondsProperty.setValue(defaultInterval + " sec");
        answerProperty.setValue("Jouw antwoord hier");
        meaningProperty.setValue("Betekenis komt hier");
    }

    private void resetTimer() {
        interval = defaultInterval;

        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer.purge();
        }
    }

    private void gameTick() {
        interval--;
        Platform.runLater(() -> secondsProperty.setValue(interval + " sec"));

        if (interval < 1) {
            showGamePopup();
            resetGame();
        }
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
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(getTimerTask(), 1000, 1000);
        loadAbbreviation();
        enableGameButtons(true);
    }

    private int getScore() {
        if (scoreProperty.getValue() == null)
            return 0;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(scoreProperty.getValue());

        return (matcher.find()) ? Integer.parseInt(matcher.group(0)) : 0;
    }

    private void updateScore() {
        if (answerProperty.getValue().equals(correctLetters)) {
            int score = getScore() + 1;
            scoreProperty.setValue("Punten: " + score);
        }
    }

    private void randomiseButtons() {
        if (randomStrings.isEmpty()) {
            updateScore();
            answerProperty.setValue("");
            loadAbbreviation();
            return;
        }

        String firstString = randomStrings.get(0);
        randomStrings.remove(0);

//        Platform.runLater(() -> {
//            for (int i = 0; i < firstString.length(); i++) {
//                buttons.get(i).setText(String.valueOf(firstString.charAt(i)));
//            }
//        });

        for (int i = 0; i < firstString.length(); i++) {
            buttons.get(i).setText(String.valueOf(firstString.charAt(i)));
        }
    }

    private void addLetterToAnswer(String letter) {
        if (answerProperty.getValue().equals("Jouw antwoord hier"))
            answerProperty.setValue(null);

        String currentText = answerProperty.getValue();
        answerProperty.setValue((currentText != null) ? currentText + letter : letter);
    }

    private void enableGameButtons(boolean enable) {
        startButton.setDisable(enable);
        buttonContainer.setDisable(!enable);
        stopButton.setDisable(!enable);
    }

    private void setupWindowHideEvent() {
        windowController.getWindow().setOnHiding(windowEvent -> {
            resetGame();
        });
    }

    private void showGamePopup() {
        int score = getScore();

        Platform.runLater(() -> {
            GamePopupView gamePopup = new GamePopupView();
            gamePopup.setTitle("Game Over");
            gamePopup.setMessage("Je score is " + score);
            gamePopup.showAndWait();
        });
    }

    @FXML
    private void gameButtonClicked(ActionEvent event) {
        Button gameButton = (Button) event.getSource();
        String letter = gameButton.getText();

        // Security in case the timer runs out and disables button, and the user simultaneously clicks
        if (gameButton.isDisable())
            return;

        addLetterToAnswer(letter);
        randomiseButtons();
    }

    @FXML
    private void startButtonClicked(ActionEvent event) {
        startGame();
    }

    @FXML
    private void stopButtonClicked(ActionEvent event) {
        resetGame();
        showGamePopup();
    }
}