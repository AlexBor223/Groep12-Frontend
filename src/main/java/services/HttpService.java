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

/**
 * facilitates the connection to the back-end
 */
public class HttpService {

    /**
     * connection information
     */
    private String host = "http://localhost:8080";
    private String charset = "UTF-8";


    /**
     * send request for filtered abbreviations based on Department name and starting letters
     * @param url the url at which the abbreviations api is located
     * @param Dep the filtered on department name
     * @param abr the searched for starting letters
     * @return a list of all abbreviations fitting the description
     * @throws Exception failure of connection
     */
    public List<Abbreviation> SearchAbbreviationsObject(String url, String Dep, String abr) throws Exception{
        HttpResponse<List<Abbreviation>> response = Unirest.get(host+url)
                .header("accept", "application/json")
                .queryString("department", Dep)
                .queryString("letters", abr)
                .asObject(new GenericType<List<Abbreviation>>() {
        });
        return response.getBody();
    }

    /**
     * gets all departments from the back-end
     * @param url  the url at which the abbreviations api is located
     * @return  a list of all departments in the back-end
     * @throws Exception failure of connection
     */
    public List<DepartmentModel> GetAllDepartments(String url) throws Exception{
        System.out.println(host+url);
        HttpResponse<List<DepartmentModel>> response = Unirest.get(host+url)
                .asObject(new GenericType<List<DepartmentModel>>() {
        });
        return response.getBody();
    }

    /**
     * gets all abbreviations from the back-end
     * @param url the url at which the department api is located
     * @return a list of all departments in the back-end
     * @throws Exception
     */
    public List<Abbreviation> GetAllAbbreviations(String url) throws Exception{
        HttpResponse<List<Abbreviation>> response = Unirest.get(host+url)
                .header("accept", "application/json")
                .asObject(new GenericType<List<Abbreviation>>() {
                });
        return response.getBody();
    }

    /**
     * a basic get request for a basic json //not actually used//
     * @param id searches for ID object
     * @return requested Json object
     * @throws Exception
     */
    public HttpResponse<JsonNode> SearchObject(Integer id) throws Exception{
        HttpResponse<kong.unirest.JsonNode> abbreviation = Unirest.get(host)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .queryString("id", id)
                .asJson();

        System.out.println("Uitkomstabbreviation: " + abbreviation.getBody().getArray().get(id));
        return abbreviation;
    }

    /**
     * a basic get request for a basic json //not actually used//
     * @param url url of the needed item
     * @return requested Json object
     * @throws Exception
     */
    public HttpResponse<JsonNode> GetAllObjects(String url) throws Exception{
        HttpResponse<kong.unirest.JsonNode> abbreviation = Unirest.get(host)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        return abbreviation;
    }


    /**
     * a basic push query
     * @param url url of pushed item
     * @param abbreviation item that's being updated
     * @return requested Json object
     * @throws Exception connection error
     */
    public boolean AddOrUpdateObject(String url, Abbreviation abbreviation) throws Exception{

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host + url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(abbreviation)
                .asJson();

        System.out.println("body: " + response.getBody());
        return (response.getStatus()==201);
    }

    /**
     * deletes object based on path and id
     * @param url the path at which the object is located
     * @param id the id of the object
     * @return  successful or not
     * @throws Exception connection error
     */
    public boolean DeleteObject(String url, long id) throws Exception {
        HttpResponse<kong.unirest.JsonNode> response = Unirest.delete(host + "/" + id)
                .header("Accept", "application/json")
                .asJson();
        return (response.getStatus() == 201);
    }


    /**
     * gives an abbreviation an like or dislike
     * @param url the url of the abbreviation and like or dislike
     * @return successful or not
     * @throws Exception
     */
    public boolean LikeObject(String url) throws Exception{
        System.out.println(host + url);

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host + url )
                .header("Accept", "application/json")
                .asJson();

        return (response.getStatus()==201);
    }



}

