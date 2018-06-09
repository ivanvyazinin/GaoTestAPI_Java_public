package test.java.contentCloud.blocks;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.RuleSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.*;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class RuleTest extends CommonCloudTest {
    private ScreenSteps screenSteps;
    private RuleSteps ruleSteps;

    @BeforeClass
    public void prepareSteps(){
        ruleSteps = new RuleSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    @Story("Create Rule")
    @Description("Just create rule")
    public void createRule() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        ruleSteps.createRule(getRandomText(1024), screenSteps.testScreen.id);
        ruleSteps.checkStatusCode(201);
    }

    @Test
    @Story("Create Rule")
    @Description("Check, that you cannot create empty rule")
    public void createEmptyRule() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        ruleSteps.createRule("", screenSteps.testScreen.id);
        ruleSteps.checkStatusCode(400);
        ruleSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Rule")
    @Description("Check, that you cannot create rule with more then 1024 symbols")
    public void createRuleWith16001Symbols() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        ruleSteps.createRule(getRandomText(1025), screenSteps.testScreen.id);
        ruleSteps.checkStatusCode(400);
        ruleSteps.checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test
    public void editRule() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        ruleSteps.createRule(getRandomText(123), screenSteps.testScreen.id);
        ruleSteps.testRule.rule = "Changed" + ruleSteps.testRule.rule;
        ruleSteps.editRule(ruleSteps.testRule.id);
        ruleSteps.checkStatusCode(200);
        ruleSteps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteRule() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        ruleSteps.createRule(getRandomText(123), screenSteps.testScreen.id);
        ruleSteps.deleteRule(ruleSteps.testRule.id);
        ruleSteps.checkStatusCode(204);
        ruleSteps.getRule(ruleSteps.testRule.id);
        ruleSteps.checkStatusCode(404);
    }
}