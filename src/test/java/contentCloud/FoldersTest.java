package test.java.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.TitleSteps;


import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.FOLDER_FOR_TESTS;

public class FoldersTest extends CommonCloudTest {
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
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        folderSteps.checkStatusCode(201);
    }

    @Test
    public void createFolderWithSameName() {
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(folderSteps.testFolder.name, FOLDER_FOR_TESTS);
        folderSteps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test
    public void editFolderName(){
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        folderSteps.testFolder.name = "Changed " + folderSteps.testFolder.name;
        folderSteps.editFolder();
        folderSteps.checkThatBodyHasValue(folderSteps.testFolder.name);
    }

    @Test
    public void deleteFolder() {
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        folderSteps.deleteFolder();
        folderSteps.checkStatusCode(204);
        folderSteps.getFolder();
        folderSteps.checkStatusCode(404);
    }

    @Test
    public void moveFolder() {
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        String destination = folderSteps.testFolder.id;
        folderSteps.createFolder(FOLDER_FOR_TESTS);
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
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        screenSteps.createScreen(folderSteps.testFolder.id);
        titleSteps.createTitle(screenSteps.testScreen.id);

        folderSteps.deleteFolder();
        folderSteps.checkStatusCode(204);
    }


    @Test(groups = "Slow")
    public void createFiftyFolders() {
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        for(int i=0; i<48; i++){
            folderSteps.createFolder(folderSteps.testFolder.id);
        }
        folderSteps.checkStatusCode(400);
        folderSteps.checkThatJsonContains("5f4f2d2f-b6d0-46e8-a1f3-26fc1d0cb5c6",PATH_ERROR);
    }
}
