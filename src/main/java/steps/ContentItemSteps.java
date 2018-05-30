package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ConstructorScreensAPI;
import main.java.api.contentCloud.ContentItemAPI;
import main.java.entities.contentCloud.constructor.ConstructorScreens;
import main.java.entities.contentCloud.ContentItem;
import main.java.entities.contentCloud.constructor.ContentItemConstructor;
import main.java.entities.contentCloud.constructor.Link;

import static main.java.properties.Constants.PATH_ID;

public class ContentItemSteps extends CommonSteps {
    public ContentItem testContentItem;
    public ContentItemConstructor.Data cc;

    ContentItemAPI contentItemsApi = new ContentItemAPI();
    ConstructorScreensAPI constructorScreensAPI = new ConstructorScreensAPI();

    @Step("Creating ContentItem with random name in folder '{parentFolder}'")
    public void createContentItem(String parentFolder){
        testContentItem = new ContentItem(parentFolder);
        response = contentItemsApi.post(testContentItem);
        testContentItem.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating ContentItem with random name '{name}' and description '{description]' in folder '{parentFolder}'")
    public void createContentItem(String name, String description, String parentFolder){
        testContentItem = new ContentItem(name, description, parentFolder);
        response = contentItemsApi.post(testContentItem);
        testContentItem.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Editing ContentItem")
    public void editContentItem(){
        response = contentItemsApi.put(testContentItem.id, testContentItem);
        testContentItem.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Moving ContentItem to folder '{parent}'")
    public void moveContentItem(String parent){
        testContentItem.parentFolder = parent;
        response = contentItemsApi.put(testContentItem.id, testContentItem);
        testContentItem.id = response.jsonPath().getString(PATH_ID);
    }

    //TODO
    public void copyContentItem(String parent){
        testContentItem.parentFolder = parent;
        response = contentItemsApi.put(testContentItem.id, testContentItem);
        testContentItem.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Deleting ContentItem")
    public void deleteContentItem(String itemId) {
        response = contentItemsApi.delete(itemId);
    }

    @Step("Retrieving ContentItem")
    public void getContentItem(){
        response = contentItemsApi.getById(testContentItem.id);
    }

    @Step("Retrieving all ContentItems")
    public void getAllContentItems(){
        response = contentItemsApi.get();
    }

    @Step("Place screen '{screenId}' into constructor")
    public void placeScreenIntoConstructor(String screenId){
        ConstructorScreens constructorScreen = new ConstructorScreens(screenId, testContentItem.id);
        response = constructorScreensAPI.post(constructorScreen);
    }

    @Step("Get Content Item Constructor")
    public void getContentItemConstructor(String contentItemId){
        response = contentItemsApi.getConstructor(contentItemId);
        cc = response.as(ContentItemConstructor.class).data;
    }

    @Step("Add link to constructor from '{from}' to '{to}'")
    public void addConstructorLink(String from, String to){
        Link link = new Link(from, to);
        cc.links.add(link);
        response = contentItemsApi.updateConstructor(testContentItem.id,cc);
    }

}
