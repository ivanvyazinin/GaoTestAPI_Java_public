package test.java.steps;

import main.java.api.contentCloud.TagsAPI;
import main.java.entities.contentCloud.Tag;

import static main.java.properties.Constants.PATH_ID;
import static main.java.utils.Generator.getRandomTextField;

public class TagsSteps extends CommonSteps {
    public Tag testTag;
    public String testTagId;

    TagsAPI tagsAPI = new TagsAPI();

    public void addTag(String resource, String resourceId){
        testTag = new Tag(getRandomTextField("Tag name"));
        response = tagsAPI.addTag(resource, resourceId, testTag);
        testTagId = response.jsonPath().getString(PATH_ID);
    }

    public void deleteTag(String resource, String resourceId) {
        response = tagsAPI.deleteTag(resource, resourceId, testTagId);
    }


}
