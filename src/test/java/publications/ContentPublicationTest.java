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
import test.java.SuperTest;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

public class ContentPublicationTest extends SuperTest {
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
                new Paragraph(ROOT_FOLDER, context.getLevel()));
    }

    @BeforeMethod
    public void prepareEntity(){
        testContentPublication = new ContentPublication();
        testContentPublication.name = getRandomTextField("publication name");
        testContentPublication.description = getRandomTextField("publication description");
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
}
