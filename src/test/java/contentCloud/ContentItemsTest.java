package test.java.contentCloud;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.ROOT_FOLDER;

@Feature("Content Items")
public class ContentItemsTest extends SuperTest {
    private CommonSteps steps;

    private ContentItem testContentItem;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentItem = new ContentItem(context.getTestFolder());
    }


    @Test
    @Story("Create Content Item")
    public void createContentItem() {
        steps.createEntity(testContentItem);
        steps.checkStatusCode(201);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Create Content Item")
    public void createContentItemWithoutDescription() {
        testContentItem.description = null;
        steps.createEntity(testContentItem);
        steps.checkStatusCode(201);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Get Content Items")
    public void getAllContentItems() {
        steps.getEntites(ContentItem.class, ContentItem.url);
        steps.checkStatusCode(200);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Edit Content Item")
    public void editContentItem() {
        testContentItem = steps.createEntity(testContentItem);
        testContentItem.name = "Changed" + testContentItem.name;
        testContentItem.description = "Changed" + testContentItem.description;

        steps.editEntity(testContentItem);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(testContentItem.description);
        steps.checkThatBodyHasValue(testContentItem.name);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Move Content Item")
    public void moveContentItem() {
        testContentItem = steps.createEntity(
                new ContentItem(ROOT_FOLDER));
        testContentItem.parentFolder = context.getTestFolder();

        steps.editEntity(testContentItem);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(context.getTestFolder());
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Move Content Item")
    public void moveContentItemToFolderWithSameContentItem() {
        testContentItem = steps.createEntity(
                new ContentItem(ROOT_FOLDER));

        testContentItem.parentFolder = context.getTestFolder();
        steps.createEntity(testContentItem);

        testContentItem.parentFolder = context.getTestFolder();
        steps.editEntity(testContentItem);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test(dependsOnMethods = "createContentItem")
    @Story("Delete Content Item")
    public void deleteContentItem() {
        testContentItem  = steps.createEntity(testContentItem);
        steps.deleteEntity(testContentItem);
        steps.checkStatusCode(204);
        steps.getEntity(testContentItem);
        steps.checkStatusCode(404);
    }
}
