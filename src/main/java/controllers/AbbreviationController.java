package controllers;

import dao.AbbreviationDao;
import models.Abbreviation;
import models.AbbreviationCloner;

import java.util.ArrayList;

public class AbbreviationController {
    private final AbbreviationDao abbreviationDao;
    private final AbbreviationCloner abbreviationCloner;

    public AbbreviationController() {
        abbreviationDao = new AbbreviationDao();
        abbreviationCloner = new AbbreviationCloner();
    }

    public ArrayList<Abbreviation> getAll() {
        return abbreviationDao.getAllAbbreviations();
    }

    public ArrayList<Abbreviation> filter(String letters, String departmentName) {
        return abbreviationDao.filterAbbreviations(letters, departmentName);
    }

    public Abbreviation clone(Abbreviation abbreviation) {
        return abbreviationCloner.clone(abbreviation);
    }

    public void create(Abbreviation abbreviation) {
        abbreviationDao.addAbbreviation(abbreviation);
    }

    public void update(Abbreviation abbreviation) {
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
}
