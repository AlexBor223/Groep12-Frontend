package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class WindowService {
    private static WindowService windowService;
    private static Stage window;
    private final String defaultTitle = "AFKO Applicatie - Groep 12";

    /**
     * Private constructor for the WindowService
     * @author Plinio
     */
    private WindowService() {}

    /**
     * Gets the instance of WindowService
     * @return WindowService
     * @author Plinio
     */
    public static WindowService getInstance() {
        if (windowService == null)
            windowService = new WindowService();

        return windowService;
    }

    /**
     * Gets a resource URL from the resource folder
     * @param resourcePath
     * @return URL
     * @author Plinio
     */
    private URL getResource(String resourcePath) {
        return getClass().getClassLoader().getResource(resourcePath);
    }

    /**
     * Gets the resource URL as String
     * @param resourcePath
     * @return Resource String
     * @author Plinio
     */
    private String getResourceAsString(String resourcePath) {
        return String.valueOf(getClass().getClassLoader().getResource(resourcePath));
    }

    /**
     * Checks if the window exists
     * @return boolean
     * @author Plinio
     */
    private boolean windowExists() {
        return window != null;
    }

    /**
     * Creates a new window is window doesn't exist
     * @author Plinio
     */
    private void createWindowIfNecessary() {
        if (!windowExists())
            window = new Stage();
    }

    /**
     * Shows the window based on fxmlName
     * @param fxmlName
     * @param windowTitle Optional parameter to alter window title
     * @author Plinio
     */
    public void showWindow(String fxmlName, String windowTitle) {
        if (windowTitle == null)
            windowTitle = defaultTitle;

        destroyWindow();
        createWindowIfNecessary();

        try {
            AnchorPane pane = FXMLLoader.load(getResource("fxml/" + fxmlName + ".fxml"));
            Scene scene = new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight());
            scene.getStylesheets().add(getResourceAsString("application.css"));

            window.setTitle(windowTitle);
            window.setResizable(false);
            window.setMaximized(false);
            window.getIcons().add(new Image(getResourceAsString("images/logo.png")));
            window.setScene(scene);
            window.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the current window
     * @return Stage
     * @author Plinio
     */
    public Stage getWindow() {
        return window;
    }

    /**
     * Destroys window if it exists
     * @author Plinio
     */
    public void destroyWindow() {
        if (windowExists())
            window.close();
    }
}
