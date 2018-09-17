package test.java.publications;

import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Generator.getRandomTextField;

public class ContentPublicationSearchTest extends SuperTest {
    private CommonSteps steps;
    private ContentPublication testContentPublication;

    @Test
    public void prepare(){
        steps = new CommonSteps();
        ContentItemSteps contentItemSteps = new ContentItemSteps();
        ContentItem testContentItem = contentItemSteps.getCIWithValidConstructor(
                new ContentItem(ROOT_FOLDER),
                new Screen(ROOT_FOLDER),
                new Paragraph(ROOT_FOLDER, context.getLevel()));

        for(int i=0; i<20; i++){
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

            steps.createEntity(testContentPublication);
        }
    }

   // @Test
    public void searchPublicationByName(){
        steps.getEntites(ContentPublication.class,ContentPublication.url);
    }
}
