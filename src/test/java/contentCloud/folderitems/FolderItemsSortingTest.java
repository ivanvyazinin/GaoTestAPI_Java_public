package test.java.contentCloud.folderitems;

import io.qameta.allure.Description;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.FolderSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextField;

public class FolderItemsSortingTest extends SuperTest {
    private FolderSteps folderSteps;
    private Folder testFolder;
    private String nameOfFirstFolder;

    @BeforeClass
    @Description("Preparing test data")
    void prepareSteps() throws Exception{
        CommonSteps steps = new CommonSteps();
        folderSteps = new FolderSteps();

        testFolder = steps.createEntity(new Folder(context.getTestFolder()));

        nameOfFirstFolder = getRandomTextField("! first folder");
        steps.createEntity(new Folder(nameOfFirstFolder, testFolder.id));

        for (int i=0; i<3; i++){
            steps.createEntity(new Folder(testFolder.id));
        }
        Thread.sleep(100);
        for (int i=0; i<4; i++){
            steps.createEntity(new ContentItem(testFolder.id));
        }
        Thread.sleep(100);
        for (int i=0; i<5; i++){
            steps.createEntity(new Screen(testFolder.id));
        }
        Thread.sleep(100);
        for (int i=0; i<5; i++){
            steps.createEntity(new Paragraph(testFolder.id, context.getLevel()));
        }
    }

    @Test
    void checkDefaultPaginationLimitPageOne() {
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"page","1"},
                {"per_page","16"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(18, "data.count");
        folderSteps.checkItemsNumberInResponse(16);
    }

    @Test
    void checkDefaultPaginationLimitPageTwo() {
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"page","2"},
                {"per_page","16"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(18, "data.count");
        folderSteps.checkItemsNumberInResponse(2);
    }

    @Test
    void checkWeightSorting() {
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"parentFolder",testFolder.id},
                {"page","1"},
                {"per_page","16"},
                {"sorting","weight"},
                {"order","asc"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
        folderSteps.checkThatJsonContains(1, "data.items[1].itemType");
        folderSteps.checkThatJsonContains(1, "data.items[2].itemType");
    }

    @Test
    void checkSortingByCreatedAt() {
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"page","1"},
                {"per_page","16"},
                {"parentFolder",testFolder.id},
                {"sorting","weight"},
                {"order","asc"},
                {"sorting","createdAt"},
                {"order","desc"}
        });
        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
        folderSteps.checkThatJsonContains(4, "data.items[5].itemType");
        folderSteps.checkThatJsonContains(3, "data.items[15].itemType");
    }

    @Test
    void checkSortingByType() {
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"page","1"},
                {"per_page","16"},
                {"parentFolder",testFolder.id},
                {"sorting","weight"},
                {"order","asc"},
                {"sorting","itemType"},
                {"order","desc"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(1, "data.items[0].itemType");
        folderSteps.checkThatJsonContains(4, "data.items[4].itemType");
        folderSteps.checkThatJsonContains(3, "data.items[9].itemType");
        folderSteps.checkThatJsonContains(2, "data.items[13].itemType");
    }

    @Test
    void checkSortingByName(){
        folderSteps.folderItemsAPI.setRequestParameters(new String[][] {
                {"page","1"},
                {"per_page","16"},
                {"parentFolder",testFolder.id},
                {"sorting","weight"},
                {"order","asc"},
                {"sorting","name"},
                {"order","asc"}
        });

        folderSteps.getFolderItems(null);
        folderSteps.checkStatusCode(200);
        folderSteps.checkThatJsonContains(nameOfFirstFolder , "data.items[0].name");
    }
}
