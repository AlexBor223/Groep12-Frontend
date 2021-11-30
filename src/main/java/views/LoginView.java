package views;

import controllers.WindowController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.LoginService;

import java.net.URL;
import java.security.spec.ECField;
import java.util.ResourceBundle;

public class LoginView implements Initializable {
    private final WindowController windowController;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    public LoginView() {
        windowController = new WindowController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void loginClicked() throws Exception {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (!filledInCredentials(username, password))
            return;

        // TODO: Implement login check and show message if needed. If login successful then show admin window
        LoginService loginService = LoginService.getInstance();
        try {
            loginService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (loginService.getAccessToken() != null && loginService.getRefreshToken() != null) {
            windowController.showWindow("Admin", "Adminpaneel");
        } else {
            statusLabel.setText("Controleer uw gegevens");
        }
    }

    private boolean filledInCredentials(String username, String password) {
        if (username.isBlank() && password.isBlank()) {
            statusLabel.setText("Vul eerst uw inloggegevens in!");
            return false;
        }

        if (username.isBlank()) {
            statusLabel.setText("Voer een gebruikersnaam in!");
            return false;
        }

        if (password.isBlank()) {
            statusLabel.setText("Voer een wachtwoord in!");
            return false;
        }

        return true;
    }
}