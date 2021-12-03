package views;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
    protected final Stage window;

    public Popup() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaximized(false);
        window.setResizable(false);
        window.getIcons().add(new Image(getResourceAsString("images/popup.png")));
    }

    protected String getResourceAsString(String resourcePath) {
        return String.valueOf(getClass().getClassLoader().getResource(resourcePath));
    }

    protected void setWindowTitle(String title) {
        window.setTitle(title);
    }
}