import controllers.AbbreviationController;
import controllers.DepartmentController;
import models.Abbreviation;
import org.junit.Test;

import static org.junit.Assert.*;

public class RubenUnitTests {

    private final String testAbbreviationName = "8pvkyELJngyPN5y";

    @Test
    public void Should_returnIncreaseLikes(){
        //Arrange
        AbbreviationController  abbreviationController = new AbbreviationController();
        DepartmentController departmentController = new DepartmentController();
        Abbreviation abbreviation = abbreviationController.filter("", departmentController.getAllDepartmentNames().get(0)).get(0);
        //Act
        abbreviationController.like(abbreviation.getId());
        int difference = abbreviationController.filter(abbreviation.getLetters(), departmentController.getAllDepartmentNames().get(0)).get(0).getLikes()-abbreviation.getLikes();

        //assert/reset
        abbreviationController.dislike(abbreviation.getId());
        assertEquals(1 , difference);
    }

    @Test
    public void Should_beRemoved(){
        //Arrange
        AbbreviationController  abbreviationController = new AbbreviationController();
        DepartmentController departmentController = new DepartmentController();
        Abbreviation abbreviation;
        abbreviation = new Abbreviation(departmentController.getAllDepartments().get(0).getId(), testAbbreviationName, "test", 0);
        abbreviationController.create(abbreviation);
        abbreviation = abbreviationController.filter(testAbbreviationName, departmentController.getAllDepartments().get(0).getName()).get(0);

        //Act
        for(int j = 0; j<11; j++) {
            abbreviationController.dislike(abbreviation.getId());
        }


        //assert/reset
        assertEquals(abbreviationController.filter(testAbbreviationName, departmentController.getAllDepartmentNames().get(0)).size(), 0);
    }
}
