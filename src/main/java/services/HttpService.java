package services;

import kong.unirest.*;
import models.Abbreviation;
import models.DepartmentModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLEncoder;
import java.util.List;

public class HttpService {

    private String host = "http://localhost:8080";
    private String charset = "UTF-8";


    public List<Abbreviation> SearchAbbreviationsObject(String url, String Dep, String abr) throws Exception{
        HttpResponse<List<Abbreviation>> response = Unirest.get(host+url)
                .header("accept", "application/json")
                .queryString("Department", Dep)
                .queryString("Abbreviation", abr)
                .asObject(new GenericType<List<Abbreviation>>() {
        });
        return response.getBody();
    }

    public List<DepartmentModel> GetAllDepartments(String url) throws Exception{
        System.out.println(host+url);
        HttpResponse<List<DepartmentModel>> response = Unirest.get(host+url)
                .asObject(new GenericType<List<DepartmentModel>>() {
        });
        return response.getBody();
    }

    public List<Abbreviation> GetAllAbbreviations(String url) throws Exception{
        HttpResponse<List<Abbreviation>> response = Unirest.get(host+url)
                .header("accept", "application/json")
                .asObject(new GenericType<List<Abbreviation>>() {
                });
        return response.getBody();
    }



    public boolean AddOrUpdateObject(String url) throws Exception{
        HttpResponse<JsonNode> response = Unirest.post(host+url)
                .asJson();
        return (response.getStatus()==201);
    }

    public boolean DeleteObject(String url) throws Exception {
        HttpResponse<JsonNode> response = Unirest.delete(host+url)
                .asJson();
        return (response.getStatus() == 201);
    }






}
