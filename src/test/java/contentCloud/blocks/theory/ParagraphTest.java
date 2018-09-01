package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.*;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_PARAGRAPH;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class ParagraphTest extends CommonCloudTest {
    private CommonBlocsAPI paragraphAPI;
    private Paragraph paragraph;

    @BeforeClass
    public void prepareSteps(){
        paragraphAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_PARAGRAPH);
    }

    @BeforeMethod
    public void prepareEntity(){
        paragraph  = new Paragraph();
    }

    @Test
    @Story("Create Paragraph")
    @Description("Just create paragraph")
    public void createParagraph() {
        paragraph.paragraph = getRandomText(16000);
        newResponse = paragraphAPI.post(paragraph);
        checkStatusCode(201);
    }

    @Test
    @Story("Create Paragraph")
    @Description("Check that you can create Paragraph with HTML tags, but they are removed")
    public void createParagraphWithHTML() {
        String testText = getRandomText(10);

        paragraph.paragraph = "<b>" + testText + "</b>";
        newResponse = paragraphAPI.post(paragraph);
        checkStatusCode(201);
        newResponse = paragraphAPI.getById(newResponse.jsonPath().getString(PATH_ID));
        checkThatBodyHasNotValue("<b>");
        checkThatBodyHasValue(testText);
    }

    @Test
    @Story("Create Paragraph")
    @Description("Check, that you cannot create empty paragraph")
    public void createEmptyParagraph() {
        paragraph.paragraph = "";
        newResponse = paragraphAPI.post(paragraph);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Paragraph")
    @Description("Check, that you cannot create paragraph with more then 16000 symbols")
    public void createParagraphWith16001Symbols() {
        paragraph.paragraph = getRandomText(16001);
        newResponse = paragraphAPI.post(paragraph);
        checkStatusCode(400);
    }

    @Test
    public void editParagraph() {
        newResponse = paragraphAPI.post(paragraph);
        paragraph.paragraph = "Changed" + paragraph.paragraph;
        newResponse = paragraphAPI.put(newResponse.jsonPath().getString(PATH_ID), paragraph);
        checkStatusCode(200);
        checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteParagraph() {
        paragraph.id  = paragraphAPI.post(paragraph).jsonPath().getString(PATH_ID);
        newResponse = paragraphAPI.delete(paragraph.id);
        checkStatusCode(204);
        newResponse = paragraphAPI.getById(paragraph.id);
        checkStatusCode(404);
    }
}