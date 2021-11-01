package services;

import kong.unirest.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLEncoder;

public class HttpService {

    private String host = "http://info.cern.ch";
    private String charset = "UTF-8";


    public JsonNode SearchObject(String url) throws Exception{
        String query = String.format( URLEncoder.encode(url, charset));
        HttpResponse<JsonNode> response = Unirest.get(host+url)
                .asJson();
        return response.getBody();
    }

    public boolean AddOrUpdateObject(String url) throws Exception{
        String query = String.format( URLEncoder.encode(url, charset));
        HttpResponse<JsonNode> response = Unirest.post(host+url)
                .asJson();
        return (response.getStatus()==201);
    }

    public boolean DeleteObject(String url) throws Exception {
        String query = String.format(URLEncoder.encode(url, charset));
        HttpResponse<JsonNode> response = Unirest.delete(host + url)
                .asJson();
        return (response.getStatus() == 201);
    }






}
