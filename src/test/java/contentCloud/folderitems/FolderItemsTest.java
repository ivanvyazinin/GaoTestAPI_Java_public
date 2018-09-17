package test.java.contentCloud.folderitems;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.FolderSteps;
import test.java.SuperTest;

import static main.java.properties.Constants.ROOT_FOLDER;

@Feature("Folder items")
public class FolderItemsTest extends SuperTest {
    private CommonSteps steps;
    private FolderSteps folderSteps;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        folderSteps = new FolderSteps();
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
        Folder testFolder = steps.createEntity(
                new Folder(context.getTestFolder()));

        steps.createEntity(new Folder(testFolder.id));
        steps.createEntity(new Screen(testFolder.id));
        steps.createEntity(new ContentItem(testFolder.id));

        folderSteps.getFolderItems(testFolder.id);
        folderSteps.checkThatJsonContains(3,"data.count");
        folderSteps.checkItemsNumberInResponse(3);
    }
}
