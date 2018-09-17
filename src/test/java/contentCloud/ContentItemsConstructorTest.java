package test.java.contentCloud;

import io.qameta.allure.Feature;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.constructor.Item;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static org.testng.Assert.assertEquals;

@Feature("Content Items")
public class ContentItemsConstructorTest extends SuperTest {
    private CommonSteps steps;
    private ContentItemSteps contentItemSteps;
    private Screen testScreen;
    private ContentItem testContentItem;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
        contentItemSteps = new ContentItemSteps();
        ScreenSteps screenSteps = new ScreenSteps();

        testScreen = new Screen(context.getTestFolder());
        screenSteps.getScreenWithBlock(testScreen, new Paragraph(context.getTestFolder(), context.getLevel()));
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentItem = new ContentItem(context.getTestFolder());
    }

    @Test
    public void createValidConstructor(){
        contentItemSteps.getCIWithValidConstructor(
                new ContentItem(context.getTestFolder()),
                new Screen(context.getTestFolder()),
                new Paragraph(context.getTestFolder(), context.getLevel()));

        contentItemSteps.checkThatBodyHasNotValue("errors");
    }

    @Test
    public void constructorWithTailAndHead(){
        testContentItem = steps.createEntity(testContentItem);
        contentItemSteps.validateConstructor(testContentItem);
        contentItemSteps.checkThatBodyHasValue("errors");
    }

    @Test
    public void constructorWithTailAndHeadAndScreen(){
        testContentItem = steps.createEntity(testContentItem);
        contentItemSteps.placeScreenIntoConstructor(testContentItem, testScreen);

        contentItemSteps.validateConstructor(testContentItem);
        contentItemSteps.checkThatBodyHasValue("errors");
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