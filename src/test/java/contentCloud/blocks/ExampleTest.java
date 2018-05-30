package test.java.contentCloud.blocks;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.ExampleSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.ERROR_IS_BLANK;
import static main.java.properties.Constants.ERROR_TOO_LONG;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class ExampleTest extends SuperTest {
    private ScreenSteps screenSteps;
    private ExampleSteps exampleSteps;

    @BeforeClass
    public void prepareSteps(){
        exampleSteps = new ExampleSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    @Story("Create Example")
    @Description("Just create example")
    public void createExample() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        exampleSteps.createExample(getRandomText(1024), screenSteps.testScreen.id);
        exampleSteps.checkStatusCode(201);
    }

    @Test
    @Story("Create Example")
    @Description("Check that you can create Example with HTML tags, but they are removed")
    public void createExampleWithHTML() {
        String testText = getRandomText(10);

        screenSteps.createScreen(FOLDER_FOR_TESTS);
        exampleSteps.createExample("<b>" + testText + "</b>", screenSteps.testScreen.id);
        exampleSteps.checkStatusCode(201);
        exampleSteps.getExample(exampleSteps.testExample.id);
        exampleSteps.checkThatBodyHasNotValue("<b>");
        exampleSteps.checkThatBodyHasValue(testText);
    }

    @Test
    @Story("Create Example")
    @Description("Check, that you cannot create empty example")
    public void createEmptyExample() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        exampleSteps.createExample("", screenSteps.testScreen.id);
        exampleSteps.checkStatusCode(400);
        exampleSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Example")
    @Description("Check, that you cannot create example with more then 1024 symbols")
    public void createExampleWith16001Symbols() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        exampleSteps.createExample(getRandomText(1025), screenSteps.testScreen.id);
        exampleSteps.checkStatusCode(400);
        exampleSteps.checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test
    public void editExample() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        exampleSteps.createExample(getRandomText(123), screenSteps.testScreen.id);
        exampleSteps.testExample.example = "Changed" + exampleSteps.testExample.example;
        exampleSteps.editExample(exampleSteps.testExample.id);
        exampleSteps.checkStatusCode(201);
        exampleSteps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteExample() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        exampleSteps.createExample(getRandomText(123), screenSteps.testScreen.id);
        exampleSteps.deleteExample(exampleSteps.testExample.id);
        exampleSteps.checkStatusCode(204);
        exampleSteps.getExample(exampleSteps.testExample.id);
        exampleSteps.checkStatusCode(404);
    }
}