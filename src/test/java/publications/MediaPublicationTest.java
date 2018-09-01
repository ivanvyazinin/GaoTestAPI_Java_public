package test.java.publications;

import main.java.entities.publications.MediaPublication;
import main.java.steps.publications.MediaPublicationSteps;
import org.testng.annotations.*;

import static main.java.utils.Generator.getRandomTextField;

public class MediaPublicationTest extends SuperPublicationTest {
    MediaPublicationSteps mediaPublicationSteps;

    @BeforeClass
    public void prepare(){
        mediaPublicationSteps = new MediaPublicationSteps();
    }

    @BeforeMethod
    public void preparePublication(){
        mediaPublicationSteps.testMediaPublication = new MediaPublication();
    }

    @Test
    public void createPublication(){
        mediaPublicationSteps.testMediaPublication.name = getRandomTextField("publication name");
        mediaPublicationSteps.testMediaPublication.description = getRandomTextField("publication description");
        mediaPublicationSteps.testMediaPublication.language = language.get(0).id;
        mediaPublicationSteps.testMediaPublication.license = license.get(0).id;
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(1).id);

        mediaPublicationSteps.createPublication();
        mediaPublicationSteps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithoutDescription(){
        mediaPublicationSteps.testMediaPublication.name = getRandomTextField("publication name");
        mediaPublicationSteps.testMediaPublication.language = language.get(0).id;
        mediaPublicationSteps.testMediaPublication.license = license.get(0).id;
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(1).id);

        mediaPublicationSteps.createPublication();
        mediaPublicationSteps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithFewFieldOfStudy(){
        mediaPublicationSteps.testMediaPublication.name = getRandomTextField("publication name");
        mediaPublicationSteps.testMediaPublication.language = language.get(0).id;
        mediaPublicationSteps.testMediaPublication.license = license.get(0).id;
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(1).id);
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(2).id);
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(3).id);

        mediaPublicationSteps.createPublication();
        mediaPublicationSteps.checkStatusCode(201);
    }

    @Test
    public void deletePublication(){
        mediaPublicationSteps.testMediaPublication.name = getRandomTextField("publication name");
        mediaPublicationSteps.testMediaPublication.description = getRandomTextField("publication description");
        mediaPublicationSteps.testMediaPublication.language = language.get(0).id;
        mediaPublicationSteps.testMediaPublication.license = license.get(0).id;
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(1).id);

        mediaPublicationSteps.createPublication();
        mediaPublicationSteps.deletePublication(mediaPublicationSteps.testMediaPublication.id);
        mediaPublicationSteps.checkStatusCode(204);
        mediaPublicationSteps.getPublication(mediaPublicationSteps.testMediaPublication.id);
        mediaPublicationSteps.checkStatusCode(404);
    }

    @Test
    public void editPublication(){
        mediaPublicationSteps.testMediaPublication.name = getRandomTextField("publication name");
        mediaPublicationSteps.testMediaPublication.description = getRandomTextField("publication description");
        mediaPublicationSteps.testMediaPublication.language = language.get(0).id;
        mediaPublicationSteps.testMediaPublication.license = license.get(0).id;
        mediaPublicationSteps.testMediaPublication.studies.add(fieldsOfStudy.get(1).id);
        mediaPublicationSteps.createPublication();

        mediaPublicationSteps.testMediaPublication.name = getRandomTextField("changed publication name");
        mediaPublicationSteps.editPublication(mediaPublicationSteps.testMediaPublication.id);
        mediaPublicationSteps.checkStatusCode(200);
        mediaPublicationSteps.checkThatBodyHasValue("changed publication name");
    }


}
