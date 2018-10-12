package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.FillTheGapsFromList;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Content Cloud")
@Feature("Editor adds Practice blocks to the screen")
@Story("Editor adds FillTheGapsFromList block")
public class FillTheGapsFromListTest extends SuperTest {
    private CommonSteps steps;
    private FillTheGapsFromList fillTheGapsFromList;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        fillTheGapsFromList = new FillTheGapsFromList();
    }

    @Test
    @Description("Just create FillTheGapsFromListTest Block")
    public void createfillTheGapsFromList() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(201);
    }

    @Test
    @Description("Cannot create FillTheGapsFromListTest Block without options")
    public void createfillTheGapsFromListWithoutOptions() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        fillTheGapsFromList.gaps.get(0).options.clear();
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create FillTheGapsFromListTest Block with 6 options")
    public void createfillTheGapsFromListWithSixOptions() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        fillTheGapsFromList.gaps.get(0).options.add(getRandomTextRandomLength(160));
        fillTheGapsFromList.gaps.get(0).options.add(getRandomTextRandomLength(160));
        fillTheGapsFromList.gaps.get(0).options.add(getRandomTextRandomLength(160));
        fillTheGapsFromList.gaps.get(0).options.add(getRandomTextRandomLength(160));
        fillTheGapsFromList.gaps.get(0).options.add(getRandomTextRandomLength(160));
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create FillTheGapsFromListTest Block with 161 symbol in option")
    public void createfillTheGapsFromListWithTooLongOptions() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        fillTheGapsFromList.gaps.get(0).options.add(getRandomText(161));
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create FillTheGapsFromListTest Block with 161 symbol in right answer")
    public void createfillTheGapsFromListWithTooLongAnswer() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        fillTheGapsFromList.gaps.get(0).right_answer = getRandomText(161);
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Options in a Gap should be unique")
    public void createfillTheGapsFromListWithNotUniqueOptionsInTheGap() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        fillTheGapsFromList.gaps.get(0).options.add(
                fillTheGapsFromList.gaps.get(0).options.get(0)
        );
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Right answer in a Gap shouldn't be the same as one of the options")
    public void createfillTheGapsFromListWithRightAnswerSameAsOptionInTheGap() {
        fillTheGapsFromList.gaps.add(new FillTheGapsFromList.Gap());
        fillTheGapsFromList.gaps.get(0).options.add(
                fillTheGapsFromList.gaps.get(0).right_answer
        );
        steps.createEntity(fillTheGapsFromList);
        steps.checkStatusCode(400);
    }
}
