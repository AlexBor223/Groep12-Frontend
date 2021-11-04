package views;

import controllers.WindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NavbarView implements Initializable {
    private WindowController windowController;

    public NavbarView() {
        windowController = new WindowController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void loadWindow(ActionEvent event) {
        Button navButton = (Button) event.getSource();
        String fxmlName = navButton.getId();
        windowController.showWindow(fxmlName, null);
    }

    @FXML
    public void goToMain() {
        windowController.showWindow("Main", null);
    }
}
