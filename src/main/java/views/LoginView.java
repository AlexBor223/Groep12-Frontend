package views;

import Controllers.AdminController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Admin;


public class LoginView {
    private final AdminController adminController;
    Admin admin;

    @FXML
    private TextField AdminUsername;
    @FXML
    private TextField AdminPassword;

    public LoginView() {
        adminController = new AdminController();
        admin = new Admin();
    }

    public void getInput() throws Exception {
        String username = AdminUsername.getText();
        String password = AdminPassword.getText();

        admin.setUsername(username);
        admin.setPassword(password);

        adminController.login(admin.getUsername(), admin.getPassword());
    }
}