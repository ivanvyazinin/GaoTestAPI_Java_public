package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.SelectCorrectOptionAfterReadingText;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

public class SelectCorrectOptionAfterReadingTextTest extends SuperTest {
    private CommonSteps steps;
    private SelectCorrectOptionAfterReadingText selectCorrectOptionAfterReadingText;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
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
        steps.createEntity(selectCorrectOptionAfterReadingText);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Create SelectCorrectOptionAfterReading")
    @Description("Can't create SelectCorrectOptionAfterReading without questions")
    public void createSelectCorrectOptionAfterReadingWithoutQuestions() {
        steps.createEntity(selectCorrectOptionAfterReadingText);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Create SelectCorrectOptionAfterReading")
    @Description("Can't create SelectCorrectOptionAfterReading with 11 questions")
    public void createSelectCorrectOptionAfterReadingWith20questions() {
        for (int i=0;i<11;i++){
            selectCorrectOptionAfterReadingText.questionsWithAnswers.add(new SelectCorrectOptionAfterReadingText.QuestionsWithAnswer());
        }
        steps.createEntity(selectCorrectOptionAfterReadingText);
        steps.checkStatusCode(400);
    }

}
