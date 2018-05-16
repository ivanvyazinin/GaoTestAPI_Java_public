package test.java.api.contentCloud;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.ContentItemSteps;
import test.java.steps.FolderSteps;
import test.java.steps.ScreenSteps;

import static main.java.properties.Constants.ROOT_FOLDER;

public class FolderItemsTest extends SuperTest {
    private FolderSteps folderSteps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;

    @BeforeClass
    public void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
    }

    @Test
    @Story("Get items of root folder")
    public void getItemsOfRootFolder() {
        folderSteps.getRootFolderItems();
        folderSteps.checkStatusCode(200);
    }

    @Test
    @Story("Folder contains folder, CI and Screen. Get items")
    public void getItemsOfFolder() {
        folderSteps.createFolder(ROOT_FOLDER);
        String folderId = folderSteps.testFolderId;

        folderSteps.createFolder(folderId);
        screenSteps.createScreen(folderId);
        contentItemSteps.createContentItem(folderId);

        folderSteps.getFolderItems(folderId);
        folderSteps.checkThatJsonContains(3,"data.count");
        folderSteps.checkItemsNumberInResponse(3);
    }
}
