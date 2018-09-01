package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ConstructorScreensAPI;
import main.java.api.contentCloud.ContentItemAPI;
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

    @Step("Creating ContentItem with random name in folder '{parentFolder}'")
    public ContentItem createContentItem(ContentItem contentItem){
        response = contentItemsApi.post(contentItem);
        contentItem.id = response.jsonPath().getString(PATH_ID);
        return contentItem;
    }

    @Step("Creating ContentItem with screen into it")
    public ContentItem createContentItemWithScreen(ContentItem contentItem, Screen screen){
        contentItem.id = contentItemsApi.post(contentItem).jsonPath().getString(PATH_ID);

        ConstructorScreens constructorScreen = new ConstructorScreens(screen.id, contentItem.id);
        constructorScreensAPI.post(constructorScreen);
        return contentItem;
    }

    public ContentItem copyContentItem(ContentItem contentItem, String parent){
        response = contentItemsApi.copy(parent, contentItem.id);
        return response.jsonPath().getObject("data",ContentItem.class);
    }

    @Step("Retrieving ContentItem")
    public void getContentItem(ContentItem contentItem){
        response = contentItemsApi.getById(contentItem.id);
    }

    @Step("Place screen '{screenId}' into constructor")
    public void placeScreenIntoConstructor(ContentItem contentItem, String screenId){
        ConstructorScreens constructorScreen = new ConstructorScreens(screenId, contentItem.id);
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

    @Step("Create CI with valid constructor with screen '{screenId}'")
    public ContentItem getCIWithValidConstructor(ContentItem contentItem, String screenId){
        createContentItem(contentItem);
        String from="";
        String to="";

        placeScreenIntoConstructor(contentItem, screenId);

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
