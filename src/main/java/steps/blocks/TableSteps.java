package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.TheoryBlocksAPI;
import main.java.entities.contentCloud.blocks.Table;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;
import static main.java.properties.Endpoints.ENDPOINT_TABLE;

public class TableSteps extends CommonSteps {
    public Table testTable;

    TheoryBlocksAPI tableAPI = new TheoryBlocksAPI(ENDPOINT_TABLE);

    @Step("Creating Table with content '{content}' in screen '{screen}'")
    public void createTable(String content, String screen){
        testTable = new Table(content, screen);
        response = tableAPI.post(testTable);
        testTable.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Retrieve Table")
    public void getTable(String id){
        response = tableAPI.getById(id);
    }

    @Step("Delete Table")
    public void deleteTable(String id){
        response = tableAPI.delete(id);
    }

    @Step("Edit Table")
    public void editTable(String id){
        response = tableAPI.put(id, testTable);
    }
}
