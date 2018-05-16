package test.java.steps.blocks;

import main.java.api.contentCloud.TitlesAPI;
import main.java.entities.contentCloud.blocks.Title;
import test.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class TitleSteps extends CommonSteps {
    public Title testTitle;
    public String testTitleId;

    TitlesAPI titlesAPI = new TitlesAPI();

    public void createTitle(String screen){
        testTitle = new Title(screen);
        response = titlesAPI.post(testTitle);
        testTitleId = response.jsonPath().getString(PATH_ID);
    }
}
