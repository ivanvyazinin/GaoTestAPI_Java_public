package test.java.api.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.ContentItemSteps;

import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
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
        contentItemSteps.createContentItem(folderForTests);
        contentItemSteps.checkStatusCode(201);

        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(folderForTests);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Create Content Item")
    public void createContentItemWithoutDescription() {
        contentItemSteps.createContentItem(getRandomTextField("name"),null,folderForTests);
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
        contentItemSteps.createContentItem(folderForTests);
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
        contentItemSteps.moveContentItem(folderForTests);
        contentItemSteps.checkStatusCode(200);
        contentItemSteps.getContentItem();
        contentItemSteps.checkThatBodyHasValue(folderForTests);
    }

    @Test(dependsOnMethods = "createContentItemInDaRoot")
    @Story("Move Content Item")
    public void moveContentItemToFolderWithSameContentItem() {
        contentItemSteps.createContentItem(folderForTests);
        contentItemSteps.createContentItem(contentItemSteps.testContentItem.name, contentItemSteps.testContentItem.description, ROOT_FOLDER);
        contentItemSteps.moveContentItem(folderForTests);
        contentItemSteps.checkStatusCode(400);
        contentItemSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }
}
