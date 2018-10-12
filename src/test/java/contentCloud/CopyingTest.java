package test.java.contentCloud;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.JoinSentencePicture;
import main.java.entities.contentCloud.constructor.Item;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.blocks.theory.Title;
import main.java.steps.*;
import main.java.steps.blocks.BlockSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Content Cloud")
@Feature("Editor copies stuff")
public class CopyingTest extends SuperTest {
    private BlockSteps blockSteps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;
    private CommonSteps steps;

    private Folder destinationFolder;

    @BeforeClass
    public void prepareSteps(){
        blockSteps = new BlockSteps();
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();

        steps = new CommonSteps();
        destinationFolder = steps.createEntity(
                new Folder(context.getTestFolder()));
    }

    @Test
    @Story("Editor copies block in a folder")
    public void copyReusableBlock() {
        Paragraph paragraph = steps.createEntity(
                new Paragraph(context.getTestFolder(), context.getLevel()));
        steps.copyEntity(paragraph,destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(paragraph.id);
        steps.checkThatBodyHasValue(paragraph.paragraph);
    }

    @Test
    @Story("Editor copies block in a folder")
    public void copyJoinPictureAndSentenceBlock() {
        JoinSentencePicture testBlock = blockSteps.getJoinSentencePictureBlock(context.getTestFolder(), context);
        JoinSentencePicture testBlockCopy = steps.copyEntity(testBlock, destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(testBlock.id);
        steps.checkThatBodyHasValue(testBlock.name);
        steps.getEntity(testBlockCopy);
    }

    @Test
    @Story("Editor copies screen")
    public void copyScreen(){
        Screen originalScreen = steps.createEntity(
                new Screen(context.getTestFolder()));
        steps.copyEntity(originalScreen, destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(originalScreen.id);
        steps.checkThatBodyHasValue(originalScreen.name);
    }

    @Test
    @Story("Editor copies screen")
    public void copyScreenWithSimpleBlock(){
        Screen originalScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()), new Title());

        steps.copyEntity(originalScreen, destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(originalScreen.id);
        steps.checkThatBodyHasValue(originalScreen.name);
    }

    @Test
    @Story("Editor copies screen")
    public void copyScreenWithReusableBlock(){
        Paragraph paragraph = new Paragraph(context.getTestFolder(), context.getLevel());
        Screen originalScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()), paragraph);
        paragraph.id = screenSteps.getScreenBlocks(originalScreen).getItem(0).id;

        Screen screenCopy = steps.copyEntity(originalScreen, destinationFolder.id);
        screenSteps.getScreenBlocks(screenCopy);
        screenSteps.checkThatBodyHasNotValue(paragraph.id);
        screenSteps.checkThatBodyHasValue(paragraph.name);
        screenSteps.checkThatJsonContains(false,"data.items[0].reusable");
    }

    @Test
    @Story("Editor copies content item")
    public void copyContentItem(){
        ContentItem originalContentItem = steps.createEntity(new ContentItem(context.getTestFolder()));
        steps.copyEntity(originalContentItem,destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(originalContentItem.id);
        steps.checkThatBodyHasValue(originalContentItem.name);
    }

    @Test
    @Story("Editor copies content item")
    public void copyContentItemWithScreenAndBlock(){
        Paragraph paragraph = new Paragraph(context.getTestFolder(), context.getLevel());
        Screen originalScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()), paragraph);
        paragraph.id = screenSteps.getScreenBlocks(originalScreen).getItem(0).id;

        ContentItem originalContentItem = steps.createEntity(
                new ContentItem(context.getTestFolder()));
        contentItemSteps.placeScreenIntoConstructor(originalContentItem, originalScreen);

        ContentItem contentItemCopy = steps.copyEntity(originalContentItem, destinationFolder.id);

        contentItemSteps.getContentItemConstructor(contentItemCopy);
        contentItemSteps.checkThatBodyHasNotValue(originalScreen.id);
        contentItemSteps.checkThatBodyHasValue(originalScreen.name);

        //TODO bad boi
        for (Item item : contentItemSteps.getContentItemConstructor(contentItemCopy).items){
            if(item.type.equals("screen")){
                if(item.screen.name.equals(originalScreen.name)){
                    screenSteps.getScreenBlocks(item.screen);
                    screenSteps.checkThatBodyHasNotValue(paragraph.id);
                    screenSteps.checkThatBodyHasValue(paragraph.name);
                    screenSteps.checkThatJsonContains(false,"data.items[0].reusable");
                }
            }
        }
    }
}
