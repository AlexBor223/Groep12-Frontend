package Dao;

import kong.unirest.JsonNode;
import models.Abbreviation;
import services.HttpService;

import java.util.*;

public class AbbreviationDao implements AbbreviationDaoInter{

    List<Abbreviation> abbreviations;

    private static HttpService HttpService = new HttpService();
    private final String AbrPath = "/api/abbreviations";


    public AbbreviationDao() {
        abbreviations = new ArrayList<Abbreviation>();
    }

    @Override
    public Abbreviation getAbbreviation(Integer id) {

        return abbreviations.get(id);
    }

    @Override //krijg alle abbreviations
    public List<Abbreviation> getAllAbbreviations() {
        return abbreviations;
    }

    @Override
    public void updateAbbreviation(Abbreviation abbreviation) {

    }

    @Override
    public ArrayList<Abbreviation> searchAbbreviations(String abbreviation, String department) {

//        try {
//            abbreviations = HttpService.SearchAbbreviationsObject( AbrPath, abbreviation, department);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        try {
            abbreviations = HttpService.GetAllAbbreviations(AbrPath);
       }catch(Exception e){
            e.printStackTrace();
       }
        ArrayList<Abbreviation> abbreviationList = new ArrayList<Abbreviation>();
        abbreviationList = (ArrayList<Abbreviation>) abbreviations;
        return abbreviationList;
    }

    @Override  //verwijder een abbreviation
    public void deleteAbbreviation(Abbreviation abbreviation) {
        abbreviations.remove(abbreviation.getId());
    }
}
