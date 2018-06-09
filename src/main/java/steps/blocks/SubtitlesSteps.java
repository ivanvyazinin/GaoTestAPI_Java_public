package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.TheoryBlocksAPI;
import main.java.entities.contentCloud.blocks.Subtitle;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;
import static main.java.properties.Endpoints.ENDPOINT_SUBTITLE;

public class SubtitlesSteps extends CommonSteps {
    public Subtitle testSubtitle;

    TheoryBlocksAPI titlesAPI = new TheoryBlocksAPI(ENDPOINT_SUBTITLE);

    @Step("Creating Subtitle with random name in screen '{screen}'")
    public void createSubtitle(String screen){
        testSubtitle = new Subtitle(screen);
        response = titlesAPI.post(testSubtitle);
        testSubtitle.id = response.jsonPath().getString(PATH_ID);
    }
}
