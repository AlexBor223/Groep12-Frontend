package Dao;

import models.Abbreviation;

import java.util.ArrayList;
import java.util.List;

public class AbbreviationDao implements AbbreviationDaoInter{

    List<Abbreviation> abbreviations;


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
    public void updateAbbreviation(Abbreviation abbreviation) {

    }

    @Override  //verwijder een abbreviation
    public void deleteAbbreviation(Abbreviation abbreviation) {
        abbreviations.remove(abbreviation.getId());
    }
}
