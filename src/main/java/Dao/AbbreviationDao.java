package Dao;

import models.Abbreviation;
import services.HttpService;

import java.util.ArrayList;
import java.util.List;

public class AbbreviationDao implements AbbreviationDaoInter{

    List<Abbreviation> abbreviations;
    static HttpService httpService = new HttpService();




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

    @Override  //verwijder een abbreviation
    public void deleteAbbreviation(Abbreviation abbreviation) {
        abbreviations.remove(abbreviation.getId());
    }


    @Override
    public void LikeAbbreviation(Long id) throws Exception {
        httpService.LikeObject("/GiveLike", id);
    }

    @Override
    public void DisLikeAbbreviation(Long id) throws Exception {
        httpService.LikeObject("/GiveDisLike", id);
    }
}