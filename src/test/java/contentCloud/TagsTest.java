package test.java.contentCloud;

import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.TagsSteps;

import java.util.HashMap;

import static main.java.properties.Constants.EMBED_DIRECTORY;
import static main.java.properties.Constants.EMBED_FILE;
import static main.java.properties.Constants.EMBED_TAG;
import static main.java.properties.Context.FOLDER_FOR_TESTS;

public class TagsTest extends CommonCloudTest {
    private TagsSteps tagsSteps;
    private CommonSteps steps;

    @BeforeClass
    public void prepareSteps(){
        tagsSteps = new TagsSteps();

        steps = new CommonSteps();


        String [][] parameters = {
                {"embed",EMBED_TAG}
        };
        steps.api.setRequestParameters(parameters);
    }

    @Test()
    @Story("Add tag to Screen")
    public void AddTagToScreen() {
        Screen screen = steps.createEntity(
                new Screen(FOLDER_FOR_TESTS));
        tagsSteps.addTag("screen", screen.id);
        tagsSteps.checkStatusCode(201);
        steps.getEntity(screen);
        steps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromScreen() {
        Screen screen = steps.createEntity(
                new Screen(FOLDER_FOR_TESTS));
        tagsSteps.addTag("screen", screen.id);
        tagsSteps.deleteTag("screen",screen.id);
        tagsSteps.checkStatusCode(204);
        steps.getEntity(screen);
        steps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }

    @Test()
    @Story("Add tag to ContentItem")
    public void AddTagToContentItem() {
        ContentItem contentItem = steps.createEntity(
                new ContentItem(FOLDER_FOR_TESTS));
        tagsSteps.addTag("content_item", contentItem.id);
        tagsSteps.checkStatusCode(201);
        steps.getEntity(contentItem);
        steps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromContentItem() {
        ContentItem contentItem = steps.createEntity(
                new ContentItem(FOLDER_FOR_TESTS));
        tagsSteps.addTag("content_item", contentItem.id);
        tagsSteps.deleteTag("content_item",contentItem.id);
        tagsSteps.checkStatusCode(204);
        steps.getEntity(contentItem);
        steps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }
}