package test.java.contentCloud;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.api.contentCloud.ContentItemAPI;
import main.java.entities.contentCloud.folderItems.ContentItem;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.java.steps.ContentItemSteps;

import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.properties.Constants.ROOT_FOLDER;

@Feature("Content Items")
public class ContentItemsTest extends CommonCloudTest {
    private ContentItemSteps contentItemSteps;
    private ContentItem testContentItem;
    private ContentItemAPI contentItemAPI;

    @BeforeClass
    public void prepareSteps(){
        contentItemAPI = new ContentItemAPI();
        contentItemSteps = new ContentItemSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentItem = new ContentItem(FOLDER_FOR_TESTS);
    }


    @Test
    @Story("Create Content Item")
    public void createContentItem() {
        newResponse = contentItemAPI.post(testContentItem);
        checkStatusCode(201);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Create Content Item")
    public void createContentItemWithoutDescription() {
        testContentItem.description = null;
        newResponse = contentItemAPI.post(testContentItem);
        checkStatusCode(201);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Get Content Items")
    public void getAllContentItems() {
        newResponse = contentItemAPI.get();
        checkStatusCode(200);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Edit Content Item")
    public void editContentItem() {
        contentItemSteps.createContentItem(testContentItem);

        testContentItem.name = "Changed" + testContentItem.name;
        testContentItem.description = "Changed" + testContentItem.description;

        newResponse = contentItemAPI.put(testContentItem.id, testContentItem);
        checkStatusCode(200);
        checkThatBodyHasValue(testContentItem.description);
        checkThatBodyHasValue(testContentItem.name);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Move Content Item")
    public void moveContentItem() {
        testContentItem = contentItemSteps.createContentItem(new ContentItem(ROOT_FOLDER));
        testContentItem.parentFolder = FOLDER_FOR_TESTS;

        newResponse = contentItemAPI.put(testContentItem.id, testContentItem);
        checkStatusCode(200);
        checkThatBodyHasValue(FOLDER_FOR_TESTS);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Move Content Item")
    public void moveContentItemToFolderWithSameContentItem() {
        testContentItem = contentItemSteps.createContentItem(new ContentItem(FOLDER_FOR_TESTS));

        testContentItem.parentFolder = ROOT_FOLDER;
        contentItemSteps.createContentItem(testContentItem);

        testContentItem.parentFolder = FOLDER_FOR_TESTS;
        newResponse = contentItemAPI.put(testContentItem.id, testContentItem);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Delete Content Item")
    public void deleteContentItem() {
        contentItemSteps.createContentItem(testContentItem);
        newResponse = contentItemAPI.delete(testContentItem.id);
        checkStatusCode(204);
        newResponse = contentItemAPI.getById(testContentItem.id);
        checkStatusCode(404);
    }
}
