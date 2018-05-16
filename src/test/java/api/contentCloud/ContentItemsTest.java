package test.java.api.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.ContentItemSteps;
import test.java.steps.FolderSteps;

import static main.java.utils.Generator.getRandomTextField;
import static main.java.properties.Constants.ROOT_FOLDER;

public class ContentItemsTest extends SuperTest {
    private FolderSteps folderSteps;
    private ContentItemSteps contentItemSteps;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        contentItemSteps = new ContentItemSteps();
    }

    @Test
    @Story("Create Content Item")
    public void createContentItemInDaRoot() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        contentItemSteps.checkStatusCode(201);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue("tail");
        contentItemSteps.checkThatBodyHasValue("head");
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Create Content Item")
    public void createContentItemInDaFolder() {
        folderSteps.createFolder(ROOT_FOLDER);
        contentItemSteps.createContentItem(folderSteps.testFolderId);
        contentItemSteps.checkStatusCode(201);

        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(folderSteps.testFolderId);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Create Content Item")
    public void createContentItemWithoutDescription() {
        contentItemSteps.createContentItem(getRandomTextField("name"),null,ROOT_FOLDER);
        contentItemSteps.checkStatusCode(201);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Get Content Items")
    public void getAllContentItems() {
        contentItemSteps.getAllContentItems();
        contentItemSteps.checkStatusCode(200);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Edit Content Item")
    public void EditContentItem() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        contentItemSteps.testContentItem.name = "Changed" + contentItemSteps.testContentItem.name;
        contentItemSteps.testContentItem.description = "Changed" + contentItemSteps.testContentItem.description;
        contentItemSteps.editContentItem();
        contentItemSteps.checkStatusCode(200);
        contentItemSteps.checkThatBodyHasValue(contentItemSteps.testContentItem.description);
        contentItemSteps.checkThatBodyHasValue(contentItemSteps.testContentItem.name);
    }

/*
    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Add tag to Content Item")
    public void AddTagToContentItem() {
        ContentItem contentItemInDaRoot = steps.createContentItem(ROOT_FOLDER);

        Response response = contentItemInDaRoot.addTag(getRandomTextField("Tag"));
        checkStatusCode(201, response.statusCode());

        Response getItem = contentItemInDaRoot.getItem();
        checkThatBodyHasValue(getItem.asString(),contentItemInDaRoot.id);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Delete tag from Content Item")
    public void DeleteTagFromContentItem() {
        String tagId;

        ContentItem contentItemInDaRoot = steps.createContentItem(ROOT_FOLDER);

        contentItemInDaRoot.addTag(getRandomTextField("Tag"));

        Response getItem = contentItemInDaRoot.getItem();
        tagId = getItem.jsonPath().get("data.tags[0].id");

        Response response = contentItemInDaRoot.deleteTag(tagId);

        checkStatusCode(204, response.statusCode());
        assertTrue(!contentItemInDaRoot.getItem().jsonPath().get("data.tags").toString().contains(tagId));
    }
*/
}
