package services;

import kong.unirest.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public class LoginService {
    private static LoginService loginService;
    private static HttpService httpService;
    private final String host = "http://localhost:8080";
    private final String charset = "UTF-8";
    private String accessToken;
    private String refreshToken;

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (loginService == null) {
            loginService = new LoginService();
            httpService = HttpService.getInstance();
        }

        return loginService;
    }

    public void login(String username, String password) throws Exception {
        String url = "/api/login";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://localhost:8080/api/login"))
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            try {
                JSONObject responseJSON = new JSONObject(result);
                setAccessToken(responseJSON.getString("access_token"));
                setRefreshToken(responseJSON.getString("refresh_token"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}