package test.java.contentCloud

import io.qameta.allure.Description
import io.qameta.allure.Feature
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import main.java.steps.ContentItemSteps
import main.java.steps.FolderSteps
import main.java.steps.ScreenSteps
import test.java.SuperTest

import static main.java.utils.Generator.getRandomTextField
import static main.java.properties.Context.FOLDER_FOR_TESTS

@Feature("Folder items")
class FolderItemsSortingTest extends SuperTest {
    private FolderSteps folderSteps
    private ScreenSteps screenSteps
    private ContentItemSteps contentItemSteps

    private String testFolderId
    private String nameOfFirstFolder


    @BeforeClass
    @Description("Preparing test data")
    void prepareSteps(){
        folderSteps = new FolderSteps()
        screenSteps = new ScreenSteps()
        contentItemSteps = new ContentItemSteps()

        folderSteps.createFolder(FOLDER_FOR_TESTS)

        nameOfFirstFolder = getRandomTextField("! first folder")
        testFolderId = folderSteps.testFolder.id

        folderSteps.createFolder(FOLDER_FOR_TESTS)
        for (int i; i<5; i++){
            folderSteps.createFolder(testFolderId)
        }
        for (int i; i<6; i++){
            contentItemSteps.createContentItem(testFolderId)
        }
        for (int i; i<6; i++){
            screenSteps.createScreen(testFolderId)
        }
    }

    @Test
    void checkDefaultPaginationLimitPageOne() {

        folderSteps.api.setRequestParameters(
                [
                        page: "1"
                ]
        )

        folderSteps.getFolderItems(testFolderId)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(18, "data.count")
        folderSteps.checkItemsNumberInResponse(16)
    }

    @Test
    void checkDefaultPaginationLimitPageTwo() {
        folderSteps.api.setRequestParameters(
                [
                        page: "2"
                ]
        )

        folderSteps.getFolderItems(testFolderId)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(18, "data.count")
        folderSteps.checkItemsNumberInResponse(2)
    }

    @Test
    void checkSortingByType() {
        folderSteps.api.setRequestParameters(
                [
                        page   : "1",
                        sorting: "type",
                        order  : "desc"
                ]
        )

        folderSteps.getFolderItems(testFolderId)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(3, "data.items[0].type")
        folderSteps.checkThatJsonContains(1, "data.items[15].type")

    }

    @Test
    void checkSortingByName(){
        folderSteps.api.setRequestParameters(
                [
                        page: "1",
                        sorting:"name",
                        order: "asc"
                ]
        )

        folderSteps.getFolderItems(testFolderId)
        folderSteps.checkStatusCode(200)
        folderSteps.checkThatJsonContains(nameOfFirstFolder , "data.items[0].name")
    }
}