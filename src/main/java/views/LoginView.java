package views;

import Controllers.AdminController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Admin;


public class LoginView {
    private final AdminController adminController;

    @FXML
    private TextField AdminUsername;
    @FXML
    private TextField AdminPassword;

            Admin admin = new Admin();

    public LoginView() {
            adminController = new AdminController();
        }

        public void getInput() throws Exception {
            String username = AdminUsername.getText();
            String password = AdminPassword.getText();

            admin.setUsername(username);
            admin.setPassword(password);

            adminController.login(admin.getUsername(), admin.getPassword());
        }
    }