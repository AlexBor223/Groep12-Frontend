package views;

import controllers.AbbreviationController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DeletePopupView extends Popup {
    private AbbreviationController abbreviationController;
    private boolean clickedDelete;

    public DeletePopupView() {
        clickedDelete = false;
    }

    private VBox createVBox() {
        VBox container = new VBox();
        container.setPrefSize(300, 100);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        return container;
    }

    private HBox createButtonHBox() {
        Button deleteButton = new Button("Ja, toch verwijderen");
        deleteButton.getStyleClass().add("popup-delete-btn");
        deleteButton.setOnAction(e -> {
            clickedDelete = true;
            window.close();
        });

        Button cancelButton = new Button("Nee, annuleren");
        cancelButton.getStyleClass().add("popup-btn");
        cancelButton.setOnAction(e -> window.close());

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setSpacing(10);
        buttonHBox.getChildren().addAll(deleteButton, cancelButton);

        return buttonHBox;
    }

    private VBox createContainer() {
        Label confirmationLabel = new Label(
                "Weet je zeker dat je de afkorting wilt verwijderen?\nDeze actie kan niet ongedaan gemaakt worden.");

        HBox buttonHBox = createButtonHBox();

        VBox container = createVBox();
        container.getChildren().addAll(confirmationLabel, buttonHBox);

        return container;
    }

    public void showAndWait() {
        VBox container = createContainer();
        Scene scene = new Scene(container, container.getPrefWidth(), container.getPrefHeight());
        scene.getStylesheets().add(getResourceAsString("application.css"));
        window.setScene(scene);
        window.showAndWait();
    }

    public boolean getClickedDelete() {
        return clickedDelete;
    }
}