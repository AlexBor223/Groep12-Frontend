package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class NavbarController implements Initializable {

    @FXML public AnchorPane Content;
    final private String Initialfxml = "AbrSearch";
    private HashMap<String, Node> fxmls = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadWindow(Initialfxml);
    }

    @FXML
    public void ChangeWindow(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String fxmlName = btn.getId();

        LoadWindow(fxmlName);
    }

    public void LoadWindow(String fxmlName) {

        try {
            if (!fxmls.containsKey(fxmlName)) {
                Node fxml = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
                fxmls.put(fxmlName, fxml);
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        Content.getChildren().clear();
        Content.getChildren().add(fxmls.get(fxmlName));
    }

}
