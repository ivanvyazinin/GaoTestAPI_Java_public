package test.java.directories;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import main.java.entities.directories.*;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Editor works with directories")
@Feature("Editors searches a record in directory")
public class SearchDirectoriesTest extends SuperTest {
    private CommonSteps steps;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
    }

    @Test
    public void searchEQF(){
        steps.api.setRequestParameters(new String[][] {
                {"search","EQF1"},
        });

        steps.getEntites(Eqf.class, Eqf.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
    }

    @Test
    public void partialSearchEQF(){
        steps.api.setRequestParameters(new String[][] {
                {"search","EQF"},
        });

        steps.getEntites(Eqf.class, Eqf.url);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("EQF1");
    }

    @Test
    public void searchLevel(){
        steps.api.setRequestParameters(new String[][] {
                {"search","A"},
        });

        steps.getEntites(Level.class, Level.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
    }

    @Test
    public void NothingFoundSearchLevel(){
        steps.api.setRequestParameters(new String[][] {
                {"search","ASD"},
        });

        steps.getEntites(Level.class, Level.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(0);
    }

    @Test
    public void searchLicence(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Public Domain"},
        });

        steps.getEntites(Licence.class, Licence.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchLicenceByDescription(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Public Domain Items"},
        });

        steps.getEntites(Licence.class, Licence.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(0);
    }

    @Test
    public void searchLanguage(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Russian"},
        });

        steps.getEntites(Language.class, Language.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchSkill(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Leadership"},
        });

        steps.getEntites(Skill.class, Skill.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchFunctionalZones(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Managing"},
        });

        steps.getEntites(Zone.class, Zone.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchIscoName(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Government social benefits official"},
        });

        steps.getEntites(Isco.class, Isco.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
        steps.checkThatJsonContains("3443","data.items[0].code");
    }

    @Test
    public void searchIscoCode(){
        steps.api.setRequestParameters(new String[][] {
                {"search","3443"},
        });

        steps.getEntites(Isco.class, Isco.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
        steps.checkThatJsonContains("Government social benefits official","data.items[0].name");
    }

    @Test
    public void searchStudy(){
        steps.api.setRequestParameters(new String[][] {
                {"search","Economic history"},
        });

        steps.getEntites(Study.class, Study.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(1);
        steps.checkThatBodyHasValue("\"level\":2");
        steps.checkThatBodyHasValue("\"level\":1");
    }
}
