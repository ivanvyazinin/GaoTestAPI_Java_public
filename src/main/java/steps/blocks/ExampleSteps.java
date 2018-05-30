package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.ExampleAPI;
import main.java.entities.contentCloud.blocks.Example;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class ExampleSteps extends CommonSteps {
    public Example testExample;

    ExampleAPI exampleAPI = new ExampleAPI();

    @Step("Creating Example with content '{content}' in screen '{screen}'")
    public void createExample(String content, String screen){
        testExample = new Example(content, screen);
        response = exampleAPI.post(testExample);
        testExample.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Retrieve Example")
    public void getExample(String id){
        response = exampleAPI.getById(id);
    }

    @Step("Delete Example")
    public void deleteExample(String id){
        response = exampleAPI.delete(id);
    }

    @Step("Edit Example")
    public void editExample(String id){
        response = exampleAPI.put(id, testExample);
    }
}