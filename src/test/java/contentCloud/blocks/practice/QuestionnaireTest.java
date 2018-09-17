package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.Questionnaire;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class QuestionnaireTest extends SuperTest {
    private CommonSteps steps;
    private Questionnaire questionnaire;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
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
        steps.createEntity(questionnaire);
        steps.checkStatusCode(201);
    }


    @Test
    @Story("Create Questionnaire Block")
    @Description("Cannot create Questionnaire Block with 513 symbols in Statement")
    public void createQuestionnaireWithStatementTooLong() {
        questionnaire.questions.add(new Questionnaire.Question(getRandomTextRandomLength(513),"short"));
        steps.createEntity(questionnaire);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Create Questionnaire Block")
    @Description("Cannot create Questionnaire Block with 7 Statements")
    public void createQuestionnaireWithElevenAnswers() {
        for (int i=0;i<11;i++){questionnaire.questions.add(new Questionnaire.Question());}
        steps.createEntity(questionnaire);
        steps.checkStatusCode(400);
    }


}
