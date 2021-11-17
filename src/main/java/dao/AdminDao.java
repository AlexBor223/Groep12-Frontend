package dao;

import services.HttpService;

import java.io.InputStream;
import java.util.List;

public class AdminDao implements AdminDaoInter {
    private static HttpService httpService = new HttpService();
    private InputStream JWT;
    @Override
    public void login(String username, String password) throws Exception{
       JWT = httpService.login(username, password);
       System.out.println(JWT);
    }
}