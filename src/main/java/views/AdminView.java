package views;

import controllers.WindowController;
import javafx.fxml.FXML;

public class AdminView {

//    @FXML

WindowController windowController = new WindowController();

    public void loadAddPage() {
        windowController.showWindow("AdminAddPanel", "Afkortingen toevoegen");

    }
}
