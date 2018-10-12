package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Title;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Content Cloud")
@Feature("Editor adds Theory blocks to the screen")
@Story("Editor adds Title block")
public class TitlesTest extends SuperTest {
    private CommonSteps steps;
    private Title title;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        title  = new Title();
    }

    @Test
    public void createTitle() {
        steps.createEntity(title);
        steps.checkStatusCode(201);
    }

}