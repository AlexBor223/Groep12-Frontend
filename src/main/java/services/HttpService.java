package services;

import kong.unirest.*;
import models.Abbreviation;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLEncoder;

public class HttpService {

    private String host = "https://ptsv2.com";
    private String charset = "UTF-8";


    public JsonNode SearchObject(String url) throws Exception{
        HttpResponse<JsonNode> response = Unirest.get(host+url)
                .asJson();
        return response.getBody();
    }

    public boolean AddOrUpdateObject(String url, String abbreviation) throws Exception{
        HttpResponse<JsonNode> response = Unirest.post(host+url)
                .header("accept", "application/json")
                .body(abbreviation)
                .asJson();

        return (response.getStatus()==201);
    }

    public boolean DeleteObject(String url) throws Exception {
        HttpResponse<JsonNode> response = Unirest.delete(host+url)
                .asJson();
        return (response.getStatus() == 201);
    }






}
