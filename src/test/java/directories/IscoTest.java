package test.java.directories;

import main.java.steps.directories.IscoSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomIscoCode;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

public class IscoTest extends SuperTest {
    IscoSteps iscosSteps;

    @BeforeClass
    void prepare(){
        iscosSteps = new IscoSteps();
    }

    @Test
    public void createIsco(){
        iscosSteps.createIsco();
        iscosSteps.checkStatusCode(201);
    }

    @Test
    public void createIscoWithBlankName(){
        iscosSteps.createIsco("",getRandomIscoCode());
        iscosSteps.checkStatusCode(400);
        iscosSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    public void createIscoTooLongName(){
        iscosSteps.createIsco(getRandomText(161),getRandomIscoCode());
        iscosSteps.checkStatusCode(400);
        iscosSteps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createIsco3SymbolCode(){
        iscosSteps.createIsco(getRandomTextField("Auto isco"),"333");
        iscosSteps.checkStatusCode(400);
        iscosSteps.checkThatJsonContains(ERROR_ISCO_NOT_VALID, PATH_ERROR);
    }

    @Test
    public void createIsco5SymbolCode(){
        iscosSteps.createIsco(getRandomTextField("Auto isco") , "33333");
        iscosSteps.checkStatusCode(400);
        iscosSteps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createIscoWithSameName(){
        iscosSteps.createIsco();
        iscosSteps.createIsco(iscosSteps.testIsco.name, getRandomIscoCode());
        iscosSteps.checkStatusCode(400);
        iscosSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test
    public void createIscoWithSameCode(){
        iscosSteps.createIsco();
        iscosSteps.createIsco(getRandomTextField("isco"), iscosSteps.testIsco.code);
        iscosSteps.checkStatusCode(400);
        iscosSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test
    public void editIsco(){
        iscosSteps.createIsco();
        iscosSteps.testIsco.name = getRandomTextField("Changed IscoAuto");
        iscosSteps.testIsco.code = getRandomIscoCode();
        iscosSteps.editIsco(iscosSteps.testIsco.id);
        iscosSteps.checkStatusCode(200);
        iscosSteps.checkThatBodyHasValue("Changed IscoAuto");
    }

    @Test
    public void deleteIsco(){
        iscosSteps.createIsco();
        iscosSteps.deleteIsco(iscosSteps.testIsco.id);
        iscosSteps.checkStatusCode(204);
        iscosSteps.getIsco(iscosSteps.testIsco.id);
        iscosSteps.checkStatusCode(404);
    }

}
