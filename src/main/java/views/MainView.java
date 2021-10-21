package views;

import controllers.WindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void testMethod() {
        System.out.println("Test method called");
    }
    @FXML
    public void LoadWindow(ActionEvent event){
        Button btn = (Button) event.getSource();
        String fxmlName = btn.getId();
        System.out.println(fxmlName);
        WindowController windowController = new WindowController();
        windowController.showWindow(fxmlName, "AFKO Applicatie - Groep 12");

    }



}
