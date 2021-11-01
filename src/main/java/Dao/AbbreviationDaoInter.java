package Dao;

import models.Abbreviation;

import java.util.List;

public interface AbbreviationDaoInter {
    Abbreviation getAbbreviaton(Integer id);
    List<Abbreviation> getAllAbbreviations();
    void updateAbbreviation(String abbreviation) throws Exception;
    void deleteAbbreviation(Abbreviation abbreviation);
}
