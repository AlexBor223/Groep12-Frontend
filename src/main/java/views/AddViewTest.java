package views;

import models.Abbreviation;

import static org.junit.Assert.*;

import org.junit.Test;


public class AddViewTest {

    AddView addView = new AddView();


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
    public void filledInInfoTest() {

        assertTrue(addView.filledInInfo("Wer", "Wat is er", 1));
        assertFalse(addView.filledInInfo("", "Wat is er", 2));
        assertFalse(addView.filledInInfo("Wer", "", 3));
        assertFalse(addView.filledInInfo("", "", 1));
        assertFalse(addView.filledInInfo("ww", "wachtwoord", -1));


    }


    @Test
    public void getInputTest() {  //ergens las ik dat je geen constructor mocht testen maar in bijvoorbeeld een uitleg video werdt dit wel gedaan en ik test niet alleen de constructor

        Abbreviation abbreviation1 = new Abbreviation();
        Abbreviation abbreviation2 = new Abbreviation();

        abbreviation1.setMeaning("Zo snel mogelijk");
        abbreviation1.setLetters("ZSM");
        abbreviation1.setDepartmentId(1);

        assertNotNull(abbreviation1.getLetters());
        assertNotNull(abbreviation1.getLetters());
        assertEquals(0, abbreviation1.getId());
        assertFalse(abbreviation1.getId() >= 1);

        assertNull(abbreviation2.getLetters());
        assertNull(abbreviation2.getMeaning());
        assertEquals(0, abbreviation2.getId());
        assertFalse(abbreviation2.getId() >= 1);


    }

}