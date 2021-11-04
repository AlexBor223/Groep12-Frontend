package Dao;

import models.Abbreviation;

import java.util.List;

public interface AbbreviationDaoInter {
    List<Abbreviation> getAllAbbreviations();
    void updateAbbreviation(Abbreviation abbreviation);
    void deleteAbbreviation(Abbreviation abbreviation);
    List<Abbreviation> searchAbbreviations(String abbreviation, String department);
    Abbreviation getAbbreviaton(Integer id) throws Exception;
    void LikeAbbreviation(Long id) throws Exception;
    void DisLikeAbbreviation(Long id) throws Exception;
}
