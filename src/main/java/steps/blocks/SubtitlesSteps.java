package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.SubtitlesAPI;
import main.java.entities.contentCloud.blocks.Subtitle;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class SubtitlesSteps extends CommonSteps {
    public Subtitle testSubtitle;

    SubtitlesAPI titlesAPI = new SubtitlesAPI();

    @Step("Creating Subtitle with random name in screen '{screen}'")
    public void createSubtitle(String screen){
        testSubtitle = new Subtitle(screen);
        response = titlesAPI.post(testSubtitle);
        testSubtitle.id = response.jsonPath().getString(PATH_ID);
    }
}
