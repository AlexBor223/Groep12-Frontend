package services;

import kong.unirest.*;
import models.Abbreviation;


public class HttpService {

    private String host = "http://localhost:8080/api/abbreviations";
    private String charset = "UTF-8";


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

        System.out.println("Alle Abr: " + abbreviation.getBody().getArray());
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

    public boolean DeleteObject(String url) throws Exception {
        HttpResponse<kong.unirest.JsonNode> response = Unirest.delete(host)
                .asJson();
        return (response.getStatus() == 201);
    }



    public boolean LikeObject(String url, long id) throws Exception{

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host +"/" + id + url )
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();


        System.out.println(host +"/" + id +"/GiveLike");
        System.out.println("body: " + response.getBody());
        return (response.getStatus()==201);
    }






}
