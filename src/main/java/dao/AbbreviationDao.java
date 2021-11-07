package dao;

import models.Abbreviation;
import services.HttpService;

import java.util.*;

/**
 * makes abbreviation data accessible for front-end
 */

public class AbbreviationDao implements AbbreviationDaoInter{


    List<Abbreviation> abbreviations;

    /**
     * items needed for connections
     */
    private static HttpService httpService = new HttpService();
    private final String AbrPath = "/api/abbreviations";


    /**
     * creates a basic abbreviationDao
     */
    public AbbreviationDao() {
        abbreviations = new ArrayList<Abbreviation>();
    }

    /**
     * gets an Abbreviation from the server by ID
     * @param id the id of the abbreviation
     * @return the abbreviation of which the id is searched
     * @throws Exception
     */
    @Override
    public Abbreviation getAbbreviaton(Integer id) throws Exception {
        httpService.SearchObject(id);

     return null;
    }

    /**
     * gets all abbreviations from the server
     * @return all abbreviations
     * @throws Exception HttpServer error
     */

    @Override
    public List<Abbreviation> getAllAbbreviations() throws Exception {
        httpService.GetAllObjects("");

        return abbreviations;
    }

    /**
     * updates the abbreviation at the back-end
     * @param abbreviation the edited version of the abbreviation
     * @throws Exception HttpServer error
     */
    @Override
    public void updateAbbreviation(Abbreviation abbreviation) throws Exception {
        httpService.AddOrUpdateObject(AbrPath, abbreviation);
    }

    /**
     * Searched for abbreviation in back-end by letters and department
     * @param abbreviation the letters which are being searched
     * @param department the department that's filtered on, can be an empty string
     * @return a list of all fitting abbreviations
     */
    @Override
    public List<Abbreviation> searchAbbreviations(String abbreviation, String department) {
        abbreviations = new ArrayList<Abbreviation>();
        try {
            abbreviations = httpService.SearchAbbreviationsObject( AbrPath+"/filter", abbreviation, department);
        }catch(Exception e){
            e.printStackTrace();
        }

        return abbreviations;
    }

    /**
     * Deletes an abbreviation in the back-end by ID
     * @param id the ID of the abbreviation
     * @throws Exception HttpService error
     */
    @Override
    public void deleteAbbreviation(Long id) throws Exception {
        httpService.DeleteObject("",id);
    }

    /**
     * gives a like to an abbreviation by I
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Boolean LikeAbbreviation(Long id) throws Exception {
        return httpService.LikeObject(String.format("%s/%d/%s",AbrPath, id,"GiveLike"));
    }

    /**
     * gives a dislike to an abbreviation by ID
     * @param id the disliked abbreviation
     * @return wether the dislike succeeds
     * @throws Exception
     */
    @Override
    public Boolean DislikeAbbreviation(Long id) throws Exception {
        return httpService.LikeObject(String.format("%s/%d/%s",AbrPath, id,"GiveDisLike"));
    }
}