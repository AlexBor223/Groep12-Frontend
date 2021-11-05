package views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Admin;


public class LoginView {

    @FXML
    private TextField AdminUsername;
    @FXML
    private TextField AdminPassword;

    Admin admin = new Admin();

    public void getInput() {

        String username = AdminUsername.getText();
        String password = AdminPassword.getText();



        admin.setUsername(username);
        admin.setPassword(password);
         

    }
}
