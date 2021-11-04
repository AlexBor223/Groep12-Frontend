package services;

import kong.unirest.*;
import models.Abbreviation;
import models.DepartmentModel;
import models.Abbreviation;

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
    public HttpResponse<JsonNode> SearchObject(Integer id) throws Exception{
        HttpResponse<kong.unirest.JsonNode> abbreviation = Unirest.get(host)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .queryString("id", id)
                .asJson();

        System.out.println("Uitkomstabbreviation: " + abbreviation.getBody().getArray().get(id));
        return abbreviation;
    }

    public HttpResponse<JsonNode> GetAllObjects(String url) throws Exception{
        HttpResponse<kong.unirest.JsonNode> abbreviation = Unirest.get(host)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        return abbreviation;
    }



    public boolean AddOrUpdateObject(String url, Abbreviation abbreviation) throws Exception{

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host )
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(abbreviation)
                .asJson();

        System.out.println("body: " + response.getBody());
        return (response.getStatus()==201);
    }

    public boolean DeleteObject(String url, long id) throws Exception {
        HttpResponse<kong.unirest.JsonNode> response = Unirest.delete(host + "/" + id)
                .header("Accept", "application/json")
                .asJson();
        return (response.getStatus() == 201);
    }



    public boolean LikeObject(String url) throws Exception{
        System.out.println(host + url);

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host + url )
                .header("Accept", "application/json")
                .asJson();

        return (response.getStatus()==201);
    }

}

