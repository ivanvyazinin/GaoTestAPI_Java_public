package test.java.contentCloud.blocks;

import io.qameta.allure.Description;
import main.java.entities.contentCloud.blocks.GroupOfBlocks;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.BlockSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextField;


public class GetScreensFromGroupOfBlocksTest extends SuperTest {
    private CommonSteps steps;
    private ContentItemSteps contentItemSteps;
    private GroupOfBlocks testGroupOfBlocks;
    private ContentItem testContentItem;
    private BlockSteps blockSteps;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        blockSteps = new BlockSteps();
        contentItemSteps = new ContentItemSteps();
        testContentItem = steps.createEntity(new ContentItem(context.getTestFolder()));
    }

    @BeforeMethod
    public void prepareEntity(){
        testGroupOfBlocks = new GroupOfBlocks();
        testGroupOfBlocks.color = 1;
        testGroupOfBlocks.name = getRandomTextField("GroupOfBlocks");
        testGroupOfBlocks.description = getRandomTextField("GroupOfBlocks description");
        testGroupOfBlocks.contentItem = testContentItem.id;
    }

    @Test
    @Description("Get screens of blocks Group, where one block used in a few screens")
    public void createGroupOfBlocks(){
        ScreenSteps screenSteps = new ScreenSteps();
        Paragraph testBlock = new Paragraph(context.getTestFolder(), context.getLevel());
        testBlock = steps.createEntity(testBlock);

        ContentItem testContentItem = contentItemSteps.getCIWithValidConstructor(
                new ContentItem(context.getTestFolder()),
                new Screen(context.getTestFolder()),
                testBlock);

        Screen testScreen = screenSteps.getScreenWithBlock(
                new Screen(context.getTestFolder()),
                testBlock);

        contentItemSteps.placeScreenIntoConstructor(testContentItem, testScreen);

        testGroupOfBlocks.contentItem = testContentItem.id;
        testGroupOfBlocks.blocks.add(testContentItem.screens.get(0).blocks.get(0).id);

        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        blockSteps.getScreensOfGroupBlocks(testGroupOfBlocks.id);
        blockSteps.checkStatusCode(200);
        blockSteps.checkThatJsonContains(2, "data.count");
    }
}
