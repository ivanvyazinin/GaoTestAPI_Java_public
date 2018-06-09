package test.java.directories;

import main.java.steps.directories.SkillSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

public class SkillTest extends SuperTest {
    SkillSteps skillsSteps;

    @BeforeClass
    void prepare(){
        skillsSteps = new SkillSteps();
    }

    @Test
    public void createSkill(){
        skillsSteps.createSkill();
        skillsSteps.checkStatusCode(201);
    }

    @Test
    public void createSkillWithBlankName(){
        skillsSteps.createSkill("");
        skillsSteps.checkStatusCode(400);
        skillsSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    public void createSkillTooLongName(){
        skillsSteps.createSkill(getRandomText(161));
        skillsSteps.checkStatusCode(400);
        skillsSteps.checkThatJsonContains(ERROR_TOO_LONG, PATH_ERROR);
    }

    @Test
    public void createSkillWithSameName(){
        skillsSteps.createSkill();
        skillsSteps.createSkill(skillsSteps.testSkill.name);
        skillsSteps.checkStatusCode(400);
        //TODO check error
    }

    @Test
    public void editSkill(){
        skillsSteps.createSkill();
        skillsSteps.testSkill.name = getRandomTextField("Changed SkillAuto");
        skillsSteps.editSkill(skillsSteps.testSkill.id);
        skillsSteps.checkStatusCode(200);
        skillsSteps.checkThatBodyHasValue("Changed SkillAuto");
    }

    @Test
    public void deleteSkill(){
        skillsSteps.createSkill();
        skillsSteps.deleteSkill(skillsSteps.testSkill.id);
        skillsSteps.checkStatusCode(204);
        skillsSteps.getSkill(skillsSteps.testSkill.id);
        skillsSteps.checkStatusCode(404);
    }

}
