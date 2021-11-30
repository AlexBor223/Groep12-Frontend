package controllers;

import dao.AbbreviationDao;
import models.Abbreviation;

import java.util.ArrayList;

public class AbbreviationController {
    private final AbbreviationDao abbreviationDao;

    public AbbreviationController() {
        abbreviationDao = new AbbreviationDao();
    }

    public ArrayList<Abbreviation> getAll() {
        return abbreviationDao.getAllAbbreviations();
    }

    public void create(Abbreviation abbreviation) {
        abbreviationDao.updateAbbreviation(abbreviation);
    }

    public void delete(long id) {
        abbreviationDao.deleteAbbreviationById(id);
    }

    public void like(long id) {
        abbreviationDao.likeAbbreviation(id);
    }

    public void dislike(long id) {
        abbreviationDao.dislikeAbbreviation(id);
    }

    public ArrayList<Abbreviation> getFiltered(String department, String abbreviation) {
        return abbreviationDao.searchAbbreviations(department, abbreviation);
    }
}
