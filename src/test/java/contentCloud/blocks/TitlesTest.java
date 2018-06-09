package test.java.contentCloud.blocks;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.TitleSteps;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Context.FOLDER_FOR_TESTS;

@Feature("Theory Blocks")
public class TitlesTest extends CommonCloudTest {
    private ScreenSteps screenSteps;
    private TitleSteps titleSteps;

    @BeforeClass
    public void prepareSteps(){
        titleSteps = new TitleSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    public void createTitle() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        titleSteps.createTitle(screenSteps.testScreen.id);
        titleSteps.checkStatusCode(201);
    }

    @Test
    public void changeTitlePosition() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        titleSteps.createTitle(screenSteps.testScreen.id);
        titleSteps.createTitle(screenSteps.testScreen.id);
        titleSteps.changePositionOfTitle(titleSteps.testTitle.id, 0);
        titleSteps.checkStatusCode(200);
        titleSteps.checkThatJsonContains(0,"data.position");
    }
}