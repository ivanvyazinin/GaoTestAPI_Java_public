package test.java.contentCloud.blocks.theory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.theory.BulletList;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.PATH_ERROR;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Feature("Theory Blocks")
public class BulletListTest extends CommonCloudTest {
    private CommonSteps steps;
    private BulletList bulletList;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
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
        steps.createEntity(bulletList);
        steps.checkStatusCode(201);
    }

    @Test
    @Story("Create BulletList")
    @Description("Check, that you cannot create empty bulletList")
    public void createEmptyBulletList() {
        bulletList.bulletList.add("");
        steps.createEntity(bulletList);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("3781de7d-4ddd-43ce-9a5e-18be8a542f3e", PATH_ERROR);
    }

    @Test
    @Story("Create BulletList")
    @Description("Check, that you cannot create bulletList with more then 1024 symbols")
    public void createBulletListWith16001Symbols() {
        bulletList.bulletList.add(getRandomText(16001));
        steps.createEntity(bulletList);
        steps.checkStatusCode(400);
        steps.checkThatJsonContains("ec06ce8a-d846-4cf6-ad35-3f7d220e2305",PATH_ERROR);
    }

    @Test
    @Description("Check, that you cannot more then 50 sentences in the BulletLists")
    public void create50BulletLists() {
        for (int count=0; count<50; count++){
            bulletList.bulletList.add(getRandomText(10));
        }
        steps.createEntity(bulletList);
        steps.checkStatusCode(201);
        bulletList.bulletList.add(getRandomText(10));
        steps.createEntity(bulletList);
        steps.checkStatusCode(400);
    }

    @Test
    public void editBulletList() {
        bulletList.bulletList.add(getRandomText(10));
        bulletList = steps.createEntity(bulletList);
        bulletList.bulletList.add("Changed");
        steps.editEntity(bulletList);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("Changed");
    }

    @Test
    public void deleteBulletList() {
        bulletList.bulletList.add(getRandomText(10));
        bulletList = steps.createEntity(bulletList);
        steps.deleteEntity(bulletList);
        steps.checkStatusCode(204);
        steps.getEntity(bulletList);
        steps.checkStatusCode(404);
    }
}