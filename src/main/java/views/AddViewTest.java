package views;

import models.Abbreviation;
import org.junit.Test;


import static org.junit.Assert.*;

public class AddViewTest {

    AddView addView = new AddView();


    @Test
    public void getInputTest() {  //ergens las ik dat je geen constructor mocht testen maar in bijvoorbeeld een uitleg video werdt dit wel gedaan en ik test niet alleen de constructor

        Abbreviation abbreviation1 = new Abbreviation();
        Abbreviation abbreviation2 = new Abbreviation();

        abbreviation1.setMeaning("Zo snel mogelijk");
        abbreviation1.setLetters("ZSM");
        abbreviation1.setDepartment("Binnelandse Zaken");

        assertFalse(abbreviation1.getLetters() == null);
        assertTrue(abbreviation1.getLetters() != null);
        assertTrue(abbreviation1.getId() == 0);
        assertFalse(abbreviation1.getId() >= 1);

        assertTrue(abbreviation2.getLetters() == null);
        assertTrue(abbreviation2.getMeaning() == null);
        assertTrue(abbreviation2.getDepartment() == null);
        assertTrue(abbreviation2.getId() == 0);
        assertFalse(abbreviation2.getId() >= 1);


    }

    @Test
    public void expontialgrowTest() {

        int output1 = addView.updateWaitInt(2);
        int output2 = addView.updateWaitInt(10);
        int output3 = addView.updateWaitInt(20);
        int output4 = addView.updateWaitInt(0);

        assertEquals(4, output1);
        assertEquals(1024, output2);
        assertEquals(1048576, output3);
        assertEquals(1, output4);

    }

    @Test
    public void checkInputTest() throws Exception {

        Abbreviation abbreviation1 = new Abbreviation();
        Abbreviation abbreviation2 = new Abbreviation();
        Abbreviation abbreviation3 = new Abbreviation();
        Abbreviation abbreviation4 = new Abbreviation();

        abbreviation1.setDepartment("not empty");
        abbreviation1.setLetters("t");
        abbreviation1.setMeaning("Test");


        abbreviation2.setDepartment("2");
        abbreviation2.setLetters("test");
        abbreviation2.setMeaning("");

        abbreviation3.setDepartment("");
        abbreviation3.setLetters("");
        abbreviation3.setMeaning("");

        abbreviation4.setLetters("letters");
        abbreviation4.setLikes(1);
        abbreviation4.setMeaning("");
        abbreviation4.setDepartment("");

        assertTrue(addView.checkInput(abbreviation1));
        assertFalse(addView.checkInput(abbreviation2));
        assertFalse(addView.checkInput(abbreviation3));
        assertFalse(addView.checkInput(abbreviation4));


    }

}