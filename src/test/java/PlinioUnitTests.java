import application.Main;
import com.google.gson.Gson;
import dao.AbbreviationDao;
import dao.DepartmentDao;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import models.Abbreviation;
import models.NodeSearcher;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlinioUnitTests {
    // Method to initialize toolkit
    private void startInitializeThread() {
        Thread initThread = new Thread("JavaFX Init Thread") {
            public void run() {
                Main.main(new String[0]);
            }
        };

        initThread.setDaemon(true);
        initThread.start();
    }

    // Method from dao.AbbreviationDao
    private Abbreviation jsonToAbbreviation(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Abbreviation.class);
    }

    @Test
    public void should_returnArrayListWithOneButton_when_cssClassNameMatches() {
        // Arrange
        NodeSearcher nodeSearcher = new NodeSearcher();
        String className = "pseudoClassName";
        startInitializeThread();

        AnchorPane pseudoPane = new AnchorPane();
        Button pseudoButton = new Button("Unit test button");
        pseudoButton.getStyleClass().add(className);
        pseudoPane.getChildren().add(pseudoButton);

        // Act
        ArrayList<Button> buttonArrayList = nodeSearcher.getButtonsFromClassName(pseudoPane, className);

        // Assert
        assertEquals(buttonArrayList.size(), 1);
    }

    @Test
    public void should_returnNull_when_abbreviationIdCannotBeFound() {
        // Arrange
        long impossibleId = -1;
        AbbreviationDao abbreviationDao = new AbbreviationDao();

        // Act
        Abbreviation abbreviation = abbreviationDao.getAbbreviationById(impossibleId);

        // Assert
        assertNull(String.valueOf(abbreviation), null);
    }

    @Test
    public void should_returnMinusOne_when_departmentNameDoesNotExist() {
        // Arrange
        String pseudoDepartmentName = "Qwerty Affairs";
        DepartmentDao departmentDao = new DepartmentDao();

        // Act
        long departmentId = departmentDao.getDepartmentIdByName(pseudoDepartmentName);

        // Assert
        assertEquals(departmentId, -1);
    }

    @Test
    public void should_returnFalse_whenAbbreviationsAreUnequal() {
        // Arrange
        Abbreviation abbreviation1 = new Abbreviation(
                0, 0, "DPC", "Dienst Publiek en Communicatie", 5);
        Abbreviation abbreviation2 = new Abbreviation(
                0, 0, "DPC", "Dienst Publiek Communicatie", 5); // Meaning without "en"

        // Act
        boolean areEqual = abbreviation1.equals(abbreviation2);

        // Assert
        assertFalse(areEqual);
    }

    @Test
    public void should_returnIdenticalAbbreviation_when_convertingJsonStringToAbbreviation() {
        // Arrange
        Abbreviation abbreviation = new Abbreviation(
                0, 1, "ETC", "Et cetera", 2);
        String jsonString = "{\"id\":0,\"letters\":\"ETC\",\"meaning\":\"Et cetera\",\"likes\":2,\"departmentId\":1}";

        // Act
        Abbreviation convertedAbbreviation = jsonToAbbreviation(jsonString);
        boolean areEqual = abbreviation.equals(convertedAbbreviation);

        // Assert
        assertTrue(areEqual);
    }
}