package test.java.publications;

import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.Test;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Lists.getRandomItem;

public class ContentPublicationSearchTest extends SuperPublicationTest {
    private CommonSteps steps;
    private ContentPublication testContentPublication;

    @Test
    public void prepare(){
        steps = new CommonSteps();
        ContentItemSteps contentItemSteps = new ContentItemSteps();
        ContentItem testContentItem = contentItemSteps.getCIWithValidConstructor(
                new ContentItem(ROOT_FOLDER),
                new Screen(ROOT_FOLDER),
                new Paragraph(ROOT_FOLDER, getRandomItem(level).id));

        for(int i=0; i<20; i++){
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

            steps.createEntity(testContentPublication);
        }
    }

    @Test
    public void searchPublicationByName(){
        steps.getEntites(ContentPublication.class,ContentPublication.url);
    }
}
