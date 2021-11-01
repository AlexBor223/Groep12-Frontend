package views;

import controllers.WindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NavbarView implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void loadWindow(ActionEvent event) {
        Parent btn = (Parent) event.getSource();
        String fxmlName = btn.getId();

        WindowController windowController = new WindowController();
        windowController.showWindow(fxmlName, null);
    }
}
