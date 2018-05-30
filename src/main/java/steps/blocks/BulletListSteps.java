package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.BulletListAPI;
import main.java.entities.contentCloud.blocks.BulletList;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class BulletListSteps extends CommonSteps {
    public BulletList testBulletList;

    BulletListAPI bulletListAPI = new BulletListAPI();

    @Step("Creating BulletList with content '{content}' in screen '{screen}'")
    public void createBulletList(String content, String screen){
        testBulletList = new BulletList(content, screen);
        response = bulletListAPI.post(testBulletList);
        testBulletList.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Retrieve BulletList")
    public void getBulletList(String id){
        response = bulletListAPI.getById(id);
    }

    @Step("Delete BulletList")
    public void deleteBulletList(String id){
        response = bulletListAPI.delete(id);
    }

    @Step("Edit BulletList")
    public void editBulletList(String id){
        response = bulletListAPI.put(id, testBulletList);
    }
}
