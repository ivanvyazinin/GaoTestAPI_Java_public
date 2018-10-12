package test.java.contentCloud;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.AbstractBlock;
import main.java.entities.contentCloud.blocks.theory.Title;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import main.java.steps.ScreenSteps;
import test.java.SuperTest;

import java.util.ArrayList;

import static main.java.properties.Constants.ERROR_RESOURCE_ALREADY_EXISTS;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Content Cloud")
@Feature("Editor creates and works with screen")
public class ScreensTest extends SuperTest {
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private CommonSteps steps;

    private Screen testScreen;

    @BeforeClass
    public void prepareSteps(){
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testScreen = new Screen(context.getTestFolder());
    }

    @Test
    @Story("Editor creates new screen")
    public void createScreenInDaFolder() {
        steps.createEntity(testScreen);
        steps.checkStatusCode(201);
        steps.checkThatBodyHasValue(context.getTestFolder());
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor creates new screen")
    public void createScreenWithoutDescription() {
        testScreen.description=null;
        steps.createEntity(testScreen);
        steps.checkStatusCode(201);
    }


    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor creates new screen")
    public void createScreenWithSameName() {
        steps.createEntity(testScreen);
        steps.createEntity(testScreen);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_RESOURCE_ALREADY_EXISTS, PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor edits screen parameters")
    public void editScreenNameAndDescription() {
        testScreen = steps.createEntity(testScreen);

        testScreen.name = "Changed" + testScreen.name;
        testScreen.description = "Changed" +testScreen.description;

        steps.editEntity(testScreen);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(testScreen.name);
        steps.checkThatBodyHasValue(testScreen.description);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor deletes screen")
    public void deleteScreen(){
        testScreen = steps.createEntity(testScreen);

        steps.deleteEntity(testScreen);
        steps.checkStatusCode(204);

        steps.getEntity(testScreen);
        steps.checkStatusCode(404);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor deletes screen")
    public void deleteScreenWithBlock() {
        testScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()),
                new Paragraph());

        steps.deleteEntity(testScreen);
        steps.checkStatusCode(204);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor deletes screen")
    public void deleteScreenUsedInCI() {
        testScreen = steps.createEntity(
                new Screen(context.getTestFolder()));
        contentItemSteps.createContentItemWithScreen(
                new ContentItem(context.getTestFolder()),testScreen);

        steps.deleteEntity(testScreen);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("96afdf5d-b04f-428e-8144-92e2dea2cdfc",PATH_ERROR);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor moves screen in the hierarchy")
    public void moveScreen(){
        Folder destination = steps.createEntity(
                new Folder(context.getTestFolder()));
        testScreen = steps.createEntity(testScreen);

        testScreen.parentFolder = destination.id;

        steps.editEntity(testScreen);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue(destination.id);
    }

    @Test (dependsOnMethods = "createScreenInDaFolder")
    @Story("Editor copies screen")
    public void copyEmptyScreenTwice(){
        Folder destination = steps.createEntity(
                new Folder(context.getTestFolder()));
        testScreen = steps.createEntity(testScreen);

        steps.copyEntity(testScreen,destination.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(testScreen.id);

        steps.copyEntity(testScreen,destination.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(testScreen.id);
        steps.checkThatBodyHasValue(testScreen.name+"(1)");
    }

    @Test ()
    @Story("Editor copies screen")
    public void copyScreenWithBlock(){
        Paragraph testBlock = new Paragraph();
        Folder destination = steps.createEntity(
                new Folder(context.getTestFolder()));
        testScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()), testBlock);
        testBlock.id = screenSteps.getScreenBlocks(testScreen).getItem(0).id;

        Screen screenCopy = steps.copyEntity(testScreen,destination.id);
        steps.checkStatusCode(200);

        screenSteps.getScreenBlocks(screenCopy);
        screenSteps.checkThatJsonContains(1,"data.count");
        screenSteps.checkThatBodyHasNotValue(testBlock.id);
        screenSteps.checkThatBodyHasValue(testBlock.paragraph);
    }

    @Test
    @Story("Editor edits screen structure")
    public void blockNameUniquenessValidation(){
        String paragraphName = getRandomTextField("Paragraph");

        steps.createEntity(
                new Paragraph(context.getTestFolder(), context.getLevel()).setName(paragraphName));

        Paragraph testBlock = new Paragraph();
        testScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()), testBlock);

        testScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()),
                new Paragraph(context.getTestFolder(),context.getLevel()).setId(testBlock.id).setName(paragraphName));

        screenSteps.checkThatBodyHasValue(paragraphName + "(1)");
    }

    @Test
    @Story("Editor edits screen structure")
    public void editScreenStructure(){
        ArrayList<AbstractBlock> screenStructure = new ArrayList(){};
        Paragraph testParagraph = steps.createEntity(
                new Paragraph());

        Title testTitle = new Title();

        Screen testScreen = screenSteps.getScreenWithBlock(
                new Screen(commonObjects.getTestFolder().id), testTitle
        );
        screenSteps.getScreenBlocks(testScreen);
        screenSteps.checkThatBodyHasValue(testTitle.title);

        screenStructure.add(testParagraph);
        screenSteps.updateScreenStructure(testScreen,
                screenStructure);
        screenSteps.getScreenBlocks(testScreen);
        screenSteps.checkThatBodyHasNotValue(testTitle.title);
        screenSteps.checkThatBodyHasValue(testParagraph.paragraph);
    }

}