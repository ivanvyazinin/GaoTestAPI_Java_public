package test.java.contentCloud;

import main.java.api.contentCloud.blocks.BlocsAPI;
import main.java.entities.contentCloud.constructor.Item;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.blocks.theory.Title;
import main.java.steps.ContentItemSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.BlockSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Lists.getRandomItem;

public class CopyingTest extends CommonCloudTest{
    private BlocsAPI blocsAPI;

    private BlockSteps blockSteps;
    private FolderSteps folderSteps;
    private ScreenSteps screenSteps;
    private ContentItemSteps contentItemSteps;

    @BeforeClass
    public void prepareSteps(){
        blockSteps = new BlockSteps();
        folderSteps = new FolderSteps();
        blocsAPI = new BlocsAPI();
        screenSteps = new ScreenSteps();
        contentItemSteps = new ContentItemSteps();
    }

    @Test
    public void copyReusableBlock() {
        Folder destinationFolder = new Folder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(destinationFolder);

        Paragraph paragraph = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        blockSteps.getReusableBlock(paragraph);

        newResponse = blocsAPI.copy(destinationFolder.id,paragraph.id);

        checkStatusCode(200);
        checkThatBodyHasNotValue(paragraph.id);
        checkThatBodyHasValue(paragraph.paragraph);
    }

    @Test
    public void copyScreen(){
        Folder destinationFolder = new Folder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(destinationFolder);

        Screen originalScreen = new Screen(FOLDER_FOR_TESTS);
        screenSteps.createScreen(originalScreen);

        screenSteps.copyScreen(originalScreen, destinationFolder.id);
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasNotValue(originalScreen.id);
        screenSteps.checkThatBodyHasValue(originalScreen.name);
    }

    @Test
    public void copyScreenWithSimpleBlock(){
        Folder destinationFolder = new Folder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(destinationFolder);

        Screen originalScreen = new Screen(FOLDER_FOR_TESTS);
        screenSteps.getScreenWithBlock(originalScreen,new Title());

        screenSteps.copyScreen(originalScreen, destinationFolder.id);

        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasNotValue(originalScreen.id);
        screenSteps.checkThatBodyHasValue(originalScreen.name);
    }

    @Test
    public void copyScreenWithReusableBlock(){
        Folder destinationFolder = new Folder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(destinationFolder);

        Paragraph paragraph = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        Screen originalScreen = screenSteps.getScreenWithBlock(new Screen(FOLDER_FOR_TESTS), paragraph);
        paragraph.id = screenSteps.getScreenBlocks(originalScreen).items.get(0).id;

        Screen screenCopy = screenSteps.copyScreen(originalScreen, destinationFolder.id);
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasNotValue(originalScreen.id);
        screenSteps.checkThatBodyHasValue(originalScreen.name);

        screenSteps.getScreenBlocks(screenCopy.id);
        screenSteps.checkThatBodyHasNotValue(paragraph.id);
        screenSteps.checkThatBodyHasValue(paragraph.name);
        screenSteps.checkThatJsonContains(false,"data.items[0].reusable");
    }

    @Test
    public void copyContentItem(){
        Folder destinationFolder = new Folder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(destinationFolder);

        ContentItem originalContentItem = new ContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.createContentItem(originalContentItem);

        contentItemSteps.copyContentItem(originalContentItem, destinationFolder.id);
        contentItemSteps.checkStatusCode(200);
        contentItemSteps.checkThatBodyHasNotValue(originalContentItem.id);
        contentItemSteps.checkThatBodyHasValue(originalContentItem.name);
    }

    @Test
    public void copyContentItemWithScreenAndBlock(){
        Folder destinationFolder = new Folder(FOLDER_FOR_TESTS);
        folderSteps.createFolder(destinationFolder);

        Paragraph paragraph = new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id);
        Screen originalScreen = screenSteps.getScreenWithBlock(new Screen(FOLDER_FOR_TESTS), paragraph);
        paragraph.id = screenSteps.getScreenBlocks(originalScreen).items.get(0).id;

        ContentItem originalContentItem = new ContentItem(FOLDER_FOR_TESTS);
        contentItemSteps.createContentItem(originalContentItem);
        contentItemSteps.placeScreenIntoConstructor(originalContentItem, originalScreen.id);

        ContentItem contentItemCopy = contentItemSteps.copyContentItem(originalContentItem, destinationFolder.id);
        contentItemSteps.checkStatusCode(200);
        contentItemSteps.checkThatBodyHasNotValue(originalContentItem.id);
        contentItemSteps.checkThatBodyHasValue(originalContentItem.name);

        contentItemSteps.getContentItemConstructor(contentItemCopy);
        contentItemSteps.checkThatBodyHasNotValue(originalScreen.id);
        contentItemSteps.checkThatBodyHasValue(originalScreen.name);

        //TODO awful
        for (Item item : contentItemSteps.getContentItemConstructor(contentItemCopy).items){
            if(item.type.equals("screen")){
                if(item.screen.name.equals(originalScreen.name)){

                    screenSteps.getScreenBlocks(item.screen.id);
                    screenSteps.checkThatBodyHasNotValue(paragraph.id);
                    screenSteps.checkThatBodyHasValue(paragraph.name);
                    screenSteps.checkThatJsonContains(false,"data.items[0].reusable");

                }
            }
        }
    }
}
