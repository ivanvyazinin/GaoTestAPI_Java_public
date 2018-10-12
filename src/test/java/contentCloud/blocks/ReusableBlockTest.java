package test.java.contentCloud.blocks;

import io.qameta.allure.Description;
import main.java.entities.contentCloud.blocks.AbstractBlock;
import main.java.entities.contentCloud.blocks.practice.TypeTheAnswer;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ScreenSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import java.util.ArrayList;

import static main.java.utils.Generator.getRandomText;

public class ReusableBlockTest extends SuperTest {
    private CommonSteps steps;
    private ScreenSteps screenSteps;
    private TypeTheAnswer typeTheAnswer;
    private Screen testScreen;
    private ArrayList<AbstractBlock> screenStructure;
    private Screen secondScreen;

    @BeforeClass
    public void prepareSteps(){
        screenSteps = new ScreenSteps();
        steps = new CommonSteps();
        typeTheAnswer = new TypeTheAnswer();
        typeTheAnswer.answers.add(getRandomText(160));
        screenStructure = new ArrayList();
        screenStructure.add(typeTheAnswer);
        testScreen = steps.createEntity(
                new Screen(commonObjects.getTestFolder().id));

        steps.api.setRequestParameters(new String[][] {
                {"embed", "block"},
        });
        screenSteps.updateScreenStructure(testScreen, screenStructure);

        typeTheAnswer.id = ((AbstractBlock) screenSteps.getScreenBlocks(testScreen).items.get(0)).id;
    }

    @Test(priority = 0)
    @Description("Make block from screen reusable")
    public void makeBlockInScreenReusable() {
        typeTheAnswer.reusable = true;
        typeTheAnswer.setParentFolder(commonObjects.getTestFolder());
        typeTheAnswer.level = context.getLevel();
        typeTheAnswer.name = getRandomText(15);

        screenSteps.updateScreenStructure(testScreen, screenStructure);
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatBodyHasValue(typeTheAnswer.question);
        screenSteps.checkThatJsonContains(true, "data.items[0].reusable");
    }

    @Test(priority = 1)
    @Description("Cannot make block Unreusable, when it used in more than 1 screen")
    public void makeBlockFromTwoScreensUnReusable() {
        secondScreen = steps.createEntity(
                new Screen(context.getTestFolder()));

        screenSteps.updateScreenStructure(secondScreen, screenStructure);

        typeTheAnswer.reusable = false;
        typeTheAnswer.setParentFolder(null);
        typeTheAnswer.level = null;
        typeTheAnswer.name = null;

        screenSteps.updateScreenStructure(testScreen, screenStructure);
        screenSteps.checkStatusCode(400);
    }

    @Test(priority = 3)
    @Description("Make block from screen Unreusable")
    public void makeBlockInScreenUnReusable() {
        steps.deleteEntity(secondScreen);

        screenSteps.updateScreenStructure(testScreen, screenStructure);
        screenSteps.checkStatusCode(200);
        screenSteps.checkThatJsonContains(false, "data.items[0].reusable");
    }
}
