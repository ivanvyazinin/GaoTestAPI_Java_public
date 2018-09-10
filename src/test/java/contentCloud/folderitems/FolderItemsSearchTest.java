package test.java.contentCloud.folderitems;

import io.qameta.allure.Description;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import java.util.HashMap;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomTextField;

public class FolderItemsSearchTest extends CommonCloudTest {
    private FolderSteps folderSteps;
    private String nameOfFirstFolder;
    private HashMap<String, String> params;
    private String tagName;
    private Folder testFolder;

    @BeforeClass
    @Description("Preparing test data")
    void prepareSteps(){
        CommonSteps steps = new CommonSteps();
        folderSteps = new FolderSteps();
        ScreenSteps screenSteps = new ScreenSteps();
        params = new HashMap<>();
        nameOfFirstFolder = getRandomTextField("! first folder");

        testFolder = steps.createEntity(new Folder(FOLDER_FOR_TESTS));
        steps.createEntity(new Folder(nameOfFirstFolder, testFolder.id));
        for (int i=0; i<5; i++){
            steps.createEntity(new Folder(testFolder.id));
        }
        for (int i=0; i<6; i++){
            steps.createEntity(new ContentItem(testFolder.id));
        }
        for (int i=0; i<6; i++){
            steps.createEntity(new Screen(testFolder.id));
        }

        tagName = getRandomTextField("AutoTagForSearchTest");
        screenSteps.createScreenWithTag(new Screen(testFolder.id),tagName);
    }

    @BeforeMethod
    public void prepareParams(){
        params.clear();
        params.put("parentFolder",testFolder.id);
    }

    @Test
    public void simpleSearch(){
        params.put("search", nameOfFirstFolder);

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByFolder(){
        params.put("itemType", "1");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByScreen(){
        params.put("itemType", "2");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(2, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByContentItem(){
        params.put("itemType", "3");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(3, "data.items[0].itemType");
    }

    @Test
    public void searchWithFilter(){
        params.put("search", "auto");
        params.put("itemType", "3");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(3, "data.items[0].itemType");
    }

    @Test
    public void searchByTag(){
        params.put("search", tagName);

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkItemsNumberInResponse(1);
    }
}
