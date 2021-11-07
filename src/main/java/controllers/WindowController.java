package controllers;

import javafx.stage.Stage;
import services.WindowService;

public class WindowController {
    /**
     * Passes data to WindowService to show window
     * @param fxmlName
     * @param windowTitle
     * @author Plinio
     */
    public void showWindow(String fxmlName, String windowTitle) {
        WindowService.getInstance().showWindow(fxmlName, windowTitle);
    }

    /**
     * Gets the Stage (window) from the WindowService class
     * @return Stage
     * @author Plinio
     */
    public Stage getWindow() {
        return WindowService.getInstance().getWindow();
    }
}