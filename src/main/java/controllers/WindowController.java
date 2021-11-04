package controllers;

import javafx.stage.Stage;
import services.WindowService;

public class WindowController {
    public void showWindow(String fxmlName, String windowTitle) {
        WindowService.getInstance().showWindow(fxmlName, windowTitle);
    }

    public Stage getWindow() {
        return WindowService.getInstance().getWindow();
    }
}