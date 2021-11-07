package models;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Set;

public class NodeSearcher {
    /**
     * Gets the buttons from the parent with the css class name
     * @param parent
     * @param className
     * @return ArrayList of buttons
     */
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