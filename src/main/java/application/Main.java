package application;

import controllers.WindowController;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        Font.loadFont(getClass().getResourceAsStream("/FontWeb.ttf"), 16);
        WindowController windowController = new WindowController();
        windowController.showWindow("Navbar", "AFKO Applicatie - Groep 12");


    }

    public static void main(String[] args) {
        launch(args);
    }
}