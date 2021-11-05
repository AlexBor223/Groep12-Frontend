package views;

import controllers.WindowController;

public class AddPanelView {


    WindowController windowController = new WindowController();

    public void loadDeletePage() {
        windowController.showWindow("AdminPanel", "Afkortingen verwijderen");

    }
}
