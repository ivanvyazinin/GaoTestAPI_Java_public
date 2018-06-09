package test.java.contentCloud;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.ContentItemSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.FOLDER_FOR_TESTS;

@Feature("Folder items")
public class FolderItemsTest extends CommonCloudTest {
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
        folderSteps.getFolderItems(ROOT_FOLDER);
        folderSteps.checkStatusCode(200);
    }

    @Test
    @Story("Folder contains folder, CI and Screen. Get items")
    public void getItemsOfFolder() {
        folderSteps.createFolder(FOLDER_FOR_TESTS);
        String folderId = folderSteps.testFolder.id;

        folderSteps.createFolder(folderId);
        screenSteps.createScreen(folderId);
        contentItemSteps.createContentItem(folderId);

        folderSteps.getFolderItems(folderId);
        folderSteps.checkThatJsonContains(3,"data.count");
        folderSteps.checkItemsNumberInResponse(3);
    }
}
