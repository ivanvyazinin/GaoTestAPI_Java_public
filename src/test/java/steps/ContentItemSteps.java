package test.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.ContentItemAPI;
import main.java.entities.contentCloud.ContentItem;

import static main.java.properties.Constants.PATH_ID;

public class ContentItemSteps extends CommonSteps {
    public ContentItem testContentItem;
    public String testContentItemId;

    ContentItemAPI contentItemsApi = new ContentItemAPI();

    @Step("Creating ContentItem with random name in folder '{parentFolder}'")
    public void createContentItem(String parentFolder){
        testContentItem = new ContentItem(parentFolder);
        response = contentItemsApi.post(testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating ContentItem with random name '{name}' and description '{description]' in folder '{parentFolder}'")
    public void createContentItem(String name, String description, String parentFolder){
        testContentItem = new ContentItem(name, description, parentFolder);
        response = contentItemsApi.post(testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    @Step("Editing ContentItem")
    public void editContentItem(){
        response = contentItemsApi.put(testContentItemId, testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    @Step("Moving ContentItem to folder '{parent}'")
    public void moveContentItem(String parent){
        testContentItem.parentFolder = parent;
        response = contentItemsApi.put(testContentItemId, testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    //TODO
    public void copyContentItem(String parent){
        testContentItem.parentFolder = parent;
        response = contentItemsApi.put(testContentItemId, testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    @Step("Deleting ContentItem")
    public void deleteContentItem(String itemId) {
        response = contentItemsApi.delete(itemId);
    }

    @Step("Retrieving ContentItem")
    public void getContentItem(){
        response = contentItemsApi.getById(testContentItemId);
    }

    @Step("Retrieving all ContentItems")
    public void getAllContentItems(){
        response = contentItemsApi.get();
    }

}
