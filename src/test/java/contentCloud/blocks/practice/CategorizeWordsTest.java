package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.CategorizeWords;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.properties.Endpoints.ENDPOINT_CATEGORIZE_WORDS;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class CategorizeWordsTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private CategorizeWords categorizeWords;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_CATEGORIZE_WORDS);
    }

    @BeforeMethod
    public void prepareEntity(){
        categorizeWords = new CategorizeWords();
    }

    @Test
    @Story("Create categorizeWords Block")
    @Description("Just create categorizeWords Block")
    public void createCategorizeWords() {
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        newResponse =  practiceBlocksAPI.post(categorizeWords);
        checkStatusCode(201);
    }

    @Test
    @Story("Create categorizeWords Block")
    @Description("Cannot create categorizeWords Block with 1 category")
    public void createCategorizeWordsWithOneCategory() {
        categorizeWords.category.add(new CategorizeWords.Category());
        newResponse = practiceBlocksAPI.post(categorizeWords);
        checkStatusCode(400);
    }

    @Test
    @Story("Create categorizeWords Block")
    @Description("Cannot create categorizeWords Block with 7 category")
    public void createCategorizeWordsWithSevenCategory() {
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        categorizeWords.category.add(new CategorizeWords.Category());
        newResponse = practiceBlocksAPI.post(categorizeWords);
        checkStatusCode(400);
    }

    @Test
    @Story("Create categorizeWords Block")
    @Description("Cannot create categorizeWords Block with 11 items in a category")
    public void createCategorizeWordsWithElevenItems() {
        categorizeWords.category.add(new CategorizeWords.Category());
        for (int i=0;i<11;i++){categorizeWords.category.get(0).items.add(getRandomTextRandomLength(1024));}
        newResponse = practiceBlocksAPI.post(categorizeWords);
        checkStatusCode(400);
    }

}
