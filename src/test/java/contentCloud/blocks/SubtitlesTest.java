package test.java.contentCloud.blocks;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.SubtitlesSteps;

import static main.java.properties.Context.FOLDER_FOR_TESTS;

@Feature("Theory Blocks")
public class SubtitlesTest extends SuperTest {
    private ScreenSteps screenSteps;
    private SubtitlesSteps subtitleSteps;

    @BeforeClass
    public void prepareSteps(){
        subtitleSteps = new SubtitlesSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    public void createSubtitle() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        subtitleSteps.createSubtitle(screenSteps.testScreen.id);
        subtitleSteps.checkStatusCode(201);
    }
}