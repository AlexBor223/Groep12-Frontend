package dao;

import models.Admin;

public interface AdminDaoInter {
    void login(String username, String password) throws Exception;
}