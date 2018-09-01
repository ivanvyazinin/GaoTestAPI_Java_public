package test.java.contentCloud;

import io.qameta.allure.Feature;
import main.java.api.contentCloud.ContentItemAPI;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.constructor.Item;
import main.java.entities.contentCloud.constructor.Link;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Lists.getRandomItem;
import static org.testng.Assert.assertEquals;

@Feature("Content Items")
public class ContentItemsConstructorTest extends CommonCloudTest {
    ContentItemSteps contentItemSteps;
    ScreenSteps screenSteps;
    Screen testScreen;
    ContentItem testContentItem;
    ContentItemAPI contentItemsApi;

    @BeforeClass
    public void prepare(){
        contentItemSteps = new ContentItemSteps();
        contentItemsApi = new ContentItemAPI();
        screenSteps = new ScreenSteps();

        testScreen = new Screen(FOLDER_FOR_TESTS);
        screenSteps.getScreenWithBlock(testScreen, new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id));
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentItem = new ContentItem(FOLDER_FOR_TESTS);
    }

    @Test
    public void createValidConstructor(){
        contentItemSteps.getCIWithValidConstructor(testContentItem, testScreen.id);
        contentItemSteps.checkThatBodyHasNotValue("errors");
    }

    @Test
    public void constructorWithTailAndHead(){
        contentItemSteps.createContentItem(testContentItem);
        newResponse = contentItemsApi.validateConstructor(testContentItem.id);
        checkThatBodyHasValue("errors");
    }

    @Test
    public void constructorWithTailAndHeadAndScreen(){
        contentItemSteps.createContentItem(testContentItem);
        contentItemSteps.placeScreenIntoConstructor(testContentItem, testScreen.id);

        newResponse = contentItemsApi.validateConstructor(testContentItem.id);
        checkThatBodyHasValue("errors");
    }

    @Test
    public void addLinkToConstructor(){
        String from="";
        String to="";

        contentItemSteps.createContentItemWithScreen(testContentItem,testScreen);
        contentItemSteps.getContentItemConstructor(testContentItem);

        for (Item item :  contentItemSteps.cc.items){
            switch (item.type){
                case "head": from = item.id; break;
                case "screen": to = item.id; break;
            }
        }

        contentItemSteps.addConstructorLink(testContentItem, from, to);
        contentItemSteps.getContentItemConstructor(testContentItem);
        assertEquals(contentItemSteps.response.jsonPath().getList("data.links").size(), 1);
    }


}