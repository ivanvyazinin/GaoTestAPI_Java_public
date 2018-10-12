package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Example;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Content Cloud")
@Feature("Editor adds Theory blocks to the screen")
@Story("Editor adds Example block")
public class ExampleTest extends SuperTest {
    private CommonSteps steps;
    private Example example;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        example  = new Example();
        example.example = getRandomTextField("Example");
    }

    @Test
    @Description("Just create example")
    public void createExample() {
        steps.createEntity(example);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Check that you can create Example with HTML tags, but they are removed")
    public void createExampleWithHTML() {
        String testText = getRandomText(10);
        example.example = "<b>" + testText + "</b>";
        example = steps.createEntity(example);
        steps.checkStatusCode(201);
        steps.getEntity(example);
        steps.checkThatBodyHasNotValue("<b>");
        steps.checkThatBodyHasValue(testText);
    }

    @Test
    @Description("Check, that you cannot create empty example")
    public void createEmptyExample() {
        example.example = "";
        steps.createEntity(example);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Description("Check, that you cannot create example with more then 1024 symbols")
    public void createExampleWith16001Symbols() {
        example.example = getRandomText(1025);
        steps.createEntity(example);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test (enabled = false)
    public void editExample() {
        example = steps.createEntity(example);
        example.example = "Changed" + example.example;
        steps.editEntity(example);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteExample() {
        example = steps.createEntity(example);
        steps.deleteEntity(example);
        steps.checkStatusCode(204);
        steps.getEntity(example);
        steps.checkStatusCode(404);
    }
}