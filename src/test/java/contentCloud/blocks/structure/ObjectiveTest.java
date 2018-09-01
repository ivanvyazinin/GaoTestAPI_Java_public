package test.java.contentCloud.blocks.structure;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.structure.CourseObjectives;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_STRUCTURE;
import static main.java.properties.Endpoints.ENDPOINT_COURSE_OBJETIVES;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class ObjectiveTest extends CommonCloudTest {
    private CommonBlocsAPI structureBlocksAPI;
    private CourseObjectives courseObjectives;

    @BeforeClass
    public void prepareSteps(){
        structureBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_STRUCTURE, ENDPOINT_COURSE_OBJETIVES);
    }

    @BeforeMethod
    public void prepareEntity(){
        courseObjectives = new CourseObjectives();
    }

    @Test
    @Story("Create CourseObjectives Block")
    @Description("Just create CourseObjectives Block")
    public void createTypeTheAnswer() {
        courseObjectives.objectives.add(new CourseObjectives.Objective());
        newResponse = structureBlocksAPI.post(courseObjectives);
        checkStatusCode(201);
    }


    @Test
    @Story("Create CourseObjectives Block")
    @Description("Cannot create CourseObjectives Block with 513 symbols in Objective point")
    public void createTypeTheAnswerWithTooLongPoint() {
        courseObjectives.objectives.add(new CourseObjectives.Objective(getRandomText(513),getRandomTextRandomLength(1024)));
        newResponse = structureBlocksAPI.post(courseObjectives);
        checkStatusCode(400);
    }

    @Test
    @Story("Create CourseObjectives Block")
    @Description("Cannot create CourseObjectives Block with 1025 symbols in Objective description ")
    public void createTypeTheAnswerWithTooLongDescription() {
        courseObjectives.objectives.add(new CourseObjectives.Objective(getRandomTextRandomLength(512),getRandomText(1025)));
        newResponse = structureBlocksAPI.post(courseObjectives);
        checkStatusCode(400);
    }

    @Test
    @Story("Create CourseObjectives Block")
    @Description("Cannot create CourseObjectives Block with 161 symbol in Objective title")
    public void createTypeTheAnswerWithTooLongTitle() {
        courseObjectives.objectives.add(new CourseObjectives.Objective());
        courseObjectives.title=getRandomText(161);
        newResponse = structureBlocksAPI.post(courseObjectives);
        checkStatusCode(400);
    }


}
