package application;

import controllers.WindowController;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/RijksoverheidSansWebText.ttf"), 16);
        WindowController windowController = new WindowController();
        windowController.showWindow("Main", null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}