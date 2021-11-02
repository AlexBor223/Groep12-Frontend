package Dao;

import models.Abbreviation;

import java.util.List;

public interface AbbreviationDaoInter {
    Abbreviation getAbbreviation(Integer id);
    List<Abbreviation> getAllAbbreviations();
    void updateAbbreviation(Abbreviation abbreviation);
    void deleteAbbreviation(Abbreviation abbreviation);
    List<Abbreviation> searchAbbreviations(String abbreviation, String department);
}
