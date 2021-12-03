package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Abbreviation;
import services.HttpService;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AbbreviationDao implements AbbreviationDaoInter {
    private final HttpService httpService;
    private final String abbreviationPath;

    public AbbreviationDao() {
        httpService = HttpService.getInstance();
        abbreviationPath = "/api/abbreviations";
    }

    private Abbreviation jsonToAbbreviation(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Abbreviation.class);
    }

    private ArrayList<Abbreviation> jsonToAbbreviationList(String json) {
        Gson gson = new Gson();
        Type abbreviationListType = new TypeToken<ArrayList<Abbreviation>>() {}.getType();
        return gson.fromJson(json, abbreviationListType);
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return value;
        }
    }

    private String getFilterString(String letters, String departmentName) {
        String url = "/filter?";
        letters = encodeValue(letters);
        departmentName = encodeValue(departmentName);

        if (!letters.isBlank())
            url += "letters=" + letters;

        if (!departmentName.isBlank()) {
            String departmentString = "department=" + departmentName;
            url += (url.endsWith("?")) ? departmentString : "&" + departmentString;
        }

        return url;
    }

    @Override
    public ArrayList<Abbreviation> getAllAbbreviations() {
        HttpResponse<String> response = httpService.getResponse(abbreviationPath);

        if (response != null)
            return (response.statusCode() == 200) ? jsonToAbbreviationList(response.body()) : new ArrayList<>();

        return new ArrayList<>();
    }

    @Override
    public ArrayList<Abbreviation> filterAbbreviations(String letters, String departmentName) {
        HttpResponse<String> response = httpService.getResponse(abbreviationPath + getFilterString(letters, departmentName));

        if (response != null)
            return (response.statusCode() == 200) ? jsonToAbbreviationList(response.body()) : new ArrayList<>();

        return new ArrayList<>();
    }

    @Override
    public Abbreviation getAbbreviationById(long id) {
        HttpResponse<String> response = httpService.getResponse(abbreviationPath + "/" + id);

        if (response != null)
            return (response.statusCode() == 200) ? jsonToAbbreviation(response.body()) : null;

        return null;
    }

    @Override
    public void updateAbbreviation(Abbreviation abbreviation) {
        httpService.postResponse(abbreviationPath, abbreviation);
    }

    @Override
    public void deleteAbbreviationById(long id) {
        HttpResponse<String> response = httpService.deleteResponse(abbreviationPath + "/" + id);
    }

    @Override
    public void likeAbbreviation(long id) {
        HttpResponse<String> response = httpService.postResponse(
                String.format("%s/%d/%s", abbreviationPath, id, "Like"),
                null);
    }

    @Override
    public void dislikeAbbreviation(long id) {
        HttpResponse<String> response = httpService.postResponse(
                String.format("%s/%d/%s", abbreviationPath, id, "Dislike"),
                null);
    }
}
