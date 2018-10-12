package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Content Cloud")
@Feature("Editor adds Theory blocks to the screen")
@Story("Editor adds Paragraph block")
public class ParagraphTest extends SuperTest {
    private CommonSteps steps;
    private Paragraph testParagraph;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testParagraph = new Paragraph();
        testParagraph.paragraph = getRandomTextRandomLength(16000);
    }

    @Test
    @Description("Just create paragraph")
    public void createParagraph() {
        steps.createEntity(testParagraph);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Check that you can create Paragraph with HTML tags, but they are removed")
    public void createParagraphWithHTML() {
        String testText = getRandomText(10);

        testParagraph.paragraph = "<b>" + testText + "</b>";
        testParagraph = steps.createEntity(testParagraph);
        steps.checkStatusCode(201);
        steps.getEntity(testParagraph);
        steps.checkThatBodyHasNotValue("<b>");
        steps.checkThatBodyHasValue(testText);
    }

    @Test
    @Description("Check, that you cannot create empty paragraph")
    public void createEmptyParagraph() {
        testParagraph.paragraph = "";
        steps.createEntity(testParagraph);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Description("Check, that you cannot create paragraph with more then 16000 symbols")
    public void createParagraphWith16001Symbols() {
        testParagraph.paragraph = getRandomText(16001);
        steps.createEntity(testParagraph);
        steps.checkStatusCode(400);
    }

    @Test
    public void editParagraph() {
        testParagraph = new Paragraph(context.getTestFolder(), context.getLevel());
        testParagraph = steps.createEntity(testParagraph);
        testParagraph.paragraph = "Changed" + testParagraph.paragraph;
        steps.editEntity(testParagraph);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed");
    }

    @Test
    @Description("Move block, that inside of some screen")
    public void moveParagraph() {
        Folder destinationFolder = steps.createEntity(
                new Folder(commonObjects.getTestFolder().id)
        );
        ScreenSteps screenSteps = new ScreenSteps();
        Screen testScreen = screenSteps.getScreenWithBlock(
                new Screen(commonObjects.getTestFolder().id), testParagraph
        );
        testParagraph.id = testScreen.blocks.get(0).id;

        testParagraph.setParentFolder(destinationFolder);
        steps.editEntity(testParagraph);
        steps.checkStatusCode(200);
        steps.api.setRequestParameters(new String[][] {
                {"embed", "block"}
        });

        steps.getEntity(testScreen);
        steps.checkThatBodyHasValue(destinationFolder.id);
    }

    @Test
    public void deleteParagraph() {
        testParagraph = steps.createEntity(testParagraph);
        steps.deleteEntity(testParagraph);
        steps.checkStatusCode(204);
        steps.getEntity(testParagraph);
        steps.checkStatusCode(404);
    }
}