package test.java.publications;

import main.java.steps.publications.ContentPublicationSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

public class ContentPublicationTest extends SuperPublicationTest {
    ContentPublicationSteps contentPublicationSteps;

    @BeforeClass
    public void prepare(){
        contentPublicationSteps = new ContentPublicationSteps();
    }

    @Test
    public void createPublication(){
        contentPublicationSteps.testContentPublication.name = getRandomTextField("publication name");
        contentPublicationSteps.testContentPublication.description = getRandomTextField("publication description");
        contentPublicationSteps.testContentPublication.eqf = eqf.get(0).id;
        contentPublicationSteps.testContentPublication.level = level.get(0).id;
        contentPublicationSteps.testContentPublication.language = language.get(0).id;
        contentPublicationSteps.testContentPublication.zone = functionalZone.get(0).id;
        contentPublicationSteps.testContentPublication.license = license.get(0).id;
        contentPublicationSteps.testContentPublication.isco.add(isco.get(1).id);
        contentPublicationSteps.testContentPublication.skills.add(skill.get(1).id);
        contentPublicationSteps.testContentPublication.studies.add(fieldsOfStudy.get(1).id);

        contentPublicationSteps.createPublication();
        contentPublicationSteps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithoutDescription(){
        contentPublicationSteps.testContentPublication.name = getRandomTextField("publication name");
        contentPublicationSteps.testContentPublication.eqf = eqf.get(0).id;
        contentPublicationSteps.testContentPublication.level = level.get(0).id;
        contentPublicationSteps.testContentPublication.language = language.get(0).id;
        contentPublicationSteps.testContentPublication.zone = functionalZone.get(0).id;
        contentPublicationSteps.testContentPublication.license = license.get(0).id;
        contentPublicationSteps.testContentPublication.isco.add(isco.get(1).id);
        contentPublicationSteps.testContentPublication.skills.add(skill.get(1).id);
        contentPublicationSteps.testContentPublication.studies.add(fieldsOfStudy.get(1).id);

        contentPublicationSteps.createPublication();
        contentPublicationSteps.checkStatusCode(201);
    }

    @Test
    public void createPublicationWithNotValidName(){
        contentPublicationSteps.testContentPublication.name = getRandomText(161);
        contentPublicationSteps.testContentPublication.eqf = eqf.get(0).id;
        contentPublicationSteps.testContentPublication.level = level.get(0).id;
        contentPublicationSteps.testContentPublication.language = language.get(0).id;
        contentPublicationSteps.testContentPublication.zone = functionalZone.get(0).id;
        contentPublicationSteps.testContentPublication.license = license.get(0).id;
        contentPublicationSteps.testContentPublication.isco.add(isco.get(1).id);
        contentPublicationSteps.testContentPublication.skills.add(skill.get(1).id);
        contentPublicationSteps.testContentPublication.studies.add(fieldsOfStudy.get(1).id);

        contentPublicationSteps.createPublication();
        contentPublicationSteps.checkStatusCode(400);
    }
}
