package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavbarView implements Initializable {

    @FXML
    public AnchorPane Content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void testMethod() {
        System.out.println("Test method called");
    }

    @FXML
    public void LoadWindow(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        String fxmlName = btn.getId();
        Content.getChildren().clear();
        Content.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml")));
    }



}