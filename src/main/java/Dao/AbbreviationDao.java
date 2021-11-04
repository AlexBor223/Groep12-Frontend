package Dao;

import kong.unirest.JsonNode;
import models.Abbreviation;
import services.HttpService;

import java.util.*;

public class AbbreviationDao implements AbbreviationDaoInter {

    List<Abbreviation> abbreviations;

    private static HttpService httpService = new HttpService();
    private final String AbrPath = "api/abbreviations";


    public AbbreviationDao() {
        abbreviations = new ArrayList<Abbreviation>();
    }

    @Override
    public Abbreviation getAbbreviaton(Integer id) throws Exception {
        httpService.SearchObject(id);

        return null;
    }

    @Override //krijg alle abbreviations
    public List<Abbreviation> getAllAbbreviations() throws Exception {
        httpService.GetAllObjects("");

        return abbreviations;
    }

    @Override
    public void updateAbbreviation(Abbreviation abbreviation) throws Exception {
        httpService.AddOrUpdateObject("", abbreviation);
    }

    @Override
    public ArrayList<Abbreviation> searchAbbreviations(String abbreviation, String department) {

//        try {
//            abbreviations = HttpService.SearchAbbreviationsObject( AbrPath, abbreviation, department);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        try {
            abbreviations = httpService.GetAllAbbreviations(AbrPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Abbreviation> abbreviationList = new ArrayList<Abbreviation>();
        abbreviationList = (ArrayList<Abbreviation>) abbreviations;
        return abbreviationList;
    }

    @Override  //verwijder een abbreviation
    public void deleteAbbreviation(Long id) throws Exception {
        httpService.DeleteObject("", id);
    }


    @Override
    public Boolean LikeAbbreviation(Long id) throws Exception {
        return httpService.LikeObject(String.format("/%s/%d/%s", AbrPath, id, "GiveLike"));
    }

    @Override
    public Boolean DislikeAbbreviation(Long id) throws Exception {
        return httpService.LikeObject(String.format("/%s/%d/%s", AbrPath, id, "GiveDisLike"));
    }
}