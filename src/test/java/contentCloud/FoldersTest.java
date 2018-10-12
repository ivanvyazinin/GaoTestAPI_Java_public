package test.java.contentCloud;

import io.qameta.allure.*;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;

@Epic("Content Cloud")
@Feature("Editor creates and works with folder")
public class FoldersTest extends SuperTest {
    private CommonSteps steps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private Folder testFolder;

    @BeforeClass
    public void prepareSteps(){
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();

        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testFolder = new Folder(context.getTestFolder());
    }

    @Test
    @Story("Editor creates folder")
    public void createFolderInDaRoot(){
        steps.createEntity(
                new Folder(ROOT_FOLDER));
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Editor creates folder")
    public void createFolderInDaFolder(){
        steps.createEntity(testFolder);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Editor creates folder")
    public void createFolderWithSameName() {
        steps.createEntity(testFolder);
        steps.createEntity(testFolder);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS,PATH_ERROR);
    }

    @Test
    @Story("Editor renames folder")
    public void editFolderName(){
        testFolder = steps.createEntity(testFolder);

        testFolder.name = "Changed " + testFolder.name;
        steps.editEntity(testFolder);
        steps.checkThatBodyHasValue(testFolder.name);
    }

    @Test
    @Story("Editor moves folder in the hierarchy")
    public void moveFolder() {
        testFolder = steps.createEntity(testFolder);
        Folder destination = steps.createEntity(
                new Folder(context.getTestFolder()));

        testFolder.parentFolder = destination.id;
        steps.editEntity(testFolder);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(destination.id);
    }

    @Test
    public void getFolders() {
        steps.getEntites(Folder.class, Folder.url);
        steps.checkStatusCode(200);
    }

    @Test(groups = "Slow")
    @Story("Editor creates folder")
    @Description("Cannot create a hierarchy with 51 folder")
    public void createFiftyFolders() {
        testFolder = steps.createEntity(testFolder);

        for(int i=0; i<47; i++){
            testFolder = steps.createEntity(
                    new Folder(testFolder.id));
        }

        steps.createEntity(
                new Folder(testFolder.id));
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("5f4f2d2f-b6d0-46e8-a1f3-26fc1d0cb5c6",PATH_ERROR);
    }

    @Test
    @Story("Editor deletes folder")
    @Description("Delete empty folder")
    public void deleteEmptyFolder() {
        testFolder = steps.createEntity(testFolder);

        steps.deleteEntity(testFolder);
        steps.checkStatusCode(204);
        steps.getEntity(testFolder);
        steps.checkStatusCode(404);
    }


    @Test
    @Story("Editor deletes folder")
    @Description("Delete folder with CI, screen and block")
    public void deleteFolderWithScreenBlockCI(){
        testFolder = steps.createEntity(testFolder);
        steps.createEntity(
                new Screen(testFolder.id));
        steps.createEntity(
                new ContentItem(testFolder.id));
        steps.createEntity(new Paragraph(testFolder.id, context.getLevel()));

        steps.deleteEntity(testFolder);
        steps.checkStatusCode(204);
    }

    @Test
    @Story("Editor deletes folder")
    @Description("Delete folder with screen and block into it")
    public void deleteFolderWithScreenAndBlock(){
        testFolder = steps.createEntity(testFolder);
        screenSteps.getScreenWithBlock(
                new Screen(testFolder.id),
                new Paragraph(testFolder.id, context.getLevel()));

        steps.deleteEntity(testFolder);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("fcbd2e23-deae-4e43-9634-adeadf5d4cbb",PATH_ERROR);
    }

    @Test
    @Story("Editor deletes folder")
    @Description("Delete folder with CI and screen into it")
    public void deleteFolderWithCIwithScreen(){
        testFolder = steps.createEntity(testFolder);
        Screen testScreen = steps.createEntity(new Screen(testFolder.id));
        contentItemSteps.createContentItemWithScreen(new ContentItem(testFolder.id),testScreen);

        steps.deleteEntity(testFolder);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("1f8f36c5-c5c1-4a8d-982d-5b467ea36bfd",PATH_ERROR);
    }
}
