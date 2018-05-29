package test.java.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.TagsSteps;

import static main.java.properties.Constants.ROOT_FOLDER;

public class TagsTest extends SuperTest {
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private TagsSteps tagsSteps;

    @BeforeClass
    public void prepareSteps(){
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
        tagsSteps = new TagsSteps();
    }

    @Test()
    @Story("Add tag to Screen")
    public void AddTagToScreen() {
        screenSteps.createScreen(ROOT_FOLDER);
        tagsSteps.addTag("screen", screenSteps.testScreen.id);
        tagsSteps.checkStatusCode(201);
        screenSteps.getScreen();
        screenSteps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromScreen() {
        screenSteps.createScreen(ROOT_FOLDER);
        tagsSteps.addTag("screen", screenSteps.testScreen.id);
        tagsSteps.deleteTag("screen",screenSteps.testScreen.id);
        tagsSteps.checkStatusCode(204);
        screenSteps.getScreen();
        screenSteps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }

    @Test()
    @Story("Add tag to ContentItem")
    public void AddTagToContentItem() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        tagsSteps.addTag("content_item", contentItemSteps.testContentItem.id);
        tagsSteps.checkStatusCode(201);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromContentItem() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        tagsSteps.addTag("content_item", contentItemSteps.testContentItem.id);
        tagsSteps.deleteTag("content_item",contentItemSteps.testContentItem.id);
        tagsSteps.checkStatusCode(204);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }
}
