package test.java.contentCloud.blocks;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.GroupOfBlocks;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import main.java.steps.blocks.BlockSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Content Cloud")
@Feature("Editor creates and works with Group of blocks")
public class GroupOfBlocksTest extends SuperTest {
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
    @Story("Editor adds a Group of blocks")
    public void createEmptyGroupOfBlocks(){
        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        blockSteps.getScreensOfGroupBlocks(testGroupOfBlocks.id);
        blockSteps.checkStatusCode(200);
        blockSteps.checkThatJsonContains(0, "data.count");
    }

    @Test
    @Story("Editor adds a Group of blocks")
    public void createGroupOfBlocksWithBlocks(){
        ContentItem testContentItem = contentItemSteps.getCIWithValidConstructor(
                new ContentItem(context.getTestFolder()),
                new Screen(context.getTestFolder()),
                new Paragraph());

        testGroupOfBlocks.contentItem = testContentItem.id;
        testGroupOfBlocks.blocks.add(testContentItem.screens.get(0).blocks.get(0).id);

        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        blockSteps.getScreensOfGroupBlocks(testGroupOfBlocks.id);
        blockSteps.checkStatusCode(200);
        blockSteps.checkThatJsonContains(1, "data.count");
    }

    @Test
    @Story("Editor adds a Group of blocks")
    public void createGroupOfBlocksWithoutDescription(){
        testGroupOfBlocks.description = null;

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Editor adds a Group of blocks")
    public void createGroupOfBlocksWithSameName(){
        steps.createEntity(testGroupOfBlocks);
        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Editor adds a Group of blocks")
    public void createGroupOfBlocksTooLongName(){
        testGroupOfBlocks.name = getRandomText(161);

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Editor adds a Group of blocks")
    public void createGroupOfBlocksTooLongDescription(){
        testGroupOfBlocks.description = getRandomText(1025);

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Editor adds a Group of blocks")
    public void createGroupOfBlocksWithBlockNotFromCI(){
        testGroupOfBlocks.blocks.add(steps.createEntity(
                new Paragraph()).id);
        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Editor edits a Group of blocks")
    public void editGroupOfBlocks(){
        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        testGroupOfBlocks.color = 2;
        testGroupOfBlocks = steps.editEntity(testGroupOfBlocks);
        steps.checkStatusCode(200);
    }

    @Test
    @Story("Editor deletes a Group of blocks")
    public void deleteGroupOfBlocks(){
        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        steps.deleteEntity(testGroupOfBlocks);
        steps.checkStatusCode(204);
    }

}
