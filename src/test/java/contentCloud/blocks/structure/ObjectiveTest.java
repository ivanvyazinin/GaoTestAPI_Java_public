package test.java.contentCloud.blocks.structure;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.structure.CourseObjectives;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Content Cloud")
@Feature("Editor adds Structure blocks to the screen")
@Story("Editor adds Objective block")
public class ObjectiveTest extends SuperTest {
    private CommonSteps steps;
    private CourseObjectives courseObjectives;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        courseObjectives = new CourseObjectives();
    }

    @Test
    @Story("Create CourseObjectives Block")
    @Description("Just create CourseObjectives Block")
    public void createObjective() {
        courseObjectives.objectives.add(new CourseObjectives.Objective());
        steps.createEntity(courseObjectives);
        steps.checkStatusCode(201);
    }


    @Test
    @Story("Create CourseObjectives Block")
    @Description("Cannot create CourseObjectives Block with 513 symbols in Objective point")
    public void createObjectiveWithTooLongPoint() {
        courseObjectives.objectives.add(new CourseObjectives.Objective(getRandomText(513),getRandomTextRandomLength(1024)));
        steps.createEntity(courseObjectives);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Create CourseObjectives Block")
    @Description("Cannot create CourseObjectives Block with 1025 symbols in Objective description ")
    public void createObjectiveWithTooLongDescription() {
        courseObjectives.objectives.add(new CourseObjectives.Objective(getRandomTextRandomLength(512),getRandomText(1025)));
        steps.createEntity(courseObjectives);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Create CourseObjectives Block")
    @Description("Cannot create CourseObjectives Block with 161 symbol in Objective title")
    public void createObjectiveWithTooLongTitle() {
        courseObjectives.objectives.add(new CourseObjectives.Objective());
        courseObjectives.title=getRandomText(161);
        steps.createEntity(courseObjectives);
        steps.checkStatusCode(400);
    }


}
