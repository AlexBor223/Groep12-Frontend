package services;


import com.sun.glass.ui.GlassRobot;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.concurrent.ExecutionException;

public class HttpService {


    public static void main(String[] args) throws IOException, InterruptedException {
        synchronousRequest();
    }

    private static void synchronousRequest() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create("http://localhost:8080/"))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
//        var response = client.send(request, );

// the response:
//        System.out.println(response.body());
    }
}
