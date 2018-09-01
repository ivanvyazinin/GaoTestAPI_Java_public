package test.java.contentCloud.blocks.theory;
//TODO
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.Table;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.*;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_TABLE;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getTable;

@Feature("Theory Blocks")
public class TableTest extends CommonCloudTest {

    private CommonBlocsAPI tableAPI;
    private Table table;

    @BeforeClass
    public void prepareSteps(){
        tableAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_TABLE);
    }

    @BeforeMethod
    public void prepareEntity(){
        table  = new Table();
    }

    @Test
    @Story("Create Table")
    @Description("Just create table")
    public void createTable() {
        table.content = getTable(getRandomText(16000));
        newResponse = tableAPI.post(table);
        checkStatusCode(201);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you can create table with empty cell")
    public void createEmptyTable() {
        table.content = getTable("");
        newResponse = tableAPI.post(table);
        checkStatusCode(201);
        }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create table with more then 1600 symbols in a cell")
    public void createTableWithHugeCell() {
        table.content = getTable(getRandomText(16001));
        newResponse = tableAPI.post(table);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_TABLE_TOO_LONG, PATH_ERROR);
    }

    @Test
    @Story("Create Table")
    @Description("Check, that you cannot create table broken html structure")
    public void createTableWithBrokenHMTL() {
        table.content = getTable(getRandomText(33)).substring(7);
        newResponse = tableAPI.post(table);
        checkStatusCode(400);
        checkThatJsonContains(ERROR_TABLE_STRUCTURE_INVALID, PATH_ERROR);
    }

    @Test
    public void editTable() {
        table.content = getTable(getTable(getRandomText(11)));
        newResponse = tableAPI.post(table);
        table.content = getTable("Changed");
        tableAPI.put(newResponse.jsonPath().getString(PATH_ID), table);
        checkStatusCode(200);
        checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteTable() {
        table.content = getTable(getTable(getRandomText(11)));
        table.id  = tableAPI.post(table).jsonPath().getString(PATH_ID);
        newResponse = tableAPI.delete(table.id);
        checkStatusCode(204);
        newResponse = tableAPI.getById(table.id);
        checkStatusCode(404);
    }
}