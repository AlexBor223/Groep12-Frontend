package Dao;

import models.Abbreviation;

import java.util.List;

public interface AbbreviationDaoInter {
    List<Abbreviation> getAllAbbreviations() throws Exception;
    void updateAbbreviation(Abbreviation abbreviation) throws Exception;
    List<Abbreviation> searchAbbreviations(String abbreviation, String department);
    Abbreviation getAbbreviaton(Integer id) throws Exception;
    Boolean LikeAbbreviation(Long id) throws Exception;
    Boolean DislikeAbbreviation(Long id) throws Exception;
    public void deleteAbbreviation(Long id) throws Exception;
}
