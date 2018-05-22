package test.java.api.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.FolderSteps;
import test.java.steps.ScreenSteps;
import test.java.steps.blocks.TitleSteps;


import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.ROOT_FOLDER;

public class FoldersTest extends SuperTest {
    FolderSteps folderSteps;
    ScreenSteps screenSteps;
    TitleSteps titleSteps;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        titleSteps = new TitleSteps();
    }

    @Test
    public void createFolderInDaRoot(){
        folderSteps.createFolder(ROOT_FOLDER);
        folderSteps.checkStatusCode(201);
    }

    @Test
    public void createFolderInDaFolder(){
        folderSteps.createFolder(folderForTests);
        folderSteps.checkStatusCode(201);
    }

    @Test
    public void createFolderWithSameName() {
        folderSteps.createFolder(folderForTests);
        folderSteps.createFolder(folderSteps.testFolder.name, folderForTests);
        folderSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test
    public void editFolderName(){
        folderSteps.createFolder(folderForTests);
        folderSteps.testFolder.name = "Changed " + folderSteps.testFolder.name;
        folderSteps.editFolder();
        folderSteps.checkThatBodyHasValue(folderSteps.testFolder.name);
    }

    @Test
    public void deleteFolder() {
        folderSteps.createFolder(folderForTests);
        folderSteps.deleteFolder();
        folderSteps.checkStatusCode(204);
        folderSteps.getFolder();
        folderSteps.checkStatusCode(404);
    }

    @Test
    public void moveFolder() {
        folderSteps.createFolder(folderForTests);
        String destination = folderSteps.testFolderId;
        folderSteps.createFolder(folderForTests);
        folderSteps.testFolder.parentFolder = destination;
        folderSteps.editFolder();
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatBodyHasValue(destination);
    }

    @Test
    public void getFolders() {
        folderSteps.getAllFolder();
        folderSteps.checkStatusCode(200);
    }

    @Test
    @Story("Delete screen")
    public void deleteFolderWithScreenWithBlock(){
        folderSteps.createFolder(folderForTests);
        screenSteps.createScreen(folderSteps.testFolderId);
        titleSteps.createTitle(screenSteps.testScreenId);

        folderSteps.deleteFolder();
        folderSteps.checkStatusCode(200);
    }


    @Test(groups = "Slow")
    public void createFiftyFolders() {
        folderSteps.createFolder(folderForTests);
        for(int i=0; i<49; i++){
            folderSteps.createFolder(folderSteps.testFolderId);
        }
        folderSteps.checkStatusCode(400);
        folderSteps.checkThatJsonContains("5f4f2d2f-b6d0-46e8-a1f3-26fc1d0cb5c6",PATH_ERROR);
    }
}
