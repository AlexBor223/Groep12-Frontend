package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import models.Abbreviation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

public class AddViewTest{

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
        abbreviation1.setDepartment("2");
        abbreviation1.setLetters("test");
        abbreviation1.setMeaning("d");
//        boolean t = addView.checkInput(abbreviation1);

//        Abbreviation abbreviation2 = new Abbreviation();
//        abbreviation1.setDepartment("2");
//        abbreviation1.setLetters("test");
//        abbreviation1.setMeaning("");

//        assertTrue(t);
//        assertFalse(addView.checkInput(abbreviation2));
    }

}