package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ScreenAPI;
import main.java.api.contentCloud.TagsAPI;
import main.java.entities.contentCloud.Tag;
import main.java.entities.contentCloud.blocks.theory.ScreenBlocks;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.entities.contentCloud.folderItems.ScreenStructure;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

import static main.java.properties.Constants.PATH_ID;

public class ScreenSteps <T extends AbstractBlock> extends CommonSteps {
        public ScreenAPI ScreensApi = new ScreenAPI();

        @Step("Creating screen with random name")
        public Screen createScreenWithTag(Screen screen, String tag){
            TagsAPI tagsAPI = new TagsAPI();
            screen.id = ScreensApi.post(screen).jsonPath().getString(PATH_ID);
            tagsAPI.addTag("screen", screen.id, new Tag(tag));
            return screen;
        }

        @Step("Retrieving blocks of the screen")
        public ScreenBlocks getScreenBlocks(Screen screen){
            response = ScreensApi.getBlocks(screen.id);
            return response.jsonPath().getObject("data",ScreenBlocks.class);
        }

        @Step("GET screen with block")
        public Screen getScreenWithBlock(Screen screen, AbstractBlock block){
            ScreenStructure screenStructure = new ScreenStructure();
            screenStructure.items.add(block);
            screen.id = ScreensApi.post(screen).jsonPath().getString(PATH_ID);
            response = ScreensApi.updateBlocks(screen.id, screenStructure);
            block.id = response.jsonPath().getString("data.items[0].id");

            screen.blocks.add(block);
            return screen;
        }

        @Step("Update screen structure")
        public Screen updateScreenStructure (Screen screen, ArrayList<T> blocks){
            ScreenStructure screenStructure = new ScreenStructure();
            screenStructure.items.addAll(blocks);
            response = ScreensApi.updateBlocks(screen.id, screenStructure);
            screen.blocks.addAll(blocks);
            return screen;
        }
}