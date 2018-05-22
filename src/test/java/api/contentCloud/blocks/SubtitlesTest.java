package test.java.api.contentCloud.blocks;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.ScreenSteps;
import test.java.steps.blocks.SubtitlesSteps;

public class SubtitlesTest extends SuperTest {
    private ScreenSteps screenSteps;
    private SubtitlesSteps subtitleSteps;

    @BeforeClass
    public void prepareSteps(){
        subtitleSteps = new SubtitlesSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    public void createTitle() {
        screenSteps.createScreen(folderForTests);
        subtitleSteps.createSubtitle(screenSteps.testScreenId);
        subtitleSteps.checkStatusCode(201);
    }
}