package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.Rule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.*;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_RULE;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class RuleTest extends CommonCloudTest {
    private CommonBlocsAPI ruleAPI;
    private Rule rule;

    @BeforeClass
    public void prepareSteps(){
        ruleAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_RULE);
    }

    @BeforeMethod
    public void prepareEntity(){
        rule  = new Rule();
    }

    @Test
    @Story("Create Rule")
    @Description("Just create rule")
    public void createRule() {
        newResponse = ruleAPI.post(rule);
        checkStatusCode(201);
    }

    @Test
    @Story("Create Rule")
    @Description("Check, that you cannot create empty rule")
    public void createEmptyRule() {
        rule.rule = "";
        newResponse = ruleAPI.post(rule);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Rule")
    @Description("Check, that you cannot create rule with more then 1024 symbols")
    public void createRuleWith16001Symbols() {
        rule.rule = getRandomText(1025);
        newResponse = ruleAPI.post(rule);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test
    public void editRule() {
        newResponse = ruleAPI.post(rule);
        rule.rule = "Changed" + rule.rule;
        newResponse = ruleAPI.put(newResponse.jsonPath().getString(PATH_ID),rule);
        checkStatusCode(200);
        checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteRule() {
        rule.id = ruleAPI.post(rule).jsonPath().getString(PATH_ID);
        newResponse = ruleAPI.delete(rule.id);
        checkStatusCode(204);
        newResponse = ruleAPI.getById(rule.id);
        checkStatusCode(404);
    }
}