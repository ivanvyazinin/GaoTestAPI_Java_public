package test.java.contentCloud.blocks;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.ParagraphSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.ERROR_IS_BLANK;
import static main.java.properties.Constants.ERROR_TOO_LONG;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class ParagraphTest extends CommonCloudTest {
    private ScreenSteps screenSteps;
    private ParagraphSteps paragraphSteps;

    @BeforeClass
    public void prepareSteps(){
        paragraphSteps = new ParagraphSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    @Story("Create Paragraph")
    @Description("Just create paragraph")
    public void createParagraph() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        paragraphSteps.createParagraph(getRandomText(16000), screenSteps.testScreen.id);
        paragraphSteps.checkStatusCode(201);
    }

    @Test
    @Story("Create Paragraph")
    @Description("Check that you can create Paragraph with HTML tags, but they are removed")
    public void createParagraphWithHTML() {
        String testText = getRandomText(10);

        screenSteps.createScreen(FOLDER_FOR_TESTS);
        paragraphSteps.createParagraph("<b>" + testText + "</b>", screenSteps.testScreen.id);
        paragraphSteps.checkStatusCode(201);
        paragraphSteps.getParagraph(paragraphSteps.testParagraph.id);
        paragraphSteps.checkThatBodyHasNotValue("<b>");
        paragraphSteps.checkThatBodyHasValue(testText);
    }

    @Test
    @Story("Create Paragraph")
    @Description("Check, that you cannot create empty paragraph")
    public void createEmptyParagraph() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        paragraphSteps.createParagraph("", screenSteps.testScreen.id);
        paragraphSteps.checkStatusCode(400);
        paragraphSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Paragraph")
    @Description("Check, that you cannot create paragraph with more then 16000 symbols")
    public void createParagraphWith16001Symbols() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        paragraphSteps.createParagraph(getRandomText(16001), screenSteps.testScreen.id);
        paragraphSteps.checkStatusCode(400);
        paragraphSteps.checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test
    public void editParagraph() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        paragraphSteps.createParagraph(getRandomText(123), screenSteps.testScreen.id);
        paragraphSteps.testParagraph.paragraph = "Changed" + paragraphSteps.testParagraph.paragraph;
        paragraphSteps.editParagraph(paragraphSteps.testParagraph.id);
        paragraphSteps.checkStatusCode(200);
        paragraphSteps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteParagraph() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        paragraphSteps.createParagraph(getRandomText(123), screenSteps.testScreen.id);
        paragraphSteps.deleteParagraph(paragraphSteps.testParagraph.id);
        paragraphSteps.checkStatusCode(204);
        paragraphSteps.getParagraph(paragraphSteps.testParagraph.id);
        paragraphSteps.checkStatusCode(404);
    }
}