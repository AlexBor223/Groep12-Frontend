package Dao;

import models.Abbreviation;
import services.HttpService;

import java.util.ArrayList;
import java.util.List;

public class AbbreviationDao implements AbbreviationDaoInter{

    List<Abbreviation> abbreviations;
    static HttpService httpService = new HttpService();

    public Abbreviation[] getFilteredAbbreviations(){
        Abbreviation[] abbreviations;
        abbreviations = new Abbreviation[4];

        return abbreviations;
    }


    public AbbreviationDao() {
        abbreviations = new ArrayList<Abbreviation>();
    }

    @Override
    public Abbreviation getAbbreviaton(Integer id) {

        return abbreviations.get(id);
    }

    @Override //krijg alle abbreviations
    public List<Abbreviation> getAllAbbreviations() {
        return abbreviations;
    }

    @Override
    public void updateAbbreviation(String abbreviation) throws Exception {
        httpService.AddOrUpdateObject("/t/eso94-1635791662/post", abbreviation);
    }

    @Override  //verwijder een abbreviation
    public void deleteAbbreviation(Abbreviation abbreviation) {
        abbreviations.remove(abbreviation.getId());
    }
}
