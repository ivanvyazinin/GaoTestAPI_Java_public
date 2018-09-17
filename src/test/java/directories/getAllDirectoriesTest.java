package test.java.directories;

import main.java.entities.directories.*;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.PATH_COUNT;

public class GetAllDirectoriesTest extends SuperTest {
    private CommonSteps steps;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
    }

    @Test
    public void getLevels(){
        steps.getEntites(Level.class,Level.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(8, PATH_COUNT);
        }

    @Test
    public void getEQF(){
        steps.getEntites(Eqf.class,Eqf.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(8, PATH_COUNT);
    }

    @Test
    public void getLicence(){
        steps.getEntites(Licence.class,Licence.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(10, PATH_COUNT);
    }

    @Test
    public void getLanguages(){
        steps.getEntites(Language.class,Language.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(185, PATH_COUNT);
    }

    @Test
    public void getZones(){
        steps.getEntites(Zone.class,Zone.url);
        steps.checkStatusCode(200);
    }

    @Test
    public void getIsco(){
        steps.getEntites(Isco.class,Isco.url);
        steps.checkStatusCode(200);
    }

    @Test
    public void getSkills(){
        steps.getEntites(Skill.class,Skill.url);
        steps.checkStatusCode(200);
    }

    @Test
    public void getStudy(){
        steps.getEntites(Study.class,Study.url);
        steps.checkStatusCode(200);
    }
}
