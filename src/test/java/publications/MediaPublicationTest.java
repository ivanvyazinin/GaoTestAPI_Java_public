package test.java.publications;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.publications.MediaPublication;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.*;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Publications")
@Feature("CRUD Publications")
@Story("CRUD Media Publications")
public class MediaPublicationTest extends SuperTest {
    private CommonSteps steps;
    private MediaPublication testMediaPublication;
    private FilesSteps filesSteps;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();

        steps.api.setRequestParameters(new String[][] {
                {"embed",EMBED_TAG},
                {"embed",EMBED_FILE}
        });
    }

    @BeforeMethod
    public void prepareEntity(){
        testMediaPublication = new MediaPublication();
        testMediaPublication.name = getRandomTextField("publication name");
        testMediaPublication.description = getRandomTextField("publication description");
        testMediaPublication.author = getRandomTextRandomLength(160);
        testMediaPublication.licenseNotes = getRandomTextRandomLength(1024);
        testMediaPublication.sourceLink = "http://www.TA.com";
        testMediaPublication.license = context.getLicence();
        testMediaPublication.language = context.getLanguage();
        testMediaPublication.studies.add(context.getStudy());
    }

    @Test
    @Description("Can create publication Without File")
    public void createPublicationWithoutFile(){
        steps.createEntity(testMediaPublication);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Can create publication With File")
    public void createPublicationWithFile(){
        testMediaPublication.files.add(filesSteps.uploadFile(FILE_PATH_IMAGE));

        testMediaPublication = steps.createEntity(testMediaPublication);
        steps.getEntity(testMediaPublication);
        steps.checkThatBodyHasValue("\"status\":5");
    }

    @Test
    @Description("Can create publication Without field Description")
    public void createPublicationWithoutDescription(){
        testMediaPublication.description = null;

        steps.createEntity(testMediaPublication);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Cannot create publication without field Study")
    public void createPublicationWithoutFieldOfStudy(){
        testMediaPublication.studies.clear();

        steps.createEntity(testMediaPublication);
        steps.checkStatusCode(400);
        steps.checkThatBodyHasValue("bef8e338-6ae5-4caf-b8e2-50e7b0579e69");
    }

    @Test
    public void deletePublication(){
        testMediaPublication = steps.createEntity(testMediaPublication);

        steps.deleteEntity(testMediaPublication);
        steps.checkStatusCode(204);
        steps.getEntity(testMediaPublication);
        steps.checkThatBodyHasValue("\"status\":4");
    }

    @Test
    @Description("Editor cannot edit or delete publication on moderation")
    public void editPublicationOnModeration(){
        testMediaPublication.files.add(filesSteps.uploadFile(FILE_PATH_IMAGE));
        testMediaPublication = steps.createEntity(testMediaPublication);
        testMediaPublication.status=1;
        steps.editEntity(testMediaPublication);
        testMediaPublication.name = getRandomTextField("changed publication name");
        steps.editEntity(testMediaPublication);
        steps.checkStatusCode(400);
        steps.deleteEntity(testMediaPublication);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Publication cannot be sent to moderation without file")
    public void sentPublicationOnModerationWithoutFile(){
        testMediaPublication = steps.createEntity(testMediaPublication);
        testMediaPublication.status=1;
        steps.editEntity(testMediaPublication);
        steps.checkStatusCode(400);
        steps.checkThatBodyHasValue("1df8a282-54c6-4009-ae2e-400eb301469c");
    }
}
