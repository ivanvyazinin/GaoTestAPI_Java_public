package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.SelectCorrectOption;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.properties.Endpoints.ENDPOINT_SELECT_CORRECT_OPTION;

public class SelectCorrectOptionTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private SelectCorrectOption selectCorrectOption;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_SELECT_CORRECT_OPTION);
    }

    @BeforeMethod
    public void prepareEntity(){
        selectCorrectOption = new SelectCorrectOption();
    }

    @Test
    @Story("Create SelectCorrectOption")
    @Description("Just create SelectCorrectOption")
    public void createSelectCorrectOption() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        newResponse = practiceBlocksAPI.post(selectCorrectOption);
        checkStatusCode(201);
    }

    @Test
    @Story("Create SelectCorrectOption")
    @Description("Can't create SelectCorrectOption with 1 answer")
    public void createSelectCorrectOptionWithOneAnswer() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        newResponse = practiceBlocksAPI.post(selectCorrectOption);
        checkStatusCode(400);
    }

    @Test
    @Story("Create SelectCorrectOption")
    @Description("Can't create SelectCorrectOption with 7 answers")
    public void createSelectCorrectOptionWithSevenAnswers() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        newResponse = practiceBlocksAPI.post(selectCorrectOption);
        checkStatusCode(400);
    }

    @Test
    @Story("Create SelectCorrectOption")
    @Description("Can't create SelectCorrectOption with equal answers")
    public void createSelectCorrectOptionWithEqualAnswers() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer",true));
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer",true));
        newResponse = practiceBlocksAPI.post(selectCorrectOption);
        checkStatusCode(400);
    }

    @Test
    @Story("Create SelectCorrectOption")
    @Description("Can't create SelectCorrectOption with false answers")
    public void createSelectCorrectOptionWithAllFalseAnswers() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer1",false));
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer2",false));
        newResponse = practiceBlocksAPI.post(selectCorrectOption);
        checkStatusCode(400);
    }

}
