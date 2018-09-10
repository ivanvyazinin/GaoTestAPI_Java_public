package test.java.publications;

import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Lists.getRandomItem;

public class ContentPublicationTest extends SuperPublicationTest {
    private CommonSteps steps;
    private ContentItem testContentItem;
    private ContentPublication testContentPublication;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
        ContentItemSteps contentItemSteps = new ContentItemSteps();
        testContentItem = contentItemSteps.getCIWithValidConstructor(
                new ContentItem(ROOT_FOLDER),
                new Screen(ROOT_FOLDER),
                new Paragraph(ROOT_FOLDER, getRandomItem(level).id));
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentPublication = new ContentPublication();
        testContentPublication.name = getRandomTextField("publication name");
        testContentPublication.description = getRandomTextField("publication description");
        testContentPublication.eqf = getRandomItem(eqf).id;
        testContentPublication.level = getRandomItem(level).id;
        testContentPublication.language = getRandomItem(language).id;
        testContentPublication.zone = getRandomItem(functionalZone).id;
        testContentPublication.license = getRandomItem(license).id;
        testContentPublication.isco.add(getRandomItem(isco).id);
        testContentPublication.skills.add(getRandomItem(skill).id);
        testContentPublication.studies.add(getRandomItem(fieldsOfStudy).id);
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
}
