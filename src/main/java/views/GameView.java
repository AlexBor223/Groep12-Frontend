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


    /**
     * GameView constructor
     * @author Plinio
     */
    public GameView() {
        gameController = new GameController();
        windowController = new WindowController();

        abbreviations = gameController.getAllAbbreviations();
    }

    /**
     * Initaliser for the GameView
     * @param url
     * @param resourceBundle
     * @author Plinio
     */
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

    /**
     * Resets the abbreviations and shuffles them
     * @author Plinio
     */
    private void resetAbbreviations() {
        copyAbbreviations = new ArrayList<>();
        copyAbbreviations.addAll(abbreviations);
        Collections.shuffle(copyAbbreviations);
    }

    /**
     * Loads an abbreviation in the game
     * @author Plinio
     */
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

        meaningProperty.setValue(firstAbbr.getMeaning());
        randomiseButtons();
    }

    /**
     * Resets the game by calling reset methods
     * @author Plinio
     */
    private void resetGame() {
        Platform.runLater(() -> {
            enableGameButtons(false);
            resetVariables();
            resetTimer();
            resetAbbreviations();
        });
    }

    /**
     * Resets the game variables
     * @author Plinio
     */
    private void resetVariables() {
        scoreProperty.setValue("Punten: 0");
        secondsProperty.setValue(defaultInterval + " sec");
        answerProperty.setValue("Jouw antwoord hier");
        meaningProperty.setValue("Betekenis komt hier");
    }

    /**
     * Resets the timer
     *
     * Makes sure the interval is reset
     * @author Plinio
     */
    private void resetTimer() {
        interval = defaultInterval;

        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer.purge();
        }
    }

    /**
     * Gets called every second of the timer to update view
     *
     * Also checks if time has run out
     * @author Plinio
     */
    private void gameTick() {
        interval--;
        Platform.runLater(() -> secondsProperty.setValue(interval + " sec"));

        if (interval < 1) {
            showGamePopup();
            resetGame();
        }
    }

    /**
     * Creates the game TimerTask
     * @return TimerTask
     * @author Plinio
     */
    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                gameTick();
            }
        };
    }

    /**
     * Starts the game and timer
     * @author Plinio
     */
    private void startGame() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(getTimerTask(), 1000, 1000);
        loadAbbreviation();
        enableGameButtons(true);
    }

    /**
     * Gets the current score
     * @return score and otherwise zero
     * @author Plinio
     */
    private int getScore() {
        if (scoreProperty.getValue() == null)
            return 0;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(scoreProperty.getValue());

        return (matcher.find()) ? Integer.parseInt(matcher.group(0)) : 0;
    }

    /**
     * Updates the score
     * @author Plinio
     */
    private void updateScore() {
        if (answerProperty.getValue().equals(correctLetters)) {
            int score = getScore() + 1;
            scoreProperty.setValue("Punten: " + score);
        }
    }

    /**
     * Randomises the game buttons
     * @author Plinio
     */
    private void randomiseButtons() {
        if (randomStrings.isEmpty()) {
            updateScore();
            answerProperty.setValue("");
            loadAbbreviation();
            return;
        }

        String firstString = randomStrings.get(0);
        randomStrings.remove(0);

        for (int i = 0; i < firstString.length(); i++) {
            buttons.get(i).setText(String.valueOf(firstString.charAt(i)));
        }
    }

    /**
     * Adds the letter of the button to the view
     * @param letter
     * @author Plinio
     */
    private void addLetterToAnswer(String letter) {
        if (answerProperty.getValue().equals("Jouw antwoord hier"))
            answerProperty.setValue(null);

        String currentText = answerProperty.getValue();
        answerProperty.setValue((currentText != null) ? currentText + letter : letter);
    }

    /**
     * Method to enable/disable the game buttons
     * @param enable
     * @author Plinio
     */
    private void enableGameButtons(boolean enable) {
        startButton.setDisable(enable);
        buttonContainer.setDisable(!enable);
        stopButton.setDisable(!enable);
    }

    /**
     * Sets up the windowHideEvent
     * @author Plinio
     */
    private void setupWindowHideEvent() {
        windowController.getWindow().setOnHiding(windowEvent -> {
            resetGame();
        });
    }

    /**
     * Shows the game popup with score
     * @author Plinio
     */
    private void showGamePopup() {
        int score = getScore();

        Platform.runLater(() -> {
            GamePopupView gamePopup = new GamePopupView();
            gamePopup.setTitle("Game Over");
            gamePopup.setMessage("Je score is " + score);
            gamePopup.showAndWait();
        });
    }

    /**
     * Method that fires when a game button is pressed
     * @param event
     * @author Plinio
     */
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

    /**
     * Method that fires when the start button is pressed
     * @param event
     * @author Plinio
     */
    @FXML
    private void startButtonClicked(ActionEvent event) {
        startGame();
    }

    /**
     * Method that fires when the stop button is pressed
     * @param event
     * @author Plinio
     */
    @FXML
    private void stopButtonClicked(ActionEvent event) {
        resetGame();
        showGamePopup();
    }
}