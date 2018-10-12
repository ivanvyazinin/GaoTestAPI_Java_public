package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.SelectCorrectOption;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Content Cloud")
@Feature("Editor adds Practice blocks to the screen")
@Story("Editor adds SelectCorrectOption block")
public class SelectCorrectOptionTest extends SuperTest {
    private CommonSteps steps;
    private SelectCorrectOption selectCorrectOption;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        selectCorrectOption = new SelectCorrectOption();
    }

    @Test
    @Description("Just create SelectCorrectOption")
    public void createSelectCorrectOption() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        steps.createEntity(selectCorrectOption);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Can't create SelectCorrectOption with 1 answer")
    public void createSelectCorrectOptionWithOneAnswer() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        steps.createEntity(selectCorrectOption);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Can't create SelectCorrectOption with 7 answers")
    public void createSelectCorrectOptionWithSevenAnswers() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        steps.createEntity(selectCorrectOption);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Can't create SelectCorrectOption with equal answers")
    public void createSelectCorrectOptionWithEqualAnswers() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer());
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer",true));
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer",true));
        steps.createEntity(selectCorrectOption);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Can't create SelectCorrectOption with false answers")
    public void createSelectCorrectOptionWithAllFalseAnswers() {
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer1",false));
        selectCorrectOption.answers.add(new SelectCorrectOption.Answer("answer2",false));
        steps.createEntity(selectCorrectOption);
        steps.checkStatusCode(400);
    }
}
