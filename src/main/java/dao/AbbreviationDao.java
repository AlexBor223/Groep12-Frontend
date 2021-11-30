package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Abbreviation;
import services.HttpService;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
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
        Type abbreviationListType = new TypeToken<ArrayList<Abbreviation>>(){}.getType();
        return gson.fromJson(json, abbreviationListType);
    }

    @Override
    public ArrayList<Abbreviation> getAllAbbreviations() {
        HttpResponse<String> response = httpService.getResponse(abbreviationPath);

        if (response != null)
            return (response.statusCode() == 200) ? jsonToAbbreviationList(response.body()) : new ArrayList<>();

        return new ArrayList<>();
    }

    @Override
    public ArrayList<Abbreviation> searchAbbreviations(String abbreviation, String department) {
        HttpResponse<String> response = httpService.getResponse(String.format("%s/%s?department=%s&letters=%s", abbreviationPath, "filter", department, abbreviation));

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
