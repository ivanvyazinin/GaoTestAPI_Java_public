package test.java.publications;

import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.publications.ContentPublication;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.publications.ContentPublicationSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Lists.getRandomItem;

public class ContentPublicationTest extends SuperPublicationTest {
    ContentPublicationSteps contentPublicationSteps;
    ContentItemSteps contentItemSteps;
    ScreenSteps screenSteps;
    ContentItem testContentItem;

    @BeforeClass
    public void prepare(){
        contentPublicationSteps = new ContentPublicationSteps();
        contentItemSteps = new ContentItemSteps();
        screenSteps = new ScreenSteps();

        Screen testScreen = new Screen(ROOT_FOLDER);
        screenSteps.getScreenWithBlock(testScreen, new Paragraph(ROOT_FOLDER, getRandomItem(level).id));

        testContentItem = new ContentItem(ROOT_FOLDER);
        contentItemSteps.getCIWithValidConstructor(testContentItem, testScreen.id);
    }

    @BeforeMethod
    public void prepareTestPublication(){
        contentPublicationSteps.testContentPublication = new ContentPublication();
    }

    @Test
    public void createPublication(){
        contentPublicationSteps.testContentPublication.name = getRandomTextField("publication name");
        contentPublicationSteps.testContentPublication.description = getRandomTextField("publication description");
        contentPublicationSteps.testContentPublication.eqf = getRandomItem(eqf).id;
        contentPublicationSteps.testContentPublication.level = getRandomItem(level).id;
        contentPublicationSteps.testContentPublication.language = getRandomItem(language).id;
        contentPublicationSteps.testContentPublication.zone = getRandomItem(functionalZone).id;
        contentPublicationSteps.testContentPublication.license = getRandomItem(license).id;
        contentPublicationSteps.testContentPublication.isco.add(getRandomItem(isco).id);
        contentPublicationSteps.testContentPublication.skills.add(getRandomItem(skill).id);
        contentPublicationSteps.testContentPublication.studies.add(getRandomItem(fieldsOfStudy).id);
        contentPublicationSteps.testContentPublication.contentItems.add(testContentItem.id);

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
        contentPublicationSteps.testContentPublication.contentItems.add(testContentItem.id);

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
        contentPublicationSteps.testContentPublication.contentItems.add(testContentItem.id);

        contentPublicationSteps.createPublication();
        contentPublicationSteps.checkStatusCode(400);
    }
}
