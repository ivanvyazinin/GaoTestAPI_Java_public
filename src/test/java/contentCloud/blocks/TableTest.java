package test.java.contentCloud.blocks;
//TODO
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.TableSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.*;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getTable;

@Feature("Theory Blocks")
public class TableTest extends CommonCloudTest {
    private ScreenSteps screenSteps;
    private TableSteps tableSteps;

    @BeforeClass
    public void prepareSteps(){
        tableSteps = new TableSteps();
        screenSteps = new ScreenSteps();
    }

    @Test
    @Story("Create Table")
    @Description("Just create table")
    public void createTable() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        tableSteps.createTable(getTable(getRandomText(16000)), screenSteps.testScreen.id);
        tableSteps.checkStatusCode(201);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create empty table")
    public void createEmptyTable() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        tableSteps.createTable("", screenSteps.testScreen.id);
        tableSteps.checkStatusCode(400);
        tableSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create table with more then 1600 symbols in a cell")
    public void createTableWithHugeCell() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        tableSteps.createTable(getTable(getRandomText(16001)), screenSteps.testScreen.id);
        tableSteps.checkStatusCode(400);
        tableSteps.checkThatJsonContains(ERROR_TABLE_TOO_LONG, PATH_ERROR);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create table broken html structure")
    public void createTableWithBrokenHMTL() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        tableSteps.createTable(getTable(getRandomText(33)).substring(7), screenSteps.testScreen.id);
        tableSteps.checkStatusCode(400);
        tableSteps.checkThatJsonContains(ERROR_TABLE_STRUCTURE_INVALID, PATH_ERROR);
    }

    @Test
    public void editTable() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        tableSteps.createTable(getTable(getRandomText(11)), screenSteps.testScreen.id);
        tableSteps.testTable.content = getTable("Changed");
        tableSteps.editTable(tableSteps.testTable.id);
        tableSteps.checkStatusCode(200);
        tableSteps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteTable() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        tableSteps.createTable(getTable(getRandomText(5)), screenSteps.testScreen.id);
        tableSteps.deleteTable(tableSteps.testTable.id);
        tableSteps.checkStatusCode(204);
        tableSteps.getTable(tableSteps.testTable.id);
        tableSteps.checkStatusCode(404);
    }
}