package test.java.contentCloud;

import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.TagsSteps;
import test.java.SuperTest;

import static main.java.properties.Constants.EMBED_TAG;

public class TagsTest extends SuperTest {
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
                new Screen(context.getTestFolder()));
        tagsSteps.addTag("screen", screen.id);
        tagsSteps.checkStatusCode(201);
        steps.getEntity(screen);
        steps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromScreen() {
        Screen screen = steps.createEntity(
                new Screen(context.getTestFolder()));
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
                new ContentItem(context.getTestFolder()));
        tagsSteps.addTag("content_item", contentItem.id);
        tagsSteps.checkStatusCode(201);
        steps.getEntity(contentItem);
        steps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void DeleteTagFromContentItem() {
        ContentItem contentItem = steps.createEntity(
                new ContentItem(context.getTestFolder()));
        tagsSteps.addTag("content_item", contentItem.id);
        tagsSteps.deleteTag("content_item",contentItem.id);
        tagsSteps.checkStatusCode(204);
        steps.getEntity(contentItem);
        steps.checkThatBodyHasNotValue(tagsSteps.testTag.name);
    }

    @Test()
    @Story("Add tag to Block")
    public void AddTagToBlock() {
        Paragraph paragraph = steps.createEntity(
                new Paragraph(context.getTestFolder(), context.getLevel()));
        tagsSteps.addTag("block", paragraph.id);
        tagsSteps.checkStatusCode(201);
        steps.getEntity(paragraph);
        steps.checkThatBodyHasValue(tagsSteps.testTagId);
    }

    @Test()
    @Story("Add tag to Content Publication")
    public void AddTagToContentPublication() {
        Paragraph paragraph = steps.createEntity(
                new Paragraph(context.getTestFolder(), context.getLevel()));
        tagsSteps.addTag("block", paragraph.id);
        tagsSteps.checkStatusCode(201);
        steps.getEntity(paragraph);
        steps.checkThatBodyHasValue(tagsSteps.testTagId);
    }
}