package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Subtitle;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Content Cloud")
@Feature("Editor adds Theory blocks to the screen")
@Story("Editor adds Subtitle block")
public class SubtitlesTest extends SuperTest {
    private CommonSteps steps;
    private Subtitle subtitle;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        subtitle  = new Subtitle();
    }

    @Test
    public void createSubtitle() {
        steps.createEntity(subtitle);
        steps.checkStatusCode(201);
    }
}