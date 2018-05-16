package test.java.api.contentCloud;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.FolderSteps;

import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.ROOT_FOLDER;
import static org.testng.Assert.assertEquals;

public class FoldersTest extends SuperTest {
    FolderSteps steps;

    @BeforeClass
    public void prepareSteps(){
        steps = new FolderSteps();
    }

    @Test
    public void createFolderInDaRoot(){
        steps.createFolder(ROOT_FOLDER);
        steps.checkStatusCode(201);
    }

    @Test
    public void createFolderInDaFolder(){
        steps.createFolder(ROOT_FOLDER);
        steps.createFolder(steps.testFolderId);
        steps.checkStatusCode(201);
    }

    @Test
    public void createFolderInDaRootWithSameName() {
        steps.createFolder(ROOT_FOLDER);
        steps.createFolder(steps.testFolder.name, ROOT_FOLDER);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test
    public void editFolderName(){
        steps.createFolder(ROOT_FOLDER);
        steps.testFolder.name = "Changed " + steps.testFolder.name;
        steps.editFolder();
        steps.checkThatBodyHasValue(steps.testFolder.name);
    }

    @Test
    public void deleteFolder() {
        steps.createFolder(ROOT_FOLDER);
        steps.deleteFolder();
        steps.checkStatusCode(204);
        steps.getFolder();
        steps.checkStatusCode(404);
    }

    @Test
    public void moveFolder() {
        steps.createFolder(ROOT_FOLDER);
        String destination = steps.testFolderId;
        steps.createFolder(ROOT_FOLDER);
        steps.testFolder.parentFolder = destination;
        steps.editFolder();
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(destination);
    }

    @Test
    public void getFolders() {
        steps.getAllFolder();
        steps.checkStatusCode(200);
    }

    @Test(groups = "Slow")
    public void createFiftyFolders() {
        steps.createFolder(ROOT_FOLDER);
        for(int i=0; i<49; i++){
            steps.createFolder(steps.testFolderId);
        }
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("5f4f2d2f-b6d0-46e8-a1f3-26fc1d0cb5c6",PATH_ERROR);
    }
}
