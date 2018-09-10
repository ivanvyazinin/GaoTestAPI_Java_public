package test.java.directories;

import main.java.entities.directories.Isco;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomIscoCode;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

public class IscoTest extends SuperTest {
    private CommonSteps steps;
    private Isco testIsco;

    @BeforeClass
    void prepare(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testIsco = new Isco();
        testIsco.name = getRandomTextField("Isco");
        testIsco.code = getRandomIscoCode(); }

    @Test
    public void createIsco(){
        steps.createEntity(testIsco);
        steps.checkStatusCode(201);
    }

    @Test
    public void createIscoWithBlankName(){
        testIsco.name = "";
        steps.createEntity(testIsco);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    public void createIscoTooLongName(){
        testIsco.name = getRandomText(161);
        steps.createEntity(testIsco);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createIsco3SymbolCode(){
        testIsco.code = "333";
        steps.createEntity(testIsco);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_ISCO_NOT_VALID, PATH_ERROR);
    }

    @Test
    public void createIsco5SymbolCode(){
        testIsco.code = "33333";
        steps.createEntity(testIsco);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createIscoWithSameName(){
        steps.createEntity(testIsco);
        testIsco.name = getRandomTextField("Isco");
        steps.createEntity(testIsco);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test
    public void createIscoWithSameCode(){
        steps.createEntity(testIsco);
        testIsco.code = getRandomIscoCode();
        steps.createEntity(testIsco);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test
    public void editIsco(){
        testIsco = steps.createEntity(testIsco);
        testIsco.name = getRandomTextField("Changed IscoAuto");
        testIsco.code = getRandomIscoCode();
        steps.editEntity(testIsco);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed IscoAuto");
    }

    @Test
    public void deleteIsco(){
        testIsco = steps.createEntity(testIsco);
        steps.deleteEntity(testIsco);
        steps.checkStatusCode(204);
        steps.getEntity(testIsco);
        steps.checkStatusCode(404);
    }

}
