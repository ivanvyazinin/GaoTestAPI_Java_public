package test.java.steps.blocks;

import main.java.api.contentCloud.SubtitlesAPI;
import main.java.entities.contentCloud.blocks.Subtitle;
import test.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class SubtitlesSteps extends CommonSteps {
    public Subtitle testSubtitle;
    public String TestSubtitleId;

    SubtitlesAPI titlesAPI = new SubtitlesAPI();

    public void createSubtitle(String screen){
        testSubtitle = new Subtitle(screen);
        response = titlesAPI.post(testSubtitle);
        TestSubtitleId = response.jsonPath().getString(PATH_ID);
    }
}
