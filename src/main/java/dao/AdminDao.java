package dao;

import services.HttpService;

public class AdminDao implements AdminDaoInter {
    private static HttpService httpService = new HttpService();
    private final String AbrPath = "/api/abbreviations";

    @Override
    public void login(String username, String password) throws Exception{
        httpService.login(username, password);
    }
}