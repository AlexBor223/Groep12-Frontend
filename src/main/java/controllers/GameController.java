package controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import models.NodeSearcher;

import java.util.ArrayList;

public class GameController {
    public ArrayList<Button> getButtonsFromClassName(Parent parent, String className) {
        NodeSearcher nodeSearcher = new NodeSearcher();
        return nodeSearcher.getButtonsFromClassName(parent, className);
    }
}