package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.RankStatement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.properties.Endpoints.ENDPOINT_STATEMENT;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class RankStatementTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private RankStatement rankStatement;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_STATEMENT);
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
        newResponse = practiceBlocksAPI.post(rankStatement);
        checkStatusCode(201);
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Cannot create RankStatement with 1 Statement")
    public void createRankStatementWithOneStatement() {
        rankStatement.statements.add(getRandomTextRandomLength(1024));
        newResponse = practiceBlocksAPI.post(rankStatement);
        checkStatusCode(400);
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Cannot create RankStatement Block with 7 Statements")
    public void createRankStatementWithSevenStatements() {
        for (int i=0;i<7;i++){rankStatement.statements.add(getRandomTextRandomLength(1024));}
        newResponse = practiceBlocksAPI.post(rankStatement);
        checkStatusCode(400);
    }

    @Test
    @Story("Create RankStatement Block")
    @Description("Cannot create RankStatement with equal statements")
    public void createRankStatementWithEqualStatements() {
        rankStatement.statements.add(getRandomTextRandomLength(10));
        rankStatement.statements.add(rankStatement.statements.get(0));
        newResponse = practiceBlocksAPI.post(rankStatement);
        checkStatusCode(400);
    }



}
