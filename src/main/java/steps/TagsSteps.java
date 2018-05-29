package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.TagsAPI;
import main.java.entities.contentCloud.Tag;

import static main.java.properties.Constants.PATH_ID;
import static main.java.utils.Generator.getRandomTextField;

public class TagsSteps extends CommonSteps {
    public Tag testTag;
    public String testTagId;

    TagsAPI tagsAPI = new TagsAPI();

    @Step("Adding tag to '{resource}' with id '{resourceId}'")
    public void addTag(String resource, String resourceId){
        testTag = new Tag(getRandomTextField("Tag name"));
        response = tagsAPI.addTag(resource, resourceId, testTag);
        testTagId = response.jsonPath().getString(PATH_ID);
    }

    @Step("Deleting tag from '{resource}' with id '{resourceId}'")
    public void deleteTag(String resource, String resourceId) {
        response = tagsAPI.deleteTag(resource, resourceId, testTagId);
    }


}
