package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_ANSWER;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.utils.Generator.getRandomText;

public class TypeTheAnswerTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private TypeTheAnswer typeTheAnswer;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_ANSWER);
    }

    @BeforeMethod
    public void prepareEntity(){
        typeTheAnswer = new TypeTheAnswer();
    }

    @Test
    @Story("Create TypeTheAnswer Block")
    @Description("Just create TypeTheAnswer Block")
    public void createTypeTheAnswer() {
        typeTheAnswer.answers.add(getRandomText(160));
        newResponse = practiceBlocksAPI.post(typeTheAnswer);
        checkStatusCode(201);
    }

    @Test
    @Story("Create TypeTheAnswer Block")
    @Description("Cannot create TypeTheAnswer Block with 1025 symbols in question")
    public void createTypeTheAnswerWithQuestionTooLong() {
        typeTheAnswer.answers.add(getRandomText(11));
        typeTheAnswer.question=getRandomText(1025);
        newResponse = practiceBlocksAPI.post(typeTheAnswer);
        checkStatusCode(400);
    }
    @Test
    @Story("Create TypeTheAnswer Block")
    @Description("Cannot create TypeTheAnswer Block with 11 answers")
    public void createTypeTheAnswerWithElevenAnswers() {
        for (int i=0;i<11;i++){typeTheAnswer.answers.add(getRandomText(160));}
        newResponse = practiceBlocksAPI.post(typeTheAnswer);
        checkStatusCode(400);
    }

}
