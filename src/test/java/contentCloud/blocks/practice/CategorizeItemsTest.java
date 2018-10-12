package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.CategorizeItems;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Content Cloud")
@Feature("Editor adds Practice blocks to the screen")
@Story("Editor adds CategorizeItems block")
public class CategorizeItemsTest extends SuperTest {
    private CategorizeItems categorizeItems;
    private CommonSteps steps;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        categorizeItems = new CategorizeItems();
    }

    @Test
    @Description("Just create categorizeItems Block")
    public void createCategorizeWords() {
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        steps.createEntity(categorizeItems);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Cannot create categorizeItems Block with 1 category")
    public void createCategorizeWordsWithOneCategory() {
        categorizeItems.category.add(new CategorizeItems.Category());
        steps.createEntity(categorizeItems);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create categorizeItems Block with 7 category")
    public void createCategorizeWordsWithSevenCategory() {
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        categorizeItems.category.add(new CategorizeItems.Category());
        steps.createEntity(categorizeItems);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create categorizeItems Block with 11 items in a category")
    public void createCategorizeWordsWithElevenItems() {
        categorizeItems.category.add(new CategorizeItems.Category());
        for (int i=0;i<11;i++){
            categorizeItems.category.get(0).items.add(getRandomTextRandomLength(1024));}
        steps.createEntity(categorizeItems);
        steps.checkStatusCode(400);
    }

}
