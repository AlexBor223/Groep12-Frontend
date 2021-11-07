package controllers;

import dao.AbbreviationDao;
import models.Abbreviation;

public class AbrAddController {

    AbbreviationDao abbreviationDao = new AbbreviationDao();

    /**
     * calls the abbreviation dao to create a abreviation
     *
     * @param abbreviation
     * @author Martin
     */
    public void createAbbreviation(Abbreviation abbreviation) throws Exception {
        abbreviationDao.updateAbbreviation(abbreviation);
    }

    /**
     * calls the abbreviation dao to like a abreviation
     *
     * @param id the id of the abbreviation
     * @author Martin
     */
    public void giveLike(Long id) throws Exception {
        abbreviationDao.LikeAbbreviation(id);
    }

    /**
     * calls the abbreviation dao to dislike a abreviation
     *
     * @param id the id of the abbreviation
     * @author Martin
     */
    public void giveDislike(long id) throws Exception {
        abbreviationDao.DislikeAbbreviation(id);
    }

    /**
     * calls the abbreviation dao to delete a abreviation
     *
     * @param id the id of the abbreviation
     * @author Martin
     */
    public void delete(Long id) throws Exception {
        abbreviationDao.deleteAbbreviation(id);
    }

}
