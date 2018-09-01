package test.java.publications;

import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.publications.ContentPublication;
import main.java.steps.ContentItemSteps;
import main.java.steps.ScreenSteps;
import main.java.steps.publications.ContentPublicationSteps;
import org.testng.annotations.Test;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Generator.getRandomTextField;
import static main.java.utils.Lists.getRandomItem;

public class ContentPublicationSearchTest extends SuperPublicationTest {
    ContentPublicationSteps contentPublicationSteps;
    ContentItemSteps contentItemSteps;
    ScreenSteps screenSteps;

    @Test
    public void prepare(){
        contentPublicationSteps = new ContentPublicationSteps();
        contentItemSteps = new ContentItemSteps();
        screenSteps = new ScreenSteps();

        Screen testScreen = new Screen(ROOT_FOLDER);
        screenSteps.getScreenWithBlock(testScreen, new Paragraph(ROOT_FOLDER, getRandomItem(level).id));

        ContentItem testContentItem = new ContentItem(ROOT_FOLDER);
        contentItemSteps.getCIWithValidConstructor(testContentItem, testScreen.id);

        for(int i=0; i<20; i++){
            contentPublicationSteps.testContentPublication = new ContentPublication();
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
        }
    }

    @Test
    public void searchPublicationByName(){


        contentPublicationSteps.getPublications();
    }


}
