package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class NavbarController implements Initializable {

    @FXML public AnchorPane Content;

    public HashMap<String, Node> fxmls = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void ChangeWindow(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        String fxmlName = btn.getId();

        LoadWindow(fxmlName);
    }

    public void LoadWindow(String fxmlName) throws IOException {
        Content.getChildren().clear();

        if(!fxmls.containsKey(fxmlName)){
            fxmls.put(fxmlName, FXMLLoader.load(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml")));
        }

        Content.getChildren().add(fxmls.get(fxmlName));

    }




}
