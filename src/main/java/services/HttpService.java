package services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpService {
    URL url = new URL("http://localhost:8080/");

    // Open a connection(?) on the URL(??) and cast the response(???)
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    // Now it's "open", we can set the request method, headers etc.


    // This line makes the request
    InputStream responseStream = connection.getInputStream();


    public HttpService() throws IOException {
    }

// Finally we have the response

}
