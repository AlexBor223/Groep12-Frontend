package controllers;

import dao.AbbreviationDao;
import models.Abbreviation;

public class AbrAddController {

    AbbreviationDao abbreviationDao = new AbbreviationDao();

    public void createAbbreviation(Abbreviation abbreviation) throws Exception {
        abbreviationDao.updateAbbreviation(abbreviation);
    }

    public void giveLike(Long id) throws Exception {
        abbreviationDao.LikeAbbreviation(id);
    }

    public void giveDislike(long id) throws Exception {
        abbreviationDao.DislikeAbbreviation(id);
    }

    public void delete(Long id) throws Exception {
        abbreviationDao.deleteAbbreviation(id);
    }

}
