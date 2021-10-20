package com.Group12.FrontEnd;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class WindowController extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException{

        // Load the FXML document
        Parent root = FXMLLoader.load(getClass().getClassLoader()
                .getResource("fxml/Mainview.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hello FXML");
        stage.show();

    }
}
