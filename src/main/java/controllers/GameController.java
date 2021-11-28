package controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import models.Abbreviation;
import models.GameRandomiser;
import models.NodeSearcher;

import java.util.ArrayList;

public class GameController {
    public ArrayList<Button> getButtonsFromClassName(Parent parent, String className) {
        NodeSearcher nodeSearcher = new NodeSearcher();
        return nodeSearcher.getButtonsFromClassName(parent, className);
    }

    public ArrayList<String> getStringsFromAbbreviation(Abbreviation abbreviation, int numOfButtons) {
        GameRandomiser gameRandomiser = new GameRandomiser();
        return gameRandomiser.getStringsFromLetters(abbreviation.getLetters(), numOfButtons);
    }
}