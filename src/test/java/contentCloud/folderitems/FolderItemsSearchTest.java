package test.java.contentCloud.folderitems;

import io.qameta.allure.Description;
import main.java.api.contentCloud.TagsAPI;
import main.java.entities.contentCloud.Tag;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.steps.ContentItemSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import java.util.HashMap;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomTextField;

public class FolderItemsSearchTest extends CommonCloudTest {
    private FolderSteps folderSteps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private TagsAPI tagsAPI;

    private String nameOfFirstFolder;
    private String testFolderId;
    private HashMap<String, String> params;
    private String tagName;

    @BeforeClass
    @Description("Preparing test data")
    void prepareSteps(){
        folderSteps = new FolderSteps();
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
        tagsAPI = new TagsAPI();
        params = new HashMap<>();

        folderSteps.createFolder(FOLDER_FOR_TESTS);

        testFolderId = folderSteps.testFolder.id;

        nameOfFirstFolder = getRandomTextField("! first folder");
        folderSteps.createFolder(new Folder(nameOfFirstFolder, testFolderId));

        folderSteps.createFolder(FOLDER_FOR_TESTS);
        for (int i=0; i<5; i++){
            folderSteps.createFolder(testFolderId);
        }
        for (int i=0; i<6; i++){
            contentItemSteps.createContentItem(new ContentItem(testFolderId));
        }
        for (int i=0; i<6; i++){
            screenSteps.createScreen(testFolderId);
        }

        tagName = getRandomTextField("TagName");
        tagsAPI.addTag("screen", screenSteps.testScreen.id, new Tag(tagName));
    }

    @Test
    public void simpleSearch(){
        params.clear();
        params.put("search", "folderAuto");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.searchFolderItems();
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByFolder(){
        params.clear();
        params.put("itemType", "1");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.searchFolderItems();
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByScreen(){
        params.clear();
        params.put("itemType", "2");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.searchFolderItems();
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(2, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByContentItem(){
        params.clear();
        params.put("itemType", "3");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.searchFolderItems();
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(3, "data.items[0].itemType");
    }

    @Test
    public void searchWithFilter(){
        params.clear();
        params.put("search", "auto");
        params.put("itemType", "3");

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.searchFolderItems();
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(3, "data.items[0].itemType");
    }

    @Test
    public void searchByTag(){
        params.clear();
        params.put("search", tagName);

        folderSteps.folderItemsAPI.setRequestParameters(params);

        folderSteps.searchFolderItems();
        folderSteps.checkStatusCode(200);
        folderSteps.checkItemsNumberInResponse(1);
    }
}
