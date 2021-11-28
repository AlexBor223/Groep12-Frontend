package services;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {
    private static HttpService httpService;
    private final HttpClient client;
    private final String host;
    private final String defaultUserAgent;
    private final String defaultContentType;

    private HttpService() {
        client = HttpClient.newHttpClient();
        host = "http://localhost:8080";
        defaultContentType = "text/plain; charset=utf-8";
        defaultUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:71.0) Gecko/20100101 Firefox/71.0";
    }

    public static HttpService getInstance() {
        if (httpService == null)
            httpService = new HttpService();

        return httpService;
    }

    public String objectToJsonString(Object object) {
        return new Gson().toJson(object);
    }

    private String getConcatenatedUrl(String url) {
        return (url != null) ? host + url : host;
    }

    public HttpResponse<String> getResponse(String url) {
        HttpResponse<String> response = null;
        url = getConcatenatedUrl(url);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .setHeader("User-Agent", defaultUserAgent)
                    .setHeader("Content-Type", defaultContentType)
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    public HttpResponse<String> postResponse(String url, Object object) {
        HttpResponse<String> response = null;
        url = getConcatenatedUrl(url);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(objectToJsonString(object)))
                    .uri(URI.create(url))
                    .setHeader("User-Agent", defaultUserAgent)
                    .header("Content-Type", "application/json")
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    public HttpResponse<String> deleteResponse(String url) {
        HttpResponse<String> response = null;
        url = getConcatenatedUrl(url);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create(url))
                    .setHeader("User-Agent", defaultUserAgent)
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }
}