package test.java.directories;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.directories.Zone;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.ERROR_IS_BLANK;
import static main.java.properties.Constants.ERROR_TOO_LONG;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Editor works with directories")
@Feature("CRUD for Directories")
@Story("CRUD for FunctionalZones Directory")
public class FunctionalZonesTest extends SuperTest {
    private CommonSteps steps;
    private Zone testZone;


    @BeforeClass
    void prepare(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testZone = new Zone();
        testZone.name = getRandomTextField("Skill");
    }

    @Test
    @Story("Editors adds a record to an one-level directory")
    public void createZone(){
        steps.createEntity(testZone);
        steps.checkStatusCode(201);
    }

    @Test
    public void createZoneWithBlankName(){
        testZone.name = "";
        steps.createEntity(testZone);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    public void createZoneTooLongName(){
        testZone.name = getRandomText(161);
        steps.createEntity(testZone);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createZoneWithSameName(){
        steps.createEntity(testZone);
        steps.createEntity(testZone);
        steps.checkStatusCode(400);
    }

    @Test
    public void editZone(){
        testZone = steps.createEntity(testZone);
        testZone.name = getRandomTextField("Changed ZoneAuto");
        steps.editEntity(testZone);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed ZoneAuto");
    }

    @Test
    public void deleteZone(){
        testZone = steps.createEntity(testZone);
        steps.deleteEntity(testZone);
        steps.checkStatusCode(204);
        steps.getEntity(testZone);
        steps.checkStatusCode(404);
    }
}