package test.java.steps;

import main.java.api.contentCloud.ContentItemAPI;
import main.java.entities.contentCloud.ContentItem;

import static main.java.properties.Constants.PATH_ID;

public class ContentItemSteps extends CommonSteps {
    public ContentItem testContentItem;
    public String testContentItemId;

    ContentItemAPI contentItemsApi = new ContentItemAPI();

    public void createContentItem(String parentFolder){
        testContentItem = new ContentItem(parentFolder);
        response = contentItemsApi.post(testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    public void createContentItem(String name, String description, String parentFolder){
        testContentItem = new ContentItem(name, description, parentFolder);
        response = contentItemsApi.post(testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

    public void editContentItem(){
        response = contentItemsApi.put(testContentItemId, testContentItem);
        testContentItemId = response.jsonPath().getString(PATH_ID);
    }

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

    public void deleteContentItem() {
        response = contentItemsApi.delete(testContentItemId);
    }

    public void getContentItem(){
        response = contentItemsApi.getById(testContentItemId);
    }

    public void getAllContentItems(){
        response = contentItemsApi.get();
    }

}
