package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.MarkStatementsTrueOrFalse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_PRACTICE;
import static main.java.properties.Endpoints.ENDPOINT_MARK_STATEMENTS;
import static main.java.utils.Generator.getRandomText;

public class MarkStatementsTrueOrFalseTest extends CommonCloudTest {
    private CommonBlocsAPI practiceBlocksAPI;
    private MarkStatementsTrueOrFalse markStatementsTrueOrFalse;

    @BeforeClass
    public void prepareSteps(){
        practiceBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_MARK_STATEMENTS);
    }

    @BeforeMethod
    public void prepareEntity(){
        markStatementsTrueOrFalse = new MarkStatementsTrueOrFalse();
    }

    @Test
    @Story("Create MarkStatementsTrueOrFalse Block")
    @Description("Just create MarkStatementsTrueOrFalse Block")
    public void createMarkStatements() {
        markStatementsTrueOrFalse.statements.add(new MarkStatementsTrueOrFalse.Statement());
        newResponse = practiceBlocksAPI.post(markStatementsTrueOrFalse);
        checkStatusCode(201);
    }


    @Test
    @Story("Create MarkStatementsTrueOrFalse Block")
    @Description("Cannot create MarkStatementsTrueOrFalse Block with 513 symbols in Statement")
    public void createMarkStatementsWithStatementTooLong() {
        markStatementsTrueOrFalse.statements.add(new MarkStatementsTrueOrFalse.Statement(getRandomText(1025),true));
        newResponse = practiceBlocksAPI.post(markStatementsTrueOrFalse);
        checkStatusCode(400);
    }

    @Test
    @Story("Create MarkStatementsTrueOrFalse Block")
    @Description("Cannot create MarkStatementsTrueOrFalse Block with 7 Statements")
    public void createMarkStatementsWithSevenAnswers() {
        for (int i=0;i<7;i++){markStatementsTrueOrFalse.statements.add(new MarkStatementsTrueOrFalse.Statement());}
        newResponse = practiceBlocksAPI.post(markStatementsTrueOrFalse);
        checkStatusCode(400);
    }


}
