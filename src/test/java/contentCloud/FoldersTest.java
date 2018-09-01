package test.java.contentCloud;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.FoldersAPI;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.ContentItemSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.BlockSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static main.java.properties.Constants.*;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Lists.getRandomItem;

public class FoldersTest extends CommonCloudTest {
    FolderSteps folderSteps;
    ScreenSteps screenSteps;
    ContentItemSteps contentItemSteps;
    BlockSteps blockSteps;

    FoldersAPI foldersAPI;
    Folder testFolder;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
        blockSteps = new BlockSteps();

        foldersAPI = new FoldersAPI();
    }

    @BeforeMethod
    public void prepareEntity(){
        testFolder = new Folder(FOLDER_FOR_TESTS);
    }

    @Test
    public void createFolderInDaRoot(){
        newResponse = foldersAPI.post(new Folder(ROOT_FOLDER));
        checkStatusCode(201);
    }

    @Test
    public void createFolderInDaFolder(){
        newResponse = foldersAPI.post(testFolder);
        checkStatusCode(201);
    }

    @Test
    public void createFolderWithSameName() {
        foldersAPI.post(testFolder);
        newResponse = foldersAPI.post(testFolder);
        checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test
    public void editFolderName(){
        folderSteps.createFolder(testFolder);

        testFolder.name = "Changed " + testFolder.name;
        newResponse = foldersAPI.put(testFolder.id,testFolder);
        checkThatBodyHasValue(testFolder.name);
    }

    @Test
    public void moveFolder() {
        folderSteps.createFolder(testFolder);
        Folder destination = folderSteps.createFolder(new Folder(FOLDER_FOR_TESTS));

        testFolder.parentFolder = destination.id;
        newResponse = foldersAPI.put(testFolder.id, testFolder);
        checkStatusCode(200);
        checkThatBodyHasValue(destination.id);
    }

    @Test
    public void getFolders() {
        newResponse = foldersAPI.get();
        checkStatusCode(200);
    }

    @Test(groups = "Slow")
    @Story("Create folder")
    @Description("Cannot create a hierarchy with 51 folder")
    public void createFiftyFoldersv2() {
        folderSteps.createFolder(testFolder);
                for(int i=0; i<47; i++){
            testFolder.parentFolder=testFolder.id;
            folderSteps.createFolder(testFolder);
        }

        newResponse = foldersAPI.post(new Folder(testFolder.id));
        checkStatusCode(400);
        checkThatJsonContains("5f4f2d2f-b6d0-46e8-a1f3-26fc1d0cb5c6",PATH_ERROR);
    }

    @Test
    @Story("Delete folder")
    @Description("Delete empty folder")
    public void deleteEmptyFolder() {
        folderSteps.createFolder(testFolder);

        newResponse = foldersAPI.delete(testFolder.id);
        checkStatusCode(204);
        newResponse = foldersAPI.getById(testFolder.id);
        checkStatusCode(404);
    }


    @Test
    @Story("Delete folder")
    @Description("Delete folder with CI, screen and block")
    public void deleteFolderWithScreenBlockCI(){
        folderSteps.createFolder(testFolder);
        screenSteps.createScreen(new Screen(testFolder.id));
        contentItemSteps.createContentItem(new ContentItem(testFolder.id));
        blockSteps.getReusableBlock(new Paragraph(testFolder.id, getRandomItem(level).id));

        newResponse = foldersAPI.delete(testFolder.id);
        checkStatusCode(204);
    }

    @Test
    @Story("Delete folder")
    @Description("Delete folder with screen and block into it")
    public void deleteFolderWithScreenAndBlock(){
        folderSteps.createFolder(testFolder);
        screenSteps.getScreenWithBlock(new Screen(testFolder.id), new Paragraph(testFolder.id, getRandomItem(level).id));

        newResponse = foldersAPI.delete(testFolder.id);
        checkStatusCode(400);
        checkThatJsonContains("fcbd2e23-deae-4e43-9634-adeadf5d4cbb",PATH_ERROR);
    }

    @Test
    @Story("Delete folder")
    @Description("Delete folder with CI and screen into it")
    public void deleteFolderWithCIwithScreen(){
        folderSteps.createFolder(testFolder);
        Screen testScreen = screenSteps.createScreen(new Screen(testFolder.id));
        contentItemSteps.createContentItemWithScreen(new ContentItem(testFolder.id),testScreen);

        newResponse = foldersAPI.delete(testFolder.id);
        checkStatusCode(400);
        checkThatJsonContains("1f8f36c5-c5c1-4a8d-982d-5b467ea36bfd",PATH_ERROR);
    }
}
