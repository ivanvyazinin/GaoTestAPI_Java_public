package test.java.publications;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.Tag;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextField;

@Epic("Publications")
@Feature("Search, Sort and Filter Publications")
@Story("Search, Sort and Filter Content Publications")
public class ContentPublicationSearchTest extends SuperTest {
    private CommonSteps steps;
    private String nameFirst;
    private Tag tag;
    private String level;
    private String language;
    private String skill;
    private String zone;
    private String eqf;
    private String isco;
    private String license;
    private String study;

    @BeforeClass
    public void prepare(){
        nameFirst = getRandomTextField("! first publication");
        tag = new Tag(getRandomTextField("ContentPublicationSearchTest"));
        level=context.getLevel();
        language = context.getLanguage();
        skill = context.getSkill();
        zone = context.getZone();
        eqf = context.getEqf();
        isco = context.getIsco();
        license = context.getLicence(0);
        study = context.getStudy();

        steps = new CommonSteps();

        ContentPublication contentPublication = steps.createEntity(
                new ContentPublication(context).
                        addCi(commonObjects.getValidCI()).setLicense(context.getLicence(1))
        );
        steps.addTag(contentPublication, "content_publication", tag);

        steps.createEntity(
                new ContentPublication(context).
                        addCi(commonObjects.getValidCI()).
                        setName(nameFirst)
        );

        for(int i=0; i<5; i++){
            steps.createEntity(
                    new ContentPublication(context).addCi(commonObjects.getValidCI()).setLicense(context.getLicence(1))
            );
        }

        ContentPublication filterTestPublication = new ContentPublication();
        filterTestPublication.name = getRandomTextField("filterTestPublication");
        filterTestPublication.description = getRandomTextField("publication description");
        filterTestPublication.author = getRandomTextField("author");
        filterTestPublication.eqf = eqf;
        filterTestPublication.level = level;
        filterTestPublication.language = language;
        filterTestPublication.zone = zone;
        filterTestPublication.license = license;
        filterTestPublication.isco.add(isco);
        filterTestPublication.skills.add(skill);
        filterTestPublication.studies.add(study);
        filterTestPublication.contentItems.add(commonObjects.getValidCI().id);
        steps.createEntity(filterTestPublication);
    }

    @Test
    public void searchPublicationByName(){
        steps.api.setRequestParameters(new String[][] {
                {"search", nameFirst},
                {"status", "0"}
        });

        steps.getEntites(ContentPublication.class,ContentPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(nameFirst);
    }

    @Test
    public void sortPublicationByName(){
        steps.api.setRequestParameters(new String[][] {
                {"page","1"},
                {"per_page","16"},
                {"sorting","name"},
                {"order","asc"},
                {"status", "0"}
        });

        steps.getEntites(ContentPublication.class,ContentPublication.url);
        steps.checkThatJsonContains(nameFirst,"data.items[0].name");
    }

    @Test
    public void searchPublicationByTag(){
        steps.api.setRequestParameters(new String[][] {
                {"status", "0"},
                {"search",tag.name},
                {"embed","tag"}
        });

        steps.getEntites(ContentPublication.class,ContentPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(tag.name);
    }

    @Test
    public void filterPublicationByAllFields(){
        steps.api.setRequestParameters(new String[][] {
                {"status", "0"},
                {"language",language},
                {"skill",skill},
                {"zone",zone},
                {"eqf",eqf},
                {"license",license},
                {"isco",isco},
                {"study",study}
        });

        steps.getEntites(ContentPublication.class,ContentPublication.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue("filterTestPublication");
    }

    @Test
    public void filterPublicationByLicense(){
        steps.api.setRequestParameters(new String[][] {
                {"status", "0"},
                {"license",license}
        });

        steps.getEntites(ContentPublication.class,ContentPublication.url);
        steps.checkThatBodyHasValue("filterTestPublication");
    }
}
