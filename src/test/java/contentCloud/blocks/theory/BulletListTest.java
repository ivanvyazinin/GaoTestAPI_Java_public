package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.theory.BulletList;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Constants.PATH_ID;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS_THEORY;
import static main.java.properties.Endpoints.ENDPOINT_BULLETLIST;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Feature("Theory Blocks")
public class BulletListTest extends CommonCloudTest {
    private CommonBlocsAPI bulletListAPI;
    private BulletList bulletList;

    @BeforeClass
    public void prepareSteps(){
        bulletListAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_THEORY, ENDPOINT_BULLETLIST);
    }

    @BeforeMethod
    public void prepareEntity(){
        bulletList  = new BulletList();
    }

    @Test
    @Story("Create BulletList")
    @Description("Just create bulletList")
    public void createBulletList() {
        bulletList.bulletList.add(getRandomTextRandomLength(12));
        newResponse = bulletListAPI.post(bulletList);
        checkStatusCode(201);
    }

    @Test
    @Story("Create BulletList")
    @Description("Check, that you cannot create empty bulletList")
    public void createEmptyBulletList() {
        bulletList.bulletList.add("");
        newResponse = bulletListAPI.post(bulletList);
        checkStatusCode(400);
        checkThatJsonContains("3781de7d-4ddd-43ce-9a5e-18be8a542f3e", PATH_ERROR);
    }

    @Test
    @Story("Create BulletList")
    @Description("Check, that you cannot create bulletList with more then 1024 symbols")
    public void createBulletListWith16001Symbols() {
        bulletList.bulletList.add(getRandomText(16001));
        newResponse = bulletListAPI.post(bulletList);
        checkStatusCode(400);
        checkThatJsonContains("ec06ce8a-d846-4cf6-ad35-3f7d220e2305",PATH_ERROR);
    }

    @Test
    @Description("Check, that you cannot more then 50 sentences in the BulletLists")
    public void create50BulletLists() {
        for (int count=0; count<50; count++){
            bulletList.bulletList.add(getRandomText(10));
        }
        newResponse = bulletListAPI.post(bulletList);
        checkStatusCode(201);
        bulletList.bulletList.add(getRandomText(10));
        newResponse = bulletListAPI.post(bulletList);
        checkStatusCode(400);
    }

    @Test
    public void editBulletList() {
        bulletList.bulletList.add(getRandomText(10));
        newResponse = bulletListAPI.post(bulletList);
        bulletList.bulletList.add("Changed");
        newResponse = bulletListAPI.put(newResponse.jsonPath().getString(PATH_ID), bulletList);
        checkStatusCode(200);
        checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteBulletList() {
        bulletList.bulletList.add(getRandomText(10));
        bulletList.id  = bulletListAPI.post(bulletList).jsonPath().getString(PATH_ID);
        newResponse = bulletListAPI.delete(bulletList.id);
        checkStatusCode(204);
        newResponse = bulletListAPI.getById(bulletList.id);
        checkStatusCode(404);
    }
}