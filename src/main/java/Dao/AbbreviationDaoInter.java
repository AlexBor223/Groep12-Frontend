package Dao;

import models.Abbreviation;

import java.util.List;

public interface AbbreviationDaoInter {
    Abbreviation getAbbreviaton(Integer id) throws Exception;
    List<Abbreviation> getAllAbbreviations() throws Exception;
    void updateAbbreviation(Abbreviation abbreviation) throws Exception;
    void deleteAbbreviation(Abbreviation abbreviation);
    void LikeAbbreviation(Long id) throws Exception;
    void DisLikeAbbreviation(Long id) throws Exception;
}
