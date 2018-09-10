package test.java.contentCloud.blocks.structure;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.structure.WelcomeExit;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Context.FILE_PATH_IMAGE;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_STRUCTURE;
import static main.java.properties.Endpoints.ENDPOINT_BLOCK_WELCOME_EXIT;

public class WelcomeExitTest extends CommonCloudTest {
    private CommonSteps steps;
    private WelcomeExit welcomeExitTest;
    private FilesSteps filesSteps;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        welcomeExitTest = new WelcomeExit();
    }

    @Test
    @Story("Create WelcomeExit Block")
    @Description("Just create WelcomeExit Block")
    public void createWelcomeExitBlock() {
        welcomeExitTest.files.add(filesSteps.uploadFile(FILE_PATH_IMAGE));
        steps.createEntity(welcomeExitTest);
        steps.checkStatusCode(201);
    }
}
