package test.java.directories;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.directories.Skill;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Editor works with directories")
@Feature("CRUD for Directories")
@Story("CRUD for Skill Directory")
public class SkillTest extends SuperTest {
    private CommonSteps steps;
    private Skill testSkill;

    @BeforeClass
    public void prepare(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testSkill = new Skill();
        testSkill.name = getRandomTextField("Skill");
    }

    @Test
    public void createSkill(){
        steps.createEntity(testSkill);
        steps.checkStatusCode(201);
    }

    @Test
    public void createSkillWithBlankName(){
        testSkill.name = "";
        steps.createEntity(testSkill);
        steps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    public void createSkillTooLongName(){
        testSkill.name = getRandomText(161);
        steps.createEntity(testSkill);
        steps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createSkillWithSameName(){
        steps.createEntity(testSkill);
        steps.createEntity(testSkill);
        steps.checkStatusCode(400);
    }

    @Test
    public void editSkill(){
        testSkill = steps.createEntity(testSkill);
        testSkill.name = getRandomTextField("Changed SkillAuto");
        steps.editEntity(testSkill);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed SkillAuto");
    }

    @Test
    public void deleteSkill(){
        testSkill = steps.createEntity(testSkill);
        steps.deleteEntity(testSkill);
        steps.checkStatusCode(204);
        steps.getEntity(testSkill);
        steps.checkStatusCode(404);
    }

}
