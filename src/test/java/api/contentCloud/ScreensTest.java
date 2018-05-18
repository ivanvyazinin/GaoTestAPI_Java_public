package test.java.api.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.FolderSteps;
import test.java.steps.ScreenSteps;
import test.java.steps.blocks.TitleSteps;

import static main.java.utils.Generator.getRandomTextField;
import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.ROOT_FOLDER;

public class ScreensTest extends SuperTest {
    private FolderSteps folderSteps;
    private ScreenSteps screenSteps;
    private TitleSteps titleSteps;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        titleSteps = new TitleSteps();
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
        screenSteps.createScreen(folderForTests);
        screenSteps.checkStatusCode(201);
        screenSteps.getScreen();
        screenSteps.checkThatBodyHasValue(folderForTests);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Create screen")
    public void createScreenInDaRootWithoutDescription() {
        screenSteps.createScreen(getRandomTextField("Screen name"), null, folderForTests);
        screenSteps.checkStatusCode(201);
    }


    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Create screen")
    public void createScreenInDaRootWithSameName() {
        screenSteps.createScreen(folderForTests);
        screenSteps.createScreen(screenSteps.testScreen.name, null, folderForTests);
        screenSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    //TODO Enable when method implemented
    @Test (dependsOnMethods = "createScreenInDaRoot", enabled = false)
    public void editScreenName() {
        screenSteps.createScreen(folderForTests);
        screenSteps.testScreen.name = "Changed" + screenSteps.testScreen.name;
        screenSteps.testScreen.description = "Changed" +screenSteps.testScreen.description;
        screenSteps.editScreen();
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasValue(screenSteps.testScreen.name);
        screenSteps.checkThatBodyHasValue(screenSteps.testScreen.description);
    }

    //TODO Enable when method implemented
    @Test (dependsOnMethods = "createScreenInDaRoot", enabled = false)
    public void deleteScreen() {
        screenSteps.createScreen(folderForTests);
        screenSteps.deleteScreen(screenSteps.testScreenId);
        screenSteps.checkStatusCode(204);
        screenSteps.getScreen();
        screenSteps.checkStatusCode(404);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Move screen")
    public void moveScreen(){
        folderSteps.createFolder(folderForTests);
        screenSteps.createScreen(folderForTests);
        screenSteps.moveScreen(folderSteps.testFolderId);
        screenSteps.checkStatusCode(200);
    }

    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Copy screen")
    public void copyEmptyScreenTwice(){
        folderSteps.createFolder(folderForTests);
        screenSteps.createScreen(folderForTests);
        String originalId = screenSteps.testScreenId;

        screenSteps.copyScreen(folderSteps.testFolderId);
        screenSteps.checkStatusCode(200);

        screenSteps.getScreen();
        screenSteps.checkThatBodyHasNotValue(originalId);

        screenSteps.copyScreen(folderSteps.testFolderId);
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasValue(screenSteps.testScreen.name+"(1)");
    }


    @Test (dependsOnMethods = "createScreenInDaRoot")
    @Story("Copy screen")
    public void copyScreenWithBlock(){
        folderSteps.createFolder(folderForTests);
        screenSteps.createScreen(folderForTests);
        titleSteps.createTitle(screenSteps.testScreenId);

        screenSteps.copyScreen(folderSteps.testFolderId);
        screenSteps.checkStatusCode(200);

        screenSteps.getScreenBlocks();
        screenSteps.checkThatBodyHasValue(titleSteps.testTitle.title);
        screenSteps.checkThatBodyHasNotValue(titleSteps.testTitleId);
    }
}