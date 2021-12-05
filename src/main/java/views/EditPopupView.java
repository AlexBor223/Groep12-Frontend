package views;

import controllers.DepartmentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Abbreviation;

public class EditPopupView extends Popup {
    private final DepartmentController departmentController;
    private final Abbreviation abbreviation;
    private boolean clickedSave;

    public EditPopupView(Abbreviation abbreviation) {
        departmentController = new DepartmentController();
        clickedSave = false;
        this.abbreviation = abbreviation;
    }

    private VBox createVBox() {
        VBox container = new VBox();
        container.setPrefSize(300, 340);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        container.setPadding(new Insets(5, 10, 5, 10));

        return container;
    }

    private VBox createInputVBox(String labelText, String textFieldValue, String promptText) {
        Label label = new Label(labelText + ":");
        label.getStyleClass().add("title-lbl");

        TextField textField = new TextField(textFieldValue);
        textField.setPromptText(promptText);
        textField.getStyleClass().add("standard-textfield");

        VBox container = new VBox();
        container.setSpacing(5);
        container.getChildren().addAll(label, textField);

        return container;
    }

    private VBox createInputContainer() {
        // Letters
        Label lettersLabel = new Label("Letters:");
        lettersLabel.getStyleClass().add("title-lbl");

        TextField lettersTextField = new TextField(abbreviation.getLetters());
        lettersTextField.setPromptText("Afkorting letters");
        lettersTextField.getStyleClass().add("standard-textfield");
        lettersTextField.textProperty().addListener((options, oldValue, newValue) -> {
            if (!newValue.isBlank())
                abbreviation.setLetters(newValue.strip());
        });

        VBox letterContainer = new VBox();
        letterContainer.setSpacing(5);
        letterContainer.getChildren().addAll(lettersLabel, lettersTextField);

        // Meaning
        Label meaningLabel = new Label("Betekenis:");
        lettersLabel.getStyleClass().add("title-lbl");

        TextField meaningTextField = new TextField(abbreviation.getMeaning());
        meaningTextField.setPromptText("Afkorting betekenis");
        meaningTextField.getStyleClass().add("standard-textfield");
        meaningTextField.textProperty().addListener((options, oldValue, newValue) -> {
            if (!newValue.isBlank())
                abbreviation.setMeaning(newValue.strip());
        });

        VBox meaningContainer = new VBox();
        meaningContainer.setSpacing(5);
        meaningContainer.getChildren().addAll(meaningLabel, meaningTextField);

        // Input container
        VBox inputContainer = new VBox();
        inputContainer.setSpacing(5);
        inputContainer.getChildren().addAll(letterContainer, meaningContainer);

        return inputContainer;
    }

    private VBox createIdContainer() {
        Label label = new Label("Id:");
        label.getStyleClass().add("title-lbl");

        Label idLabel = new Label(String.valueOf(abbreviation.getId()));

        VBox idContainer = new VBox();
        idContainer.setSpacing(5);
        idContainer.getChildren().addAll(label, idLabel);
        return idContainer;
    }

    private HBox createButtonsContainer() {
        Button saveButton = new Button("Opslaan");
        saveButton.getStyleClass().add("popup-save-btn");
        saveButton.setOnAction(e -> {
            clickedSave = true;
            window.close();
        });

        Button cancelButton = new Button("Annuleren");
        cancelButton.getStyleClass().add("popup-delete-btn");
        cancelButton.setOnAction(e -> window.close());

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setSpacing(5);
        buttonHBox.getChildren().addAll(saveButton, cancelButton);

        return buttonHBox;
    }

    private VBox createFilterContainer() {
        Label label = new Label("Departement:");
        label.getStyleClass().add("title-lbl");

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getStyleClass().add("depcombo");
        filterComboBox.setPrefWidth(330);

        ObservableList<String> departmentNames = FXCollections.observableArrayList(
                departmentController.getAllDepartmentNames());

        if (!departmentNames.isEmpty()) {
            filterComboBox.setItems(departmentNames);
            filterComboBox.getSelectionModel().select(0);
        }

        filterComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            long departmentId = departmentController.getDepartmentIdByName(newValue);

            if (departmentId != -1)
                abbreviation.setDepartmentId(departmentId);
        });

        VBox filterContainer = new VBox();
        filterContainer.setSpacing(5);
        filterContainer.getChildren().addAll(label, filterComboBox);

        return filterContainer;
    }

    private VBox createApprovedContainer() {
        Label label = new Label("Goedgekeurd:");
        label.getStyleClass().add("title-lbl");

        CheckBox approvedCheckBox = new CheckBox();
        approvedCheckBox.getStyleClass().add("check-box");
        approvedCheckBox.setSelected(abbreviation.isApproved());
        approvedCheckBox.selectedProperty().addListener(e -> abbreviation.setApproved(approvedCheckBox.isSelected()));

        VBox approvedContainer = new VBox();
        approvedContainer.setSpacing(5);
        approvedContainer.getChildren().addAll(label, approvedCheckBox);

        return  approvedContainer;
    }

    private VBox createContainer() {
        VBox idContainer = createIdContainer();
        VBox filterContainer = createFilterContainer();
        VBox approvedContainer = createApprovedContainer();
        VBox inputContainer = createInputContainer();
        HBox buttonsContainer = createButtonsContainer();

        VBox container = createVBox();
        container.getChildren().addAll(idContainer, filterContainer, approvedContainer, inputContainer, buttonsContainer);

        return container;
    }

    public void showAndWait() {
        VBox container = createContainer();
        Scene scene = new Scene(container, container.getPrefWidth(), container.getPrefHeight());
        scene.getStylesheets().add(getResourceAsString("application.css"));
        window.setScene(scene);
        window.showAndWait();
    }

    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public boolean getClickedSave() {
        return clickedSave;
    }
}
