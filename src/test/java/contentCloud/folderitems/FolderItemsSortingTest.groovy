package test.java.contentCloud.folderitems

import io.qameta.allure.Description
import io.qameta.allure.Feature
import main.java.entities.contentCloud.folderItems.ContentItem
import main.java.entities.contentCloud.folderItems.Folder
import main.java.entities.contentCloud.folderItems.Screen
import main.java.steps.CommonSteps
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import main.java.steps.FolderSteps
import test.java.contentCloud.CommonCloudTest

import static main.java.utils.Generator.getRandomTextField
import static main.java.properties.Context.FOLDER_FOR_TESTS

@Feature("Folder items")
class FolderItemsSortingTest extends CommonCloudTest {
    private CommonSteps steps
    private FolderSteps folderSteps

    private Folder testFolder
    private String nameOfFirstFolder

    @BeforeClass
    @Description("Preparing test data")
    void prepareSteps(){
        steps = new CommonSteps()
        folderSteps = new FolderSteps()

        testFolder = steps.createEntity(new Folder(FOLDER_FOR_TESTS))

        nameOfFirstFolder = getRandomTextField("! first folder")
        steps.createEntity(new Folder(nameOfFirstFolder, testFolder.id))

        for (int i; i<5; i++){
            steps.createEntity(new Folder(testFolder.id));
        }
        for (int i; i<6; i++){
            steps.createEntity(new ContentItem(testFolder.id))
        }
        for (int i; i<6; i++){
            steps.createEntity(new Screen(testFolder.id))
        }
    }

    @Test
    void checkDefaultPaginationLimitPageOne() {

        folderSteps.folderItemsAPI.setRequestParameters(
                [
                        parentFolder:testFolder.id,
                        page: "1",
                        per_page:"16"
                ]
        )

        folderSteps.getFolderItems(null)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(18, "data.count")
        folderSteps.checkItemsNumberInResponse(16)
    }

    @Test
    void checkDefaultPaginationLimitPageTwo() {
        folderSteps.folderItemsAPI.setRequestParameters(
                [
                        parentFolder:testFolder.id,
                        page: "2",
                        per_page:"16"
                ]
        )

        folderSteps.getFolderItems(null)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(18, "data.count")
        folderSteps.checkItemsNumberInResponse(2)
    }

    @Test
    void checkDefaultSorting() {

        folderSteps.folderItemsAPI.resetRequestParameters()

        folderSteps.getFolderItems(null)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(1, "data.items[0].type")
        folderSteps.checkThatJsonContains(3, "data.items[15].type")

    }

    @Test
    void checkSortingByCreatedAt() {
        folderSteps.folderItemsAPI.setRequestParameters(
                [
                        parentFolder:testFolder.id,
                        page   : "1",
                        sorting: "createdAt",
                        order  : "acs",
                        per_page:"16"
                ]
        )

        folderSteps.getFolderItems(null)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(1, "data.items[0].type")
        folderSteps.checkThatJsonContains(3, "data.items[15].type")

    }


    @Test
    void checkSortingByType() {
        folderSteps.folderItemsAPI.setRequestParameters(
                [
                        parentFolder:testFolder.id,
                        page   : "1",
                        sorting: "type",
                        order  : "desc",
                        per_page:"16"
                ]
        )

        folderSteps.getFolderItems(null)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(3, "data.items[0].type")
        folderSteps.checkThatJsonContains(1, "data.items[15].type")

    }

    @Test
    void checkSortingByName(){
        folderSteps.folderItemsAPI.setRequestParameters(
                [
                        parentFolder:testFolder.id,
                        page: "1",
                        sorting:"name",
                        order: "asc",
                        per_page:"16"
                ]
        )

        folderSteps.getFolderItems(null)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(nameOfFirstFolder , "data.items[0].name")
    }
}
