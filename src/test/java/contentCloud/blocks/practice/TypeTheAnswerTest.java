package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.*;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomText;

public class TypeTheAnswerTest extends SuperTest {
    private CommonSteps steps;
    private TypeTheAnswer typeTheAnswer;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
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
        steps.createEntity(typeTheAnswer);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Create TypeTheAnswer Block")
    @Description("Cannot create TypeTheAnswer Block with 1025 symbols in question")
    public void createTypeTheAnswerWithQuestionTooLong() {
        typeTheAnswer.answers.add(getRandomText(11));
        typeTheAnswer.question=getRandomText(1025);
        steps.createEntity(typeTheAnswer);
        steps.checkStatusCode(400);
    }
    @Test
    @Story("Create TypeTheAnswer Block")
    @Description("Cannot create TypeTheAnswer Block with 11 answers")
    public void createTypeTheAnswerWithElevenAnswers() {
        for (int i=0;i<11;i++){typeTheAnswer.answers.add(getRandomText(160));}
        steps.createEntity(typeTheAnswer);
        steps.checkStatusCode(400);
    }

}
