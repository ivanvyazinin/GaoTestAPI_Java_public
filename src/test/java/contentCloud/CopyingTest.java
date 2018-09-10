package test.java.contentCloud;

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

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Lists.getRandomItem;

public class CopyingTest extends CommonCloudTest{
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
                new Folder(FOLDER_FOR_TESTS));
    }

    @Test
    public void copyReusableBlock() {
        Paragraph paragraph = blockSteps.getReusableBlock(
                new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id));
        steps.copyEntity(paragraph,destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(paragraph.id);
        steps.checkThatBodyHasValue(paragraph.paragraph);
    }

    @Test
    public void copyJoinPictureAndSentenceBlock() {
        JoinSentencePicture testBlock = blockSteps.getJoinSentencePictureBlock(new Folder(FOLDER_FOR_TESTS), getRandomItem(level).id);
        JoinSentencePicture testBlockCopy = steps.copyEntity(testBlock, destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(testBlock.id);
        steps.checkThatBodyHasValue(testBlock.name);
        steps.getEntity(testBlockCopy);
    }

    @Test
    public void copyScreen(){
        Screen originalScreen = steps.createEntity(
                new Screen(FOLDER_FOR_TESTS));
        steps.copyEntity(originalScreen, destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(originalScreen.id);
        steps.checkThatBodyHasValue(originalScreen.name);
    }

    @Test
    public void copyScreenWithSimpleBlock(){
        Screen originalScreen = screenSteps.getScreenWithBlock(
                new Screen(FOLDER_FOR_TESTS), new Title());

        steps.copyEntity(originalScreen, destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(originalScreen.id);
        steps.checkThatBodyHasValue(originalScreen.name);
    }

    @Test
    public void copyScreenWithReusableBlock(){
        Paragraph paragraph = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        Screen originalScreen = screenSteps.getScreenWithBlock(
                new Screen(FOLDER_FOR_TESTS), paragraph);
        paragraph.id = screenSteps.getScreenBlocks(originalScreen).items.get(0).id;

        Screen screenCopy = steps.copyEntity(originalScreen, destinationFolder.id);
        screenSteps.getScreenBlocks(screenCopy);
        screenSteps.checkThatBodyHasNotValue(paragraph.id);
        screenSteps.checkThatBodyHasValue(paragraph.name);
        screenSteps.checkThatJsonContains(false,"data.items[0].reusable");
    }

    @Test
    public void copyContentItem(){
        ContentItem originalContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        steps.copyEntity(originalContentItem,destinationFolder.id);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasNotValue(originalContentItem.id);
        steps.checkThatBodyHasValue(originalContentItem.name);
    }

    @Test
    public void copyContentItemWithScreenAndBlock(){
        Paragraph paragraph = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        Screen originalScreen = screenSteps.getScreenWithBlock(
                new Screen(FOLDER_FOR_TESTS), paragraph);
        paragraph.id = screenSteps.getScreenBlocks(originalScreen).items.get(0).id;

        ContentItem originalContentItem = steps.createEntity(
                new ContentItem(FOLDER_FOR_TESTS));
        contentItemSteps.placeScreenIntoConstructor(originalContentItem, originalScreen);

        ContentItem contentItemCopy = steps.copyEntity(originalContentItem, destinationFolder.id);

        contentItemSteps.getContentItemConstructor(contentItemCopy);
        contentItemSteps.checkThatBodyHasNotValue(originalScreen.id);
        contentItemSteps.checkThatBodyHasValue(originalScreen.name);

        //TODO awful
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
