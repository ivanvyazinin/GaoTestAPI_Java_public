package test.java.contentCloud;

import io.qameta.allure.Story;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.SuperTest;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.TitleSteps;

import java.util.HashMap;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.ROOT_FOLDER;

public class ScreensTest extends SuperTest {
    private FolderSteps folderSteps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private TitleSteps titleSteps;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        titleSteps = new TitleSteps();
        contentItemSteps = new ContentItemSteps();
    }

    @Test
    @Story("Create screen")
    public void createScreenInDaRoot() {
        screenSteps.createScreen(ROOT_FOLDER);
        screenSteps.checkStatusCode(201);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Create screen")
    public void createScreenInDaFolder() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        screenSteps.checkStatusCode(201);
        screenSteps.getScreen();
        screenSteps.checkThatBodyHasValue(FOLDER_FOR_TESTS);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Create screen")
    public void createScreenInDaRootWithoutDescription() {
        screenSteps.createScreen(getRandomTextField("Screen name"), null, FOLDER_FOR_TESTS);
        screenSteps.checkStatusCode(201);
    }


    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Create screen")
    public void createScreenInDaRootWithSameName() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        screenSteps.createScreen(screenSteps.testScreen.name, null, FOLDER_FOR_TESTS);
        screenSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    //TODO Enable when method implemented
    @Test (dependsOnMethods = "createScreenInDaRoot", enabled = false)
    public void editScreenName() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        screenSteps.testScreen.name = "Changed" + screenSteps.testScreen.name;
        screenSteps.testScreen.description = "Changed" +screenSteps.testScreen.description;
        screenSteps.editScreen();
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasValue(screenSteps.testScreen.name);
        screenSteps.checkThatBodyHasValue(screenSteps.testScreen.description);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Delete screen")
    public void deleteScreen() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        screenSteps.deleteScreen(screenSteps.testScreen.id);
        screenSteps.checkStatusCode(204);
        screenSteps.getScreen();
        screenSteps.checkStatusCode(404);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Delete screen with block")
    public void deleteScreenWithBlock() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        titleSteps.createTitle(screenSteps.testScreen.id);
        screenSteps.deleteScreen(screenSteps.testScreen.id);
        screenSteps.checkStatusCode(204);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Delete screen")
    public void deleteScreenUsedInCI() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        contentItemSteps.createContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.placeScreenIntoConstructor(screenSteps.testScreen.id);
        screenSteps.deleteScreen(screenSteps.testScreen.id);
        screenSteps.checkStatusCode(400);
        screenSteps.checkThatJsonContains("96afdf5d-b04f-428e-8144-92e2dea2cdfc",PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Move screen")
    public void moveScreen(){
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        screenSteps.moveScreen(folderSteps.testFolder.id);
        screenSteps.checkStatusCode(200);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Copy screen")
    public void copyEmptyScreenTwice(){
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        String originalId = screenSteps.testScreen.id;

        screenSteps.copyScreen(folderSteps.testFolder.id);
        screenSteps.checkStatusCode(200);

        screenSteps.getScreen();
        screenSteps.checkThatBodyHasNotValue(originalId);

        screenSteps.copyScreen(folderSteps.testFolder.id);
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasValue(screenSteps.testScreen.name+"(1)");
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Copy screen")
    public void copyScreenWithBlock(){
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        titleSteps.createTitle(screenSteps.testScreen.id);

        screenSteps.copyScreen(folderSteps.testFolder.id);
        screenSteps.checkStatusCode(200);

        screenSteps.getScreenBlocks();
        screenSteps.checkThatBodyHasValue(titleSteps.testTitle.title);
        screenSteps.checkThatBodyHasNotValue(titleSteps.testTitle.id);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Sort blocks of the screen")
    public void sortBlocksOfTheScreen(){
        HashMap<String, String> params = new HashMap<>();
        params.put("sorting", "position");
        params.put("order", "desc");

        screenSteps.createScreen(FOLDER_FOR_TESTS);
        titleSteps.createTitle(screenSteps.testScreen.id);
        titleSteps.createTitle(screenSteps.testScreen.id, 1);
        titleSteps.createTitle(screenSteps.testScreen.id, 2);

        screenSteps.ScreensApi.setRequestParameters(params);

        screenSteps.getScreenBlocks();
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatJsonContains(2,"data.items[0].position");

        screenSteps.ScreensApi.resetRequestParameters();
        screenSteps.getScreenBlocks();
        screenSteps.checkThatJsonContains(0,"data.items[0].position");
    }
}