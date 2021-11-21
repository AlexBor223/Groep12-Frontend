package views;

import models.Abbreviation;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddViewTest {
    AddView addView = new AddView();

    @Test
    public void getInputTest() {

        Abbreviation abbreviation1 = new Abbreviation();
        Abbreviation abbreviation2 = new Abbreviation();

        abbreviation1.setMeaning("Zo snel mogelijk");
        abbreviation1.setLetters("ZSM");
        abbreviation1.setDepartment("Binnelandse Zaken");

        assertFalse(abbreviation1.getLetters() == null);
        assertTrue(abbreviation1.getLetters() != null);
        assertTrue(abbreviation1.getId() == 0 );
        assertFalse(abbreviation1.getId() >= 1 );

        assertTrue(abbreviation2.getLetters() == null);
        assertTrue(abbreviation2.getMeaning() == null);
        assertTrue(abbreviation2.getDepartment() == null);
        assertTrue(abbreviation2.getId() == 0 );
        assertFalse(abbreviation2.getId() >= 1 );


    }

    @Test
    public void checkInputTest(){


    }
}