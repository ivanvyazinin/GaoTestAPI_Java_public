package test.java.contentCloud;

import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.TagsSteps;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.FOLDER_FOR_TESTS;

public class TagsTest extends CommonCloudTest {
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
        Screen screen = screenSteps.createScreen(new Screen(FOLDER_FOR_TESTS));
        tagsSteps.addTag("screen", screen.id);
        tagsSteps.checkStatusCode(201);
        screenSteps.getScreen(screen);
        screenSteps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromScreen() {
        Screen screen = screenSteps.createScreen(new Screen(FOLDER_FOR_TESTS));
        tagsSteps.addTag("screen", screen.id);
        tagsSteps.deleteTag("screen",screen.id);
        tagsSteps.checkStatusCode(204);
        screenSteps.getScreen(screen);
        screenSteps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }

    @Test()
    @Story("Add tag to ContentItem")
    public void AddTagToContentItem() {
        ContentItem contentItem = contentItemSteps.createContentItem(new ContentItem(FOLDER_FOR_TESTS));
        tagsSteps.addTag("content_item", contentItem.id);
        tagsSteps.checkStatusCode(201);
        contentItemSteps.getContentItem(contentItem);
        contentItemSteps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromContentItem() {
        ContentItem contentItem = contentItemSteps.createContentItem(new ContentItem(FOLDER_FOR_TESTS));
        tagsSteps.addTag("content_item", contentItem.id);
        tagsSteps.deleteTag("content_item",contentItem.id);
        tagsSteps.checkStatusCode(204);
        contentItemSteps.getContentItem(contentItem);
        contentItemSteps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }
}