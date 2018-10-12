package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.MarkStatementsTrueOrFalse;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomText;

@Epic("Content Cloud")
@Feature("Editor adds Practice blocks to the screen")
@Story("Editor adds MarkStatementsTrueOrFalse block")
public class MarkStatementsTrueOrFalseTest extends SuperTest {
    private CommonSteps steps;
    private MarkStatementsTrueOrFalse markStatementsTrueOrFalse;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        markStatementsTrueOrFalse = new MarkStatementsTrueOrFalse();
    }

    @Test
    @Description("Just create MarkStatementsTrueOrFalse Block")
    public void createMarkStatements() {
        markStatementsTrueOrFalse.statements.add(new MarkStatementsTrueOrFalse.Statement());
        steps.createEntity(markStatementsTrueOrFalse);
        steps.checkStatusCode(201);
    }


    @Test
    @Description("Cannot create MarkStatementsTrueOrFalse Block with 513 symbols in Statement")
    public void createMarkStatementsWithStatementTooLong() {
        markStatementsTrueOrFalse.statements.add(new MarkStatementsTrueOrFalse.Statement(getRandomText(1025),true));
        steps.createEntity(markStatementsTrueOrFalse);
        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create MarkStatementsTrueOrFalse Block with 7 Statements")
    public void createMarkStatementsWithSevenAnswers() {
        for (int i=0;i<7;i++){markStatementsTrueOrFalse.statements.add(new MarkStatementsTrueOrFalse.Statement());}
        steps.createEntity(markStatementsTrueOrFalse);
        steps.checkStatusCode(400);
    }


}
