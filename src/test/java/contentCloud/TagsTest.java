package test.java.contentCloud;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.Tag;
import main.java.entities.contentCloud.blocks.GroupOfBlocks;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.publications.ContentPublication;
import main.java.entities.publications.MediaPublication;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.EMBED_TAG;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Content Cloud")
@Feature("Editor adds tags to stuff")
public class TagsTest extends SuperTest {
    private CommonSteps steps;
    private Tag testTag;

    @BeforeClass
    public void prepareSteps(){
        testTag = new Tag(getRandomTextField("TAtag"));

        steps = new CommonSteps();
    }

    @BeforeMethod
    public void setParams(){
        steps.api.setRequestParameters(new String[][]{
                {"embed",EMBED_TAG}
        });
    }

    @Test()
    @Story("Add tag to Screen")
    public void addTagToScreen() {
        Screen screen = steps.createEntity(
                new Screen(context.getTestFolder()));
        steps.addTag(screen, "screen", testTag);
        steps.checkStatusCode(201);
        steps.getEntity(screen);
        steps.checkThatBodyHasValue(testTag.name);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void deleteTagFromScreen() {
        Screen screen = steps.createEntity(
                new Screen(context.getTestFolder()));
        testTag = steps.addTag(screen, "screen", testTag);
        steps.deleteTag(screen,"screen", testTag);
        steps.checkStatusCode(204);
        steps.getEntity(screen);
        steps.checkThatBodyHasNotValue(testTag.name);
    }

    @Test()
    @Story("Add tag to ContentItem")
    public void addTagToContentItem() {
        ContentItem contentItem = steps.createEntity(
                new ContentItem(context.getTestFolder()));
        steps.addTag(contentItem, "content_item", testTag);
        steps.checkStatusCode(201);
        steps.getEntity(contentItem);
        steps.checkThatBodyHasValue(testTag.name);
    }

    @Test()
    @Story("Delete tag from Screen")
    public void deleteTagFromContentItem() {
        ContentItem contentItem = steps.createEntity(
                new ContentItem(context.getTestFolder()));
        testTag = steps.addTag(contentItem, "content_item", testTag);
        steps.deleteTag(contentItem,"content_item", testTag);
        steps.checkStatusCode(204);
        steps.getEntity(contentItem);
        steps.checkThatBodyHasNotValue(testTag.name);
    }

    @Test()
    @Story("Add tag to Block")
    public void AddTagToBlock() {
        Paragraph paragraph = steps.createEntity(
                new Paragraph(context.getTestFolder(), context.getLevel()));
        steps.addTag(paragraph, "block", testTag);
        steps.checkStatusCode(201);
        steps.getEntity(paragraph);
        steps.checkThatBodyHasValue(testTag.name);
    }

    @Test
    @Story("Add tag to Content Publication")
    public void AddTagToContentPublication() {
        ContentPublication contentPublication = steps.createEntity(
                new ContentPublication(context).addCi(commonObjects.getValidCI())
        );
        steps.addTag(contentPublication, "content_publication", testTag);
        steps.checkStatusCode(201);
        steps.getEntity(contentPublication);
        steps.checkThatBodyHasValue(testTag.name);
    }

    @Test
    @Story("Add tag to Media Publication")
    public void AddTagToMediaPublication() {
        MediaPublication mediaPublication = steps.createEntity(
          new MediaPublication(context)
        );
        steps.addTag(mediaPublication, "media_publication", testTag);
        steps.checkStatusCode(201);
        steps.getEntity(mediaPublication);
        steps.checkThatBodyHasValue(testTag.name);
    }

    @Test
    @Story("Add tag to Group of blocks")
    public void AddTagToGroupOfBlocks() {
        steps.api.setRequestParameters(new String[][]{
                {"embed","group_of_blocks_tag"}
        });

        GroupOfBlocks groupOfBlocks = steps.createEntity(
          new GroupOfBlocks(context).setCI(
                  commonObjects.getValidCI()
          )
        );
        steps.addTag(groupOfBlocks, "group_of_blocks", testTag);
        steps.checkStatusCode(201);
        steps.getEntity(groupOfBlocks);
        steps.checkThatBodyHasValue(testTag.name);
    }
}