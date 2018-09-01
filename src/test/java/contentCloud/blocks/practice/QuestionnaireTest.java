package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.Questionnaire;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.properties.Endpoints.ENDPOINT_QUESTIONNARIE;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class QuestionnaireTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private Questionnaire questionnaire;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE,ENDPOINT_QUESTIONNARIE);
    }

    @BeforeMethod
    public void prepareEntity(){
        questionnaire = new Questionnaire();
    }

    @Test
    @Story("Create Questionnaire Block")
    @Description("Just create Questionnaire Block")
    public void createQuestionnaire() {
        questionnaire.questions.add(new Questionnaire.Question());
        newResponse = practiceBlocksAPI.post(questionnaire);
        checkStatusCode(201);
    }


    @Test
    @Story("Create Questionnaire Block")
    @Description("Cannot create Questionnaire Block with 513 symbols in Statement")
    public void createQuestionnaireWithStatementTooLong() {
        questionnaire.questions.add(new Questionnaire.Question(getRandomTextRandomLength(513),"short"));
        newResponse = practiceBlocksAPI.post(questionnaire);
        checkStatusCode(400);
    }

    @Test
    @Story("Create Questionnaire Block")
    @Description("Cannot create Questionnaire Block with 7 Statements")
    public void createQuestionnaireWithElevenAnswers() {
        for (int i=0;i<11;i++){questionnaire.questions.add(new Questionnaire.Question());}
        newResponse = practiceBlocksAPI.post(questionnaire);
        checkStatusCode(400);
    }


}
