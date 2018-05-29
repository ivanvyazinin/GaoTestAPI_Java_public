package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.TitlesAPI;
import main.java.entities.contentCloud.blocks.Title;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;
import static main.java.utils.Generator.getRandomTextField;

public class TitleSteps extends CommonSteps {
    public Title testTitle;

    TitlesAPI titlesAPI = new TitlesAPI();

    @Step("Creating Title with random name in screen '{screen}'")
    public void createTitle(String screen){
        testTitle = new Title(screen);
        response = titlesAPI.post(testTitle);
        testTitle.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating Title with name '{name}' and position '{position}' in screen '{screen}'")
    public void createTitle(String screen, int position){
        testTitle = new Title(getRandomTextField("title"), screen, position);
        response = titlesAPI.post(testTitle);
        testTitle.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Changing Title position to '{position}'")
    public void changePositionOfTitle(String titleId, int position){
        testTitle.position = position;
        response = titlesAPI.put(titleId, testTitle);
        testTitle.id = response.jsonPath().getString(PATH_ID);
    }
}