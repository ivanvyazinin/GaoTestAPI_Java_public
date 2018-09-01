package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Feature;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.Subtitle;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_SUBTITLE;

@Feature("Theory Blocks")
public class SubtitlesTest extends CommonCloudTest {
    private CommonBlocsAPI subtitleAPI;
    private Subtitle subtitle;

    @BeforeClass
    public void prepareSteps(){
        subtitleAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_SUBTITLE);
    }

    @BeforeMethod
    public void prepareEntity(){
        subtitle  = new Subtitle();
    }

    @Test
    public void createSubtitle() {
        newResponse = subtitleAPI.post(subtitle);
        checkStatusCode(201);
    }
}