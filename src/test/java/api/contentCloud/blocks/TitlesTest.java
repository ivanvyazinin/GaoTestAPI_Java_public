package test.java.api.contentCloud.blocks;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.api.SuperTest;
import test.java.steps.ScreenSteps;
import test.java.steps.blocks.TitleSteps;

import static main.java.properties.Constants.ROOT_FOLDER;

public class TitlesTest extends SuperTest {
    private ScreenSteps screenSteps;
    private TitleSteps titleSteps;

    @BeforeClass
    public void prepareSteps(){
        titleSteps = new TitleSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    public void createTitle() {
        screenSteps.createScreen(folderForTests);
        titleSteps.createTitle(screenSteps.testScreenId);
        titleSteps.checkStatusCode(201);
    }
}