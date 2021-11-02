package models;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Set;

public class NodeSearcher {
    public ArrayList<Button> getButtonsFromClassName(Parent parent, String className) {
        ArrayList<Button> buttons = new ArrayList<>();

        if (parent == null || className == null)
            return buttons;

        Set<Node> nodes = parent.lookupAll("." + className);

        for (Node node : nodes) {
            if (node instanceof Button)
                buttons.add((Button) node);
        }

        return buttons;
    }
}