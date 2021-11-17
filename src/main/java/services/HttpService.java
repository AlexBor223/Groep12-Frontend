package services;

import kong.unirest.*;
import kong.unirest.json.JSONObject;
import models.Abbreviation;
import models.DepartmentModel;
import models.Abbreviation;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * facilitates the connection to the back-end
 */
public class HttpService {

    /**
     * connection information
     *
     * @author Ruben
     */
    private String host = "http://localhost:8080";
    private String charset = "UTF-8";


    /**
     * send request for filtered abbreviations based on Department name and starting letters
     *
     * @param url the url at which the abbreviations api is located
     * @param Dep the filtered on department name
     * @param abr the searched for starting letters
     * @return a list of all abbreviations fitting the description
     * @throws Exception failure of connection
     * @author Ruben, Martin
     */
    public List<Abbreviation> SearchAbbreviationsObject(String url, String Dep, String abr) throws Exception {
        HttpResponse<List<Abbreviation>> response = Unirest.get(host + url)
                .header("accept", "application/json")
                .queryString("department", Dep)
                .queryString("letters", abr)
                .asObject(new GenericType<List<Abbreviation>>() {
                });
        return response.getBody();
    }

    /**
     * gets all departments from the back-end
     *
     * @param url the url at which the abbreviations api is located
     * @return a list of all departments in the back-end
     * @throws Exception failure of connection
     * @author Ruben, Martin
     */
    public List<DepartmentModel> GetAllDepartments(String url) throws Exception {
        System.out.println(host + url);
        HttpResponse<List<DepartmentModel>> response = Unirest.get(host + url)
                .asObject(new GenericType<List<DepartmentModel>>() {
                });
        return response.getBody();
    }

    /**
     * login method
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public InputStream login(String username, String password) throws Exception {
        String uri = "/api/login";
        String url = host + uri;
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
        org.apache.http.HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);
        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                return instream;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    /**
     * gets all abbreviations from the back-end
     *
     * @param url the url at which the department api is located
     * @return a list of all departments in the back-end
     * @throws Exception
     * @author Ruben, Martin
     */
    public List<Abbreviation> GetAllAbbreviations(String url) throws Exception {
        HttpResponse<List<Abbreviation>> response = Unirest.get(host + url)
                .header("accept", "application/json")
                .asObject(new GenericType<List<Abbreviation>>() {
                });
        return response.getBody();
    }

    /**
     * a basic get request for a basic json //not actually used//
     *
     * @param id searches for ID object
     * @return requested Json object
     * @throws Exception
     * @author Ruben, Martin
     */
    public HttpResponse<JsonNode> SearchObject(Integer id) throws Exception {
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
     *
     * @param url url of the needed item
     * @return requested Json object
     * @throws Exception
     * @author Ruben, Martin
     */
    public HttpResponse<JsonNode> GetAllObjects(String url) throws Exception {
        HttpResponse<kong.unirest.JsonNode> abbreviation = Unirest.get(host)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        return abbreviation;
    }


    /**
     * a basic push query
     *
     * @param url          url of pushed item
     * @param abbreviation item that's being updated
     * @return requested Json object
     * @throws Exception connection error
     * @author Ruben, Martin
     */
    public boolean AddOrUpdateObject(String url, Abbreviation abbreviation) throws Exception {

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host + url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(abbreviation)
                .asJson();

        System.out.println("body: " + response.getBody());
        return (response.getStatus() == 201);
    }

    /**
     * deletes object based on path and id
     *
     * @param url the path at which the object is located
     * @param id  the id of the object
     * @return successful or not
     * @throws Exception connection error
     * @author Ruben, Martin
     */
    public boolean DeleteObject(String url, long id) throws Exception {
        HttpResponse<kong.unirest.JsonNode> response = Unirest.delete(host + "/" + id)
                .header("Accept", "application/json")
                .asJson();
        return (response.getStatus() == 201);
    }


    /**
     * gives an abbreviation an like or dislike
     *
     * @param url the url of the abbreviation and like or dislike
     * @return successful or not
     * @throws Exception
     * @author Ruben, Martin
     */
    public boolean LikeObject(String url) throws Exception {
        System.out.println(host + url);

        HttpResponse<kong.unirest.JsonNode> response = Unirest.post(host + url)
                .header("Accept", "application/json")
                .asJson();

        return (response.getStatus() == 201);
    }


}

