package test.java.publications;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Content Cloud")
@Feature("Editor works with content publications")
@Story("Editor opens CI that used in content publications")
public class ContentItemInPublicationTest extends SuperTest {
    private CommonSteps steps;
    private ContentItem testContentItem;
    private ContentPublication testContentPublication;
    private ContentItemSteps contentItemSteps;
    private Screen testScreen;

    @BeforeClass
    public void prepare() {
        steps = new CommonSteps();
        contentItemSteps = new ContentItemSteps();
        testContentItem = steps.copyEntity(commonObjects.getValidCI(), commonObjects.getTestFolder().id);

        testContentPublication = new ContentPublication(context);
        testContentPublication.contentItems.add(testContentItem.id);
        testContentPublication = steps.createEntity(testContentPublication);

        testScreen = steps.createEntity(
                new Screen(commonObjects.getTestFolder().id));
    }

    @Test(priority = 0)
    public void editCIfromPublication(){
        testContentItem.name = "changed" + testContentItem.name;
        steps.editEntity(testContentItem);
        steps.checkStatusCode(400);

        contentItemSteps.placeScreenIntoConstructor(testContentItem, testScreen);
        contentItemSteps.checkStatusCode(400);
    }

    @Test (priority = 1)
    public void deleteCIfromPublication(){
        steps.deleteEntity(testContentItem);
        steps.checkStatusCode(400);
    }

    @Test (priority = 2)
    public void editCIfromDeletedPublication(){
        steps.deleteEntity(testContentPublication);
        testContentItem.name = "changed" + testContentItem.name;
        steps.editEntity(testContentItem);
        steps.checkStatusCode(200);

        contentItemSteps.placeScreenIntoConstructor(testContentItem, testScreen);
        contentItemSteps.checkStatusCode(201);
    }

    @Test(priority = 3)
    public void deleteCIfromDeletedPublication(){
        steps.deleteEntity(testContentItem);
        steps.checkStatusCode(204);
    }

}
