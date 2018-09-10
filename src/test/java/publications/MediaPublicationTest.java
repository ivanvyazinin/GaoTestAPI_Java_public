package test.java.publications;

import main.java.entities.publications.MediaPublication;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.*;

import static main.java.properties.Constants.EMBED_DIRECTORY;
import static main.java.properties.Constants.EMBED_FILE;
import static main.java.properties.Constants.EMBED_TAG;
import static main.java.properties.Context.FILE_PATH_IMAGE;
import static main.java.utils.Generator.getRandomTextField;

public class MediaPublicationTest extends SuperPublicationTest {
    private CommonSteps steps;
    private MediaPublication testMediaPublication;
    private FilesSteps filesSteps;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();

        String [][] parameters = {
                {"embed",EMBED_TAG},
                {"embed",EMBED_DIRECTORY},
                {"embed",EMBED_FILE}
        };
        steps.api.setRequestParameters(parameters);
    }

    @BeforeMethod
    public void prepareEntity(){
        testMediaPublication = new MediaPublication();
        testMediaPublication.name = getRandomTextField("publication name");
        testMediaPublication.description = getRandomTextField("publication description");
        testMediaPublication.language = language.get(0).id;
        testMediaPublication.license = license.get(0).id;
        testMediaPublication.studies.add(fieldsOfStudy.get(1).id);
    }

    @Test
    public void createPublicationWithoutFile(){
        steps.createEntity(testMediaPublication);
        steps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithFile(){
        testMediaPublication.files.add(filesSteps.uploadFile(FILE_PATH_IMAGE));

        testMediaPublication = steps.createEntity(testMediaPublication);
        steps.getEntity(testMediaPublication);
        steps.checkThatBodyHasValue("\"status\":5");
    }

    @Test
    public void createPublicationWithoutDescription(){
        testMediaPublication.description = null;

        steps.createEntity(testMediaPublication);
        steps.checkStatusCode(201);
    }

    @Test
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
        steps.checkStatusCode(404);
    }

    @Test
    public void editPublication(){
        testMediaPublication = steps.createEntity(testMediaPublication);

        testMediaPublication.name = getRandomTextField("changed publication name");
        steps.editEntity(testMediaPublication);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("changed publication name");
    }
}
