package services;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class WindowService {
    private static WindowService windowService;
    private Stage window;

    private WindowService() {}

    public static WindowService getInstance() {
        if (windowService == null)
            windowService = new WindowService();

        return windowService;
    }

    private URL getResource(String resourcePath) {
        return getClass().getClassLoader().getResource(resourcePath);
    }

    private String getResourceAsString(String resourcePath) {
        return String.valueOf(getClass().getClassLoader().getResource(resourcePath));
    }

    public void showWindow(String fxmlName, String windowTitle) {
        try {
            AnchorPane pane = FXMLLoader.load(getResource("fxml/" + fxmlName + ".fxml"));
            Scene scene = new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight());
            scene.getStylesheets().add(getResourceAsString("application.css"));
            window = new Stage();
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



    public Stage getWindow() {
        return window;
    }
}
