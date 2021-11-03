package controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import models.Abbreviation;
import models.GameRandomiser;
import models.NodeSearcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    public ArrayList<Button> getButtonsFromClassName(Parent parent, String className) {
        NodeSearcher nodeSearcher = new NodeSearcher();
        return nodeSearcher.getButtonsFromClassName(parent, className);
    }

    public ArrayList<String> getStringsFromAbbreviation(Abbreviation abbreviation, int numOfButtons) {
        GameRandomiser gameRandomiser = new GameRandomiser();
        return gameRandomiser.getStringsFromAbbreviation(abbreviation, numOfButtons);
    }

    public ArrayList<Abbreviation> getAllAbbreviations() {
        // TO-DO after merge: Communicate with DAO to get abbreviations
        Abbreviation test1 = new Abbreviation(0, "ACM","Autoriteit Consument en Markt", 0);
        Abbreviation test2 = new Abbreviation(1, "BZK","Binnenlandse Zaken", 0);
        Abbreviation test3 = new Abbreviation(2, "ETC","Et cetera", 0);
        Abbreviation test4 = new Abbreviation(3, "WVTTK","Wat verder te tafel komt", 0);

        return new ArrayList<>(List.of(test1, test2, test3, test4));
    }
}