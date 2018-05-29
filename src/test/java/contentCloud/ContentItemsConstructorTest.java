package test.java.contentCloud;

import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Context.FOLDER_FOR_TESTS;

public class ContentItemsConstructorTest extends SuperTest {
    ContentItemSteps contentItemSteps;
    ScreenSteps screenSteps;

    @BeforeClass
    public void prepare(){
        contentItemSteps = new ContentItemSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    public void addLinkToConstructor(){
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        contentItemSteps.createContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.placeScreenIntoConstructor(screenSteps.testScreen.id);
        contentItemSteps.getContentItemConstructor(contentItemSteps.testContentItem.id);
        contentItemSteps.addConstructorLink(
                contentItemSteps.cc.items.get(0).id,
                contentItemSteps.cc.items.get(2).id
        );
        contentItemSteps.getContentItemConstructor(contentItemSteps.testContentItem.id);
        contentItemSteps.checkThatJsonContains(contentItemSteps.cc.items.get(0).id,
                "data.links[0].from");
    }
}
