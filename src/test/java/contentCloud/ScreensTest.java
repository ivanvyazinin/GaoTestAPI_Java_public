package test.java.contentCloud;

import io.qameta.allure.Story;
import main.java.api.contentCloud.ScreenAPI;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.utils.Lists.getRandomItem;

public class ScreensTest extends CommonCloudTest {
    private FolderSteps folderSteps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;

    ScreenAPI screenAPI;
    Screen testScreen;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();

        screenAPI = new ScreenAPI();
    }

    @BeforeMethod
    public void prepareEntity(){
        testScreen = new Screen(FOLDER_FOR_TESTS);
    }

    @Test
    @Story("Create screen")
    public void createScreenInDaFolder() {
        newResponse = screenAPI.post(testScreen);
        checkStatusCode(201);
        checkThatBodyHasValue(FOLDER_FOR_TESTS);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Create screen")
    public void createScreenWithoutDescription() {
        testScreen.description=null;
        newResponse = screenAPI.post(testScreen);
        checkStatusCode(201);
    }


    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Create screen")
    public void createScreenWithSameName() {
        screenSteps.createScreen(testScreen);

        newResponse = screenAPI.post(testScreen);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    public void editScreenNameAndDescription() {
        screenSteps.createScreen(testScreen);

        testScreen.name = "Changed" + testScreen.name;
        testScreen.description = "Changed" +testScreen.description;

        newResponse = screenAPI.put(testScreen.id,testScreen);
        checkStatusCode(200);
        checkThatBodyHasValue(testScreen.name);
        checkThatBodyHasValue(testScreen.description);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Delete screen")
    public void deleteScreen() {
        screenSteps.createScreen(testScreen);

        newResponse = screenAPI.delete(testScreen.id);
        checkStatusCode(204);

        newResponse = screenAPI.getById(testScreen.id);
        checkStatusCode(404);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Delete screen with block")
    public void deleteScreenWithBlock() {
        testScreen = screenSteps.getScreenWithBlock(new Screen(FOLDER_FOR_TESTS), new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id));

        newResponse = screenAPI.delete(testScreen.id);
        checkStatusCode(204);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Delete screen")
    public void deleteScreenUsedInCI() {
        testScreen = screenSteps.createScreen(new Screen(FOLDER_FOR_TESTS));
        contentItemSteps.createContentItemWithScreen(new ContentItem(FOLDER_FOR_TESTS),testScreen);

        newResponse = screenAPI.delete(testScreen.id);
        checkStatusCode(400);
        checkThatJsonContains("96afdf5d-b04f-428e-8144-92e2dea2cdfc",PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Move screen")
    public void moveScreen(){
        Folder destination = folderSteps.createFolder(new Folder(FOLDER_FOR_TESTS));
        screenSteps.createScreen(testScreen);

        testScreen.parentFolder = destination.id;

        newResponse = screenAPI.put(testScreen.id,testScreen);
        checkStatusCode(200);
        checkThatBodyHasValue(destination.id);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Copy screen")
    public void copyEmptyScreenTwice(){
        Folder destination = folderSteps.createFolder(new Folder(FOLDER_FOR_TESTS));
        screenSteps.createScreen(testScreen);

        newResponse = screenAPI.copy(destination.id,testScreen.id);
        checkStatusCode(200);
        checkThatBodyHasNotValue(testScreen.id);

        newResponse = screenAPI.copy(destination.id,testScreen.id);
        checkStatusCode(200);
        checkThatBodyHasNotValue(testScreen.id);
        checkThatBodyHasValue(testScreen.name+"(1)");
    }

    @Test ()
    @Story("Copy screen")
    public void copyScreenWithBlock(){
        Paragraph testBlock = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        Folder destination = folderSteps.createFolder(new Folder(FOLDER_FOR_TESTS));
        testScreen = screenSteps.getScreenWithBlock(new Screen(FOLDER_FOR_TESTS), testBlock);
        testBlock.id = screenSteps.getScreenBlocks(testScreen).items.get(0).id;

        newResponse = screenAPI.copy(destination.id,testScreen.id);
        checkStatusCode(200);
        String screenCopyId = newResponse.jsonPath().getString("data.id");

        newResponse = screenAPI.getBlocks(screenCopyId);
        checkThatJsonContains(1,"data.count");
        checkThatBodyHasNotValue(testBlock.id);
        checkThatBodyHasValue(testBlock.paragraph);
    }
}