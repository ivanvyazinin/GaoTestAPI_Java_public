package test.java.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;
import main.java.steps.ContentItemSteps;

import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.properties.Constants.ROOT_FOLDER;

public class ContentItemsTest extends SuperTest {
    private ContentItemSteps contentItemSteps;

    @BeforeClass
    public void prepareSteps(){
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
        contentItemSteps.checkThatJsonContains(-2,"data.constructorItems[1].positionY");
        contentItemSteps.checkThatJsonContains(0,"data.constructorItems[1].positionX");
    }

    @Test
    @Story("Create Content Item")
    public void createContentItemInDaFolder() {
        contentItemSteps.createContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.checkStatusCode(201);

        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(FOLDER_FOR_TESTS);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Create Content Item")
    public void createContentItemWithoutDescription() {
        contentItemSteps.createContentItem(getRandomTextField("name"),null,FOLDER_FOR_TESTS);
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
    public void editContentItem() {
        contentItemSteps.createContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.testContentItem.name = "Changed" + contentItemSteps.testContentItem.name;
        contentItemSteps.testContentItem.description = "Changed" + contentItemSteps.testContentItem.description;
        contentItemSteps.editContentItem();
        contentItemSteps.checkStatusCode(200);
        contentItemSteps.checkThatBodyHasValue(contentItemSteps.testContentItem.description);
        contentItemSteps.checkThatBodyHasValue(contentItemSteps.testContentItem.name);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Move Content Item")
    public void moveContentItem() {
        contentItemSteps.createContentItem(ROOT_FOLDER);
        contentItemSteps.moveContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.checkStatusCode(200);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(FOLDER_FOR_TESTS);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Move Content Item")
    public void moveContentItemToFolderWithSameContentItem() {
        contentItemSteps.createContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.createContentItem(contentItemSteps.testContentItem.name, contentItemSteps.testContentItem.description, ROOT_FOLDER);
        contentItemSteps.moveContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.checkStatusCode(400);
        contentItemSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Delete Content Item")
    public void deleteContentItem() {
        contentItemSteps.createContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.deleteContentItem(contentItemSteps.testContentItem.id);
        contentItemSteps.checkStatusCode(204);
        contentItemSteps.getContentItem();
        contentItemSteps.checkStatusCode(404);
    }
}
