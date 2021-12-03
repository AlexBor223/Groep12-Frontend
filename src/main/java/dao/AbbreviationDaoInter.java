package dao;

import models.Abbreviation;

import java.util.ArrayList;

public interface AbbreviationDaoInter {
    ArrayList<Abbreviation> getAllAbbreviations();

    ArrayList<Abbreviation> filterAbbreviations(String letters, String departmentName);

    Abbreviation getAbbreviationById(long id);

    void updateAbbreviation(Abbreviation abbreviation);

    void deleteAbbreviationById(long id);

    void likeAbbreviation(long id);

    void dislikeAbbreviation(long id);
}
