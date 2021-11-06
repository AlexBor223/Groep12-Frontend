package Controllers;

import dao.AdminDao;
import models.Abbreviation;

public class AdminController {
    private String JWT;
    private String username;
    private String password;

    AdminDao adminDao = new AdminDao();

    public void login(String username, String password) throws Exception {
        adminDao.login(username, password);
    }
}
