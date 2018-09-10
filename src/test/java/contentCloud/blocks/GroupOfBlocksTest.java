package test.java.contentCloud.blocks;

import main.java.entities.contentCloud.blocks.GroupOfBlocks;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Lists.getRandomItem;


public class GroupOfBlocksTest extends CommonCloudTest {
    private CommonSteps steps;
    private ContentItemSteps contentItemSteps;
    private GroupOfBlocks testGroupOfBlocks;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        contentItemSteps = new ContentItemSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testGroupOfBlocks = new GroupOfBlocks();
        testGroupOfBlocks.color = 1;
        testGroupOfBlocks.name = getRandomTextField("GroupOfBlocks");
        testGroupOfBlocks.description = getRandomTextField("GroupOfBlocks description");
    }

    @Test
    public void createEmptyGroupOfBlocks(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);
    }

    @Test
    public void createGroupOfBlocksWithBlocks(){
        ContentItem testContentItem = contentItemSteps.getCIWithValidConstructor(
                new ContentItem(FOLDER_FOR_TESTS),
                new Screen(FOLDER_FOR_TESTS),
                new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id));

        testGroupOfBlocks.contentItem = testContentItem.id;
        testGroupOfBlocks.blocks.add(testContentItem.screens.get(0).blocks.get(0).id);

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);
    }

    @Test
    public void createGroupOfBlocksWithoutDescription(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;
        testGroupOfBlocks.description = null;

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);
    }

    @Test
    public void createGroupOfBlocksWithSameName(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;

        steps.createEntity(testGroupOfBlocks);
        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);
    }

    @Test
    public void createGroupOfBlocksTooLongName(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;
        testGroupOfBlocks.name = getRandomText(161);

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    public void createGroupOfBlocksTooLongDescription(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;
        testGroupOfBlocks.description = getRandomText(1025);

        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    public void createGroupOfBlocksWithBlockNotFromCI(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;

        testGroupOfBlocks.blocks.add(steps.createEntity(
                new Paragraph(FOLDER_FOR_TESTS, getRandomItem(level).id)).id);
        steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(400);
    }

    @Test
    public void editGroupOfBlocks(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;

        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        testGroupOfBlocks.color = 2;
        testGroupOfBlocks = steps.editEntity(testGroupOfBlocks);
        steps.checkStatusCode(200);
    }

    @Test
    public void deleteGroupOfBlocks(){
        ContentItem testContentItem = steps.createEntity(new ContentItem(FOLDER_FOR_TESTS));
        testGroupOfBlocks.contentItem = testContentItem.id;

        testGroupOfBlocks = steps.createEntity(testGroupOfBlocks);
        steps.checkStatusCode(201);

        steps.deleteEntity(testGroupOfBlocks);
        steps.checkStatusCode(204);
    }

}
