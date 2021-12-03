package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GamePopupView extends Popup {
    private String message;

    public GamePopupView() {
        super();
        message = "";
    }

    private VBox getVBox() {
        Label messageLabel = new Label(message);
        Button okayButton = new Button("OK");
        okayButton.getStyleClass().add("popup-btn");
        okayButton.setOnAction(e -> window.close());

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
        window.setScene(scene);
        window.showAndWait();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
