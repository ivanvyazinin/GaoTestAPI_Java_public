package test.java.publications;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.Tag;
import main.java.entities.publications.MediaPublication;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextField;

@Epic("Publications")
@Feature("Search, Sort and Filter Publications")
@Story("Search, Sort and Filter Media Publications")
public class MediaPublicationSearchTest extends SuperTest {
    private CommonSteps steps;
    private String nameFirst;
    private Tag tag;
    private String language;
    private String license;
    private String study;

    @BeforeClass
    public void prepare(){
        nameFirst = getRandomTextField("! first publication");
        tag = new Tag(getRandomTextField("MediaPublicationSearchTest"));
        language = context.getLanguage();
        license = context.getLicence(1);
        study = context.getStudy();

        steps = new CommonSteps();

        MediaPublication mediaPublication = steps.createEntity(new MediaPublication(context));
        steps.addTag(mediaPublication, "media_publication", tag);

        steps.createEntity(
                new MediaPublication(context).
                        setName(nameFirst).setLicence(context.getLicence(0))
        );

        for(int i=0; i<3; i++){
            steps.createEntity(new MediaPublication(context).setLicence(context.getLicence(0)));
        }

        MediaPublication filterTestPublication = new MediaPublication();
        filterTestPublication.name = getRandomTextField("filterTestPublication");
        filterTestPublication.description = getRandomTextField("publication description");
        filterTestPublication.author = getRandomTextField("author");
        filterTestPublication.language = language;
        filterTestPublication.license = license;
        filterTestPublication.studies.add(study);
        steps.createEntity(filterTestPublication);
    }

    @Test
    public void searchPublicationByName(){
        steps.api.setRequestParameters(new String[][] {
                {"search", nameFirst}
        });

        steps.getEntites(MediaPublication.class,MediaPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(nameFirst);
    }

    @Test
    public void sortPublicationByName(){
        steps.api.setRequestParameters(new String[][] {
                {"page","1"},
                {"per_page","16"},
                {"sorting","name"},
                {"order","asc"}
        });

        steps.getEntites(MediaPublication.class,MediaPublication.url);
        steps.checkThatJsonContains(nameFirst,"data.items[0].name");
    }

    @Test
    public void searchPublicationByTag(){
        steps.api.setRequestParameters(new String[][] {
                {"search",tag.name},
                {"embed","tag"}
        });

        steps.getEntites(MediaPublication.class,MediaPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(tag.name);
    }

    @Test
    public void filterPublicationByAllFields(){
        steps.api.setRequestParameters(new String[][] {
                {"language",language},
                {"license",license},
                {"study",study}
        });

        steps.getEntites(MediaPublication.class,MediaPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue("filterTestPublication");
    }
    @Test

    public void filterPublicationByLicence(){
        steps.api.setRequestParameters(new String[][] {
                {"license",license}
        });

        steps.getEntites(MediaPublication.class,MediaPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue("filterTestPublication");
    }
}
