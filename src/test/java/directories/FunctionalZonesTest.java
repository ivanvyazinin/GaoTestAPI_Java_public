package test.java.directories;

import main.java.steps.directories.ZonesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.ERROR_IS_BLANK;
import static main.java.properties.Constants.ERROR_TOO_LONG;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

public class FunctionalZonesTest extends SuperTest {
    ZonesSteps zonesSteps;

    @BeforeClass
    void prepare(){
        zonesSteps = new ZonesSteps();
    }

    @Test
    public void createZone(){
        zonesSteps.createZone();
        zonesSteps.checkStatusCode(201);
    }

    @Test
    public void createZoneWithBlankName(){
        zonesSteps.createZone("");
        zonesSteps.checkStatusCode(400);
        zonesSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    public void createZoneTooLongName(){
        zonesSteps.createZone(getRandomText(161));
        zonesSteps.checkStatusCode(400);
        zonesSteps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createZoneWithSameName(){
        zonesSteps.createZone();
        zonesSteps.createZone(zonesSteps.testZone.name);
        zonesSteps.checkStatusCode(400);
        //TODO check error
    }

    @Test
    public void editZone(){
        zonesSteps.createZone();
        zonesSteps.testZone.name = getRandomTextField("Changed ZoneAuto");
        zonesSteps.editZone(zonesSteps.testZone.id);
        zonesSteps.checkStatusCode(200);
        zonesSteps.checkThatBodyHasValue("Changed ZoneAuto");
    }

    @Test
    public void deleteZone(){
        zonesSteps.createZone();
        zonesSteps.deleteZone(zonesSteps.testZone.id);
        zonesSteps.checkStatusCode(204);
        zonesSteps.getZone(zonesSteps.testZone.id);
        zonesSteps.checkStatusCode(404);
    }

}
