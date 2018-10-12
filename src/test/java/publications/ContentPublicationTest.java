package test.java.publications;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Publications")
@Feature("CRUD Publications")
@Story("CRUD Content Publications")
public class ContentPublicationTest extends SuperTest {
    private CommonSteps steps;
    private ContentItem testContentItem;
    private ContentPublication testContentPublication;

    @BeforeClass
    public void prepare() {
        steps = new CommonSteps();
        testContentItem = commonObjects.getValidCI();
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentPublication = new ContentPublication();
        testContentPublication.name = getRandomTextField("publication name");
        testContentPublication.description = getRandomTextField("publication description");
        testContentPublication.author = getRandomTextRandomLength(160);
        testContentPublication.licenseNotes = getRandomTextRandomLength(1024);
        testContentPublication.sourceLink = "http://www.ta.com";
        testContentPublication.eqf = context.getEqf();
        testContentPublication.level = context.getLevel();
        testContentPublication.language = context.getLanguage();
        testContentPublication.zone = context.getZone();
        testContentPublication.license = context.getLicence();
        testContentPublication.isco.add(context.getIsco());
        testContentPublication.skills.add(context.getSkill());
        testContentPublication.studies.add(context.getStudy());

        testContentPublication.contentItems.add(testContentItem.id);
    }

    @Test
    public void createPublication(){
        steps.createEntity(testContentPublication);
        steps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithoutDescription(){
        testContentPublication.description = null;

        steps.createEntity(testContentPublication);
        steps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithNotValidName(){
        testContentPublication.name = getRandomText(161);

        steps.createEntity(testContentPublication);
        steps.checkStatusCode(400);
    }

    @Test
    public void deletePublication(){
        testContentPublication = steps.createEntity(testContentPublication);

        steps.deleteEntity(testContentPublication);
        steps.checkStatusCode(204);
        steps.getEntity(testContentPublication);
        steps.checkThatBodyHasValue("\"status\":4");
    }

    @Test
    @Description("Editor cannot edit or delete Publication on moderation")
    public void editPublicationOnModeration(){
        testContentPublication = steps.createEntity(testContentPublication);
        testContentPublication.status=1;
        //TODO bad boi
        testContentPublication.contentItems = null;
        steps.editEntity(testContentPublication);
        testContentPublication.name = getRandomTextField("changed publication name");
        steps.editEntity(testContentPublication);
        steps.checkStatusCode(400);
        steps.checkThatBodyHasValue("a2a2c620-bc74-4f89-8615-8ec5c3b83a71");
        steps.deleteEntity(testContentPublication);
        steps.checkStatusCode(400);
        steps.checkThatBodyHasValue("20bb7077-927f-434f-ba72-c2889bb41dab");
    }
}
