package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.SelectCorrectOptionAfterReadingText;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.properties.Endpoints.ENDPOINT_SELECT_CORRECT_OPTION_AFTER_READING;

public class SelectCorrectOptionAfterReadingTextTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private SelectCorrectOptionAfterReadingText selectCorrectOptionAfterReadingText;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_SELECT_CORRECT_OPTION_AFTER_READING);
    }

    @BeforeMethod
    public void prepareEntity(){
        selectCorrectOptionAfterReadingText = new SelectCorrectOptionAfterReadingText();
    }

    @Test
    @Story("Create SelectCorrectOptionAfterReading")
    @Description("Just create SelectCorrectOptionAfterReading")
    public void createSelectCorrectOptionAfterReading() {
        selectCorrectOptionAfterReadingText.questionsWithAnswers.add(new SelectCorrectOptionAfterReadingText.QuestionsWithAnswer());
        newResponse = practiceBlocksAPI.post(selectCorrectOptionAfterReadingText);
        checkStatusCode(201);
    }

    @Test
    @Story("Create SelectCorrectOptionAfterReading")
    @Description("Can't create SelectCorrectOptionAfterReading without questions")
    public void createSelectCorrectOptionAfterReadingWithoutQuestions() {
        newResponse = practiceBlocksAPI.post(selectCorrectOptionAfterReadingText);
        checkStatusCode(400);
    }

    @Test
    @Story("Create SelectCorrectOptionAfterReading")
    @Description("Can't create SelectCorrectOptionAfterReading with 11 questions")
    public void createSelectCorrectOptionAfterReadingWith20questions() {
        for (int i=0;i<11;i++){
            selectCorrectOptionAfterReadingText.questionsWithAnswers.add(new SelectCorrectOptionAfterReadingText.QuestionsWithAnswer());
        }
        newResponse = practiceBlocksAPI.post(selectCorrectOptionAfterReadingText);
        checkStatusCode(400);
    }

}
