package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.Table;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.*;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getTable;

@Feature("Theory Blocks")
public class TableTest extends SuperTest {
    private CommonSteps steps;
    private Table table;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        table  = new Table();
        table.content = getTable(getRandomText(16000));
    }

    @Test
    @Story("Create Table")
    @Description("Just create table")
    public void createTable() {
        steps.createEntity(table);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you can create table with empty cell")
    public void createEmptyTable() {
        table.content = getTable("");
        steps.createEntity(table);
        steps.checkStatusCode(201);
        }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create table with more then 1600 symbols in a cell")
    public void createTableWithHugeCell() {
        table.content = getTable(getRandomText(16001));
        steps.createEntity(table);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TABLE_TOO_LONG, PATH_ERROR);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create table broken html structure")
    public void createTableWithBrokenHMTL() {
        table.content = getTable(getRandomText(33)).substring(7);
        steps.createEntity(table);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains(ERROR_TABLE_STRUCTURE_INVALID, PATH_ERROR);
    }

    @Test
    public void editTable() {
        table = steps.createEntity(table);
        table.content = getTable("Changed");
        steps.editEntity(table);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteTable() {
        table = steps.createEntity(table);
        steps.deleteEntity(table);
        steps.checkStatusCode(204);
        steps.getEntity(table);
        steps.checkStatusCode(404);
    }
}