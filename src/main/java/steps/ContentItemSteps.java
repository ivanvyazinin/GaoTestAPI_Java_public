package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ConstructorScreensAPI;
import main.java.api.contentCloud.ContentItemAPI;
import main.java.entities.contentCloud.blocks.AbstractBlock;
import main.java.entities.contentCloud.constructor.ConstructorScreens;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.constructor.ContentItemConstructor;
import main.java.entities.contentCloud.constructor.Item;
import main.java.entities.contentCloud.constructor.Link;
import main.java.entities.contentCloud.folderItems.Screen;

import static main.java.properties.Constants.PATH_ID;

public class ContentItemSteps extends CommonSteps {
    public ContentItemConstructor cc;

    ContentItemAPI contentItemsApi = new ContentItemAPI();
    ConstructorScreensAPI constructorScreensAPI = new ConstructorScreensAPI();

    @Step("Creating ContentItem with screen into it")
    public ContentItem createContentItemWithScreen(ContentItem contentItem, Screen screen){
        contentItem.id = contentItemsApi.post(contentItem).jsonPath().getString(PATH_ID);

        ConstructorScreens constructorScreen = new ConstructorScreens(screen.id, contentItem.id);
        constructorScreensAPI.post(constructorScreen);
        return contentItem;
    }

    @Step("Place screen '{screenId}' into constructor")
    public void placeScreenIntoConstructor(ContentItem contentItem, Screen screen){
        ConstructorScreens constructorScreen = new ConstructorScreens(screen.id, contentItem.id);
        contentItem.screens.add(screen);
        response = constructorScreensAPI.post(constructorScreen);
    }

    @Step("Get Content Item Constructor")
    public ContentItemConstructor getContentItemConstructor(ContentItem contentItem){
        response = contentItemsApi.getConstructor(contentItem.id);
        cc = response.jsonPath().getObject("data", ContentItemConstructor.class);
        return cc;
    }

    @Step("Add link to constructor from '{from}' to '{to}'")
    public void addConstructorLink(ContentItem contentItem, String from, String to){
        Link link = new Link(from, to);
        cc.links.add(link);
        response = contentItemsApi.updateConstructor(contentItem.id,cc);
    }

    public void validateConstructor(ContentItem contentItem){
        response = contentItemsApi.validateConstructor(contentItem.id);
    }

    @Step("Create CI with valid constructor with screen '{screenId}'")
    public <T extends AbstractBlock> ContentItem getCIWithValidConstructor (ContentItem contentItem, Screen testScreen, T testBlock){
        ScreenSteps screenSteps = new ScreenSteps();

        contentItem = createEntity(contentItem);
        testScreen = screenSteps.getScreenWithBlock(testScreen, testBlock);

        String from="";
        String to="";

        placeScreenIntoConstructor(contentItem, testScreen);

        cc = contentItemsApi.getConstructor(contentItem.id).jsonPath().getObject("data", ContentItemConstructor.class);

        getContentItemConstructor(contentItem);
        for (Item item : cc.items){
            switch (item.type){
                case "head": from = item.id; break;
                case "screen": to = item.id; break;
            }
        }
        cc.links.add(new Link(from, to));

        for (Item item : cc.items){
            switch (item.type){
                case "screen": from = item.id; break;
                case "tail": to  = item.id; break;
            }
        }
        cc.links.add(new Link(from, to));
        response = contentItemsApi.updateConstructor(contentItem.id,cc);
        return contentItem;
    }
}
