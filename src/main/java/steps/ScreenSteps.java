package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ScreenAPI;
import main.java.entities.contentCloud.blocks.theory.ScreenBlocks;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.folderItems.ScreenStructure;
import main.java.entities.contentCloud.blocks.AbstractBlock;

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

        @Step("Creating screen with random name into folder '{parentFolder}'")
        public Screen createScreen(Screen screen){
            screen.id = ScreensApi.post(screen).jsonPath().getString(PATH_ID);
            return screen;
        }

        @Step("Copying screen to '{parent}'")
        public Screen copyScreen(Screen screen, String parent){
            response = ScreensApi.copy(parent, screen.id);
            return response.jsonPath().getObject("data",Screen.class);
        }

        @Step("Retrieving screen")
        public void getScreen(Screen screen){
            response = ScreensApi.getById(screen.id);
        }

        @Step("Retrieving blocks of the screen")
        public ScreenBlocks getScreenBlocks(Screen screen){
            return ScreensApi.getBlocks(screen.id).jsonPath().getObject("data",ScreenBlocks.class);
        }

        @Step("Retrieving blocks of the screen")
        public void getScreenBlocks(String screenId){
            response = ScreensApi.getBlocks(screenId);
        }

        @Step("GET screen with block")
        public Screen getScreenWithBlock(Screen screen, AbstractBlock block){
            ScreenStructure screenStructure = new ScreenStructure();
            screenStructure.items.add(block);
            screen.id = ScreensApi.post(screen).jsonPath().getString(PATH_ID);
            ScreensApi.updateBlocks(screen.id, screenStructure);
            return screen;
        }
}