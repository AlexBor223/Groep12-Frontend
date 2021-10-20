package application;

import controllers.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.MainView;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        WindowController windowController = new WindowController();
        windowController.showWindow("Main", "AFKO Applicatie - Groep 12");
    }

    public static void main(String[] args) {
        launch(args);
    }
}