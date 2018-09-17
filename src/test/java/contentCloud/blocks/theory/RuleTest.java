package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Rule;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextField;

@Feature("Theory Blocks")
public class RuleTest extends SuperTest {
    private CommonSteps steps;
    private Rule rule;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        rule  = new Rule();
        rule.rule = getRandomTextField("Rule");
    }

    @Test
    @Story("Create Rule")
    @Description("Just create rule")
    public void createRule() {
        steps.createEntity(rule);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Create Rule")
    @Description("Check, that you cannot create empty rule")
    public void createEmptyRule() {
        rule.rule = "";
        steps.createEntity(rule);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Rule")
    @Description("Check, that you cannot create rule with more then 1024 symbols")
    public void createRuleWith16001Symbols() {
        rule.rule = getRandomText(1025);
        steps.createEntity(rule);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test
    public void editRule() {
        rule = steps.createEntity(rule);
        rule.rule = "Changed" + rule.rule;
        steps.editEntity(rule);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteRule() {
        rule = steps.createEntity(rule);
        steps.deleteEntity(rule);
        steps.checkStatusCode(204);
        steps.getEntity(rule);
        steps.checkStatusCode(404);
    }
}