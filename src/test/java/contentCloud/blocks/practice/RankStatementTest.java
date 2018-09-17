package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.RankStatement;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class RankStatementTest extends SuperTest {
    private CommonSteps steps;
    private RankStatement rankStatement;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        rankStatement = new RankStatement();
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Just create RankStatement Block")
    public void createRankStatement() {
        rankStatement.statements.add(getRandomTextRandomLength(1024));
        rankStatement.statements.add(getRandomTextRandomLength(1024));
        steps.createEntity(rankStatement);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Cannot create RankStatement with 1 Statement")
    public void createRankStatementWithOneStatement() {
        rankStatement.statements.add(getRandomTextRandomLength(1024));
        steps.createEntity(rankStatement);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Cannot create RankStatement Block with 7 Statements")
    public void createRankStatementWithSevenStatements() {
        for (int i=0;i<7;i++){rankStatement.statements.add(getRandomTextRandomLength(1024));}
        steps.createEntity(rankStatement);
        steps.checkStatusCode(400);
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Cannot create RankStatement with equal statements")
    public void createRankStatementWithEqualStatements() {
        rankStatement.statements.add(getRandomTextRandomLength(10));
        rankStatement.statements.add(rankStatement.statements.get(0));
        steps.createEntity(rankStatement);
        steps.checkStatusCode(400);
    }



}
