package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Feature;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.Title;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_TITLE;

@Feature("Theory Blocks")
public class TitlesTest extends CommonCloudTest {
    private CommonBlocsAPI titleAPI;
    private Title title;

    @BeforeClass
    public void prepareSteps(){
        titleAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_TITLE);
    }

    @BeforeMethod
    public void prepareEntity(){
        title  = new Title();
    }


    @Test
    public void createTitle() {
        newResponse = titleAPI.post(title);
        checkStatusCode(201);
    }

}