package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.RuleAPI;
import main.java.entities.contentCloud.blocks.Rule;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class RuleSteps extends CommonSteps {
    public Rule testRule;

    RuleAPI ruleAPI = new RuleAPI();

    @Step("Creating Rule with content '{content}' in screen '{screen}'")
    public void createRule(String content, String screen){
        testRule = new Rule(content, screen);
        response = ruleAPI.post(testRule);
        testRule.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Retrieve Rule")
    public void getRule(String id){
        response = ruleAPI.getById(id);
    }

    @Step("Delete Rule")
    public void deleteRule(String id){
        response = ruleAPI.delete(id);
    }

    @Step("Edit Rule")
    public void editRule(String id){
        response = ruleAPI.put(id, testRule);
    }
}
