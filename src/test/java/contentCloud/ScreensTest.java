package test.java.contentCloud;

import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import main.java.steps.ScreenSteps;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.utils.Lists.getRandomItem;

public class ScreensTest extends CommonCloudTest {
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private CommonSteps steps;

    private Screen testScreen;

    @BeforeClass
    public void prepareSteps(){
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testScreen = new Screen(FOLDER_FOR_TESTS);
    }

    @Test
    @Story("Create screen")
    public void createScreenInDaFolder() {
        steps.createEntity(testScreen);
        steps.checkStatusCode(201);
        steps.checkThatBodyHasValue(FOLDER_FOR_TESTS);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Create screen")
    public void createScreenWithoutDescription() {
        testScreen.description=null;
        steps.createEntity(testScreen);
        steps.checkStatusCode(201);
    }


    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Create screen")
    public void createScreenWithSameName() {
        steps.createEntity(testScreen);
        steps.createEntity(testScreen);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    public void editScreenNameAndDescription() {
        testScreen = steps.createEntity(testScreen);

        testScreen.name = "Changed" + testScreen.name;
        testScreen.description = "Changed" +testScreen.description;

        steps.editEntity(testScreen);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(testScreen.name);
        steps.checkThatBodyHasValue(testScreen.description);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Delete screen")
    public void deleteScreen() {
        testScreen = steps.createEntity(testScreen);

        steps.deleteEntity(testScreen);
        steps.checkStatusCode(204);

        steps.getEntity(testScreen);
        steps.checkStatusCode(404);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Delete screen with block")
    public void deleteScreenWithBlock() {
        testScreen = screenSteps.getScreenWithBlock(
                new Screen(FOLDER_FOR_TESTS),
                new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id));

        steps.deleteEntity(testScreen);
        steps.checkStatusCode(204);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Delete screen")
    public void deleteScreenUsedInCI() {
        testScreen = steps.createEntity(
                new Screen(FOLDER_FOR_TESTS));
        contentItemSteps.createContentItemWithScreen(
                new ContentItem(FOLDER_FOR_TESTS),testScreen);

        steps.deleteEntity(testScreen);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("96afdf5d-b04f-428e-8144-92e2dea2cdfc",PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Move screen")
    public void moveScreen(){
        Folder destination = steps.createEntity(
                new Folder(FOLDER_FOR_TESTS));
        testScreen = steps.createEntity(testScreen);

        testScreen.parentFolder = destination.id;

        steps.editEntity(testScreen);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(destination.id);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Copy screen")
    public void copyEmptyScreenTwice(){
        Folder destination = steps.createEntity(
                new Folder(FOLDER_FOR_TESTS));
        testScreen = steps.createEntity(testScreen);

        steps.copyEntity(testScreen,destination.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(testScreen.id);

        steps.copyEntity(testScreen,destination.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(testScreen.id);
        steps.checkThatBodyHasValue(testScreen.name+"(1)");
    }

    @Test ()
    @Story("Copy screen")
    public void copyScreenWithBlock(){
        Paragraph testBlock = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        Folder destination = steps.createEntity(
                new Folder(FOLDER_FOR_TESTS));
        testScreen = screenSteps.getScreenWithBlock(
                new Screen(FOLDER_FOR_TESTS), testBlock);
        testBlock.id = screenSteps.getScreenBlocks(testScreen).items.get(0).id;

        Screen screenCopy = steps.copyEntity(testScreen,destination.id);
        steps.checkStatusCode(200);

        screenSteps.getScreenBlocks(screenCopy);
        screenSteps.checkThatJsonContains(1,"data.count");
        screenSteps.checkThatBodyHasNotValue(testBlock.id);
        screenSteps.checkThatBodyHasValue(testBlock.paragraph);
    }
}