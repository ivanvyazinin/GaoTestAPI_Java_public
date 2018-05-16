package test.java.api.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.ContentItemSteps;
import test.java.steps.ScreenSteps;
import test.java.steps.TagsSteps;

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
        tagsSteps.addTag("screen", screenSteps.testScreenId);
        tagsSteps.checkStatusCode(201);
        screenSteps.getScreen();
        screenSteps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromScreen() {
        screenSteps.createScreen(ROOT_FOLDER);
        tagsSteps.addTag("screen", screenSteps.testScreenId);
        tagsSteps.deleteTag("screen",screenSteps.testScreenId);
        tagsSteps.checkStatusCode(204);
        screenSteps.getScreen();
        screenSteps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }

    @Test()
    @Story("Add tag to ContentItem")
    public void AddTagToContentItem() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        tagsSteps.addTag("content_item", contentItemSteps.testContentItemId);
        tagsSteps.checkStatusCode(201);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromContentItem() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        tagsSteps.addTag("content_item", contentItemSteps.testContentItemId);
        tagsSteps.deleteTag("content_item",contentItemSteps.testContentItemId);
        tagsSteps.checkStatusCode(204);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }
}
