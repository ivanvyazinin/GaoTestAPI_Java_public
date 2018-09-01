package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.Example;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.*;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_EXAMPLE;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class ExampleTest extends CommonCloudTest {
    private CommonBlocsAPI exampleAPI;
    private Example example;

    @BeforeClass
    public void prepareSteps(){
        exampleAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_EXAMPLE);
    }

    @BeforeMethod
    public void prepareEntity(){
        example  = new Example();
    }

    @Test
    @Story("Create Example")
    @Description("Just create example")
    public void createExample() {
        newResponse = exampleAPI.post(example);
        checkStatusCode(201);
    }

    @Test
    @Story("Create Example")
    @Description("Check that you can create Example with HTML tags, but they are removed")
    public void createExampleWithHTML() {
        String testText = getRandomText(10);
        example.example = "<b>" + testText + "</b>";
        newResponse = exampleAPI.post(example);
        checkStatusCode(201);
        newResponse = exampleAPI.getById(newResponse.jsonPath().getString(PATH_ID));
        checkThatBodyHasNotValue("<b>");
        checkThatBodyHasValue(testText);
    }

    @Test
    @Story("Create Example")
    @Description("Check, that you cannot create empty example")
    public void createEmptyExample() {
        example.example = "";
        newResponse = exampleAPI.post(example);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Example")
    @Description("Check, that you cannot create example with more then 1024 symbols")
    public void createExampleWith16001Symbols() {
        example.example = getRandomText(1025);
        newResponse = exampleAPI.post(example);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test
    public void editExample() {
        newResponse = exampleAPI.post(example);
        example.example = "Changed" + example.example;
        newResponse = exampleAPI.put(newResponse.jsonPath().getString(PATH_ID), example);
        checkStatusCode(200);
        checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteExample() {
        example.id  = exampleAPI.post(example).jsonPath().getString(PATH_ID);
        newResponse = exampleAPI.delete(example.id);
        checkStatusCode(204);
        newResponse = exampleAPI.getById(example.id);
        checkStatusCode(404);
    }
}