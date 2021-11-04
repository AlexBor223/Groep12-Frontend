package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePopupView {
    private Stage window;
    private String title;
    private String message;

    public GamePopupView() {
        this.window = new Stage();
        title = "";
        message = "";
    }

    private String getResourceAsString(String resourcePath) {
        return String.valueOf(getClass().getClassLoader().getResource(resourcePath));
    }

    private void okayButtonClicked() {
        window.close();
    }

    private VBox getVBox() {
        Label messageLabel = new Label(message);
        Button okayButton = new Button("OK");
        okayButton.getStyleClass().add("okaybtn");
        okayButton.setOnAction(e -> okayButtonClicked());

        VBox pane = new VBox();
        pane.setPrefSize(300, 100);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        pane.getChildren().addAll(messageLabel, okayButton);

        return pane;
    }

    public void showAndWait() {
        VBox pane = getVBox();
        Scene scene = new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight());
        scene.getStylesheets().add(getResourceAsString("application.css"));

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaximized(false);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
