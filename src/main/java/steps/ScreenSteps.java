package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ScreenAPI;
import main.java.entities.contentCloud.Screen;

import static main.java.properties.Constants.PATH_ID;

public class ScreenSteps extends CommonSteps {
        public Screen testScreen;

        public ScreenAPI ScreensApi = new ScreenAPI();

        @Step("Creating screen with random name into folder '{parentFolder}'")
        public void createScreen(String parentFolder){
            testScreen = new Screen(parentFolder);
            response = ScreensApi.post(testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        @Step("Creating screen with name '{name}' and description '{description}' into folder '{parentFolder}'")
        public void createScreen(String name, String description, String parentFolder){
            testScreen = new Screen(name, description, parentFolder);
            response = ScreensApi.post(testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        @Step("Editing screen")
        public void editScreen(){
            response = ScreensApi.put(testScreen.id, testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        @Step("Copying screen to '{parent}'")
        public void copyScreen(String parent){
            response = ScreensApi.copy(parent, testScreen.id);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        @Step("Moving screen to '{parent}'")
        public void moveScreen(String parent){
            testScreen.parentFolder = parent;
            response = ScreensApi.put(testScreen.id, testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        @Step("Deleting screen '{screenId}'")
        public void deleteScreen(String screenId) {
            response = ScreensApi.delete(screenId);
        }

        @Step("Retrieving screen")
        public void getScreen(){
            response = ScreensApi.getById(testScreen.id);
        }

        @Step("Retrieving blocks of the screen")
        public void getScreenBlocks(){
            response = ScreensApi.getBlocks(testScreen.id);
        }

        @Step("Retrieving all screens")
        public void getAllScreens(){
            response = ScreensApi.get();
        }
}
