package main.java.steps;

import main.java.api.contentCloud.ScreenAPI;
import main.java.entities.contentCloud.Screen;

import static main.java.properties.Constants.PATH_ID;

public class ScreenSteps extends CommonSteps {
        public Screen testScreen;

        public ScreenAPI ScreensApi = new ScreenAPI();

        public void createScreen(String parentFolder){
            testScreen = new Screen(parentFolder);
            response = ScreensApi.post(testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        public void createScreen(String name, String description, String parentFolder){
            testScreen = new Screen(name, description, parentFolder);
            response = ScreensApi.post(testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        public void editScreen(){
            response = ScreensApi.put(testScreen.id, testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        public void copyScreen(String parent){
            response = ScreensApi.copy(parent, testScreen.id);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        public void moveScreen(String parent){
            testScreen.parentFolder = parent;
            response = ScreensApi.put(testScreen.id, testScreen);
            testScreen.id = response.jsonPath().getString(PATH_ID);
        }

        public void deleteScreen(String screenId) {
            response = ScreensApi.delete(screenId);
        }

        public void getScreen(){
            response = ScreensApi.getById(testScreen.id);
        }

        public void getScreenBlocks(){
            response = ScreensApi.getBlocks(testScreen.id);
        }

        public void getAllScreens(){
            response = ScreensApi.get();
        }

}
