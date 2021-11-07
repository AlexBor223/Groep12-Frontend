package controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import models.Abbreviation;
import models.GameRandomiser;
import models.NodeSearcher;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    /**
     * Gets the buttons that correspond with the CSS class name
     * @param parent
     * @param className
     * @return ArrayList of buttons
     * @author Plinio
     */
    public ArrayList<Button> getButtonsFromClassName(Parent parent, String className) {
        NodeSearcher nodeSearcher = new NodeSearcher();
        return nodeSearcher.getButtonsFromClassName(parent, className);
    }

    /**
     * Gets the random strings from the abbreviation and number of buttons
     * @param abbreviation
     * @param numOfButtons
     * @return ArrayList of Strings
     * @author Plinio
     */
    public ArrayList<String> getStringsFromAbbreviation(Abbreviation abbreviation, int numOfButtons) {
        GameRandomiser gameRandomiser = new GameRandomiser();
        return gameRandomiser.getStringsFromLetters(abbreviation.getLetters(), numOfButtons);
    }

    /**
     * Gets all abbreviations from the DAO
     * @return ArrayList of Abbreviation
     * @author Plinio
     */
    public ArrayList<Abbreviation> getAllAbbreviations() {
        // TODO after merge: Communicate with DAO to get abbreviations
        Abbreviation test1 = new Abbreviation("ACM","Autoriteit Consument en Markt", "Algemeen", 0, 0);
        Abbreviation test2 = new Abbreviation("BZK", "Binnenlandse Zaken", "Algemeen", 0, 1);
        Abbreviation test3 = new Abbreviation("ETC","Et cetera", "Algemeen", 0, 2);
        Abbreviation test4 = new Abbreviation("WVTTK","Wat verder te tafel komt", "Algemeen", 0, 3);

        return new ArrayList<>(List.of(test1, test2, test3, test4));
    }
}