package test.java.contentCloud.folderitems;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextField;

@Epic("Content Cloud")
@Feature("Editor search items in Content Cloud")
public class FolderItemsSearchTest extends SuperTest {
    private FolderSteps folderSteps;
    private String nameOfFirstFolder;
    private String tagName;
    private Folder testFolder;

    @BeforeClass
    @Description("Preparing test data")
    void prepareSteps(){
        CommonSteps steps = new CommonSteps();
        folderSteps = new FolderSteps();
        ScreenSteps screenSteps = new ScreenSteps();
        nameOfFirstFolder = getRandomTextField("! first folder");

        testFolder = steps.createEntity(new Folder(context.getTestFolder()));
        steps.createEntity(new Folder(nameOfFirstFolder, testFolder.id));
        for (int i=0; i<3; i++){
            steps.createEntity(new Folder(testFolder.id));
        }
        for (int i=0; i<3; i++){
            steps.createEntity(new ContentItem(testFolder.id));
        }
        for (int i=0; i<4; i++){
            steps.createEntity(new Screen(testFolder.id));
        }

        for (int i=0; i<4; i++){
            steps.createEntity(new Paragraph(testFolder.id, context.getLevel()));
        }

        tagName = getRandomTextField("AutoTagForSearchTest");
        screenSteps.createScreenWithTag(new Screen(testFolder.id),tagName);
    }

    @Test
    public void simpleSearch(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"search",nameOfFirstFolder}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByFolder(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"itemType","1"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByScreen(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"itemType","2"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(2, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByContentItem(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"itemType","3"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(3, "data.items[0].itemType");
    }

    @Test
    public void simpleFilterByBlock(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"itemType","4"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(4, "data.items[0].itemType");
    }

    @Test
    public void searchWithFilter(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"search","TA"},
                {"itemType","3"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(3, "data.items[0].itemType");
    }

    @Test
    public void searchByTag(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"search",tagName}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1,"data.count");
    }
}
