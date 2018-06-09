package test.java.contentCloud.blocks;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.steps.ScreenSteps;
import main.java.steps.blocks.BulletListSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.ERROR_IS_BLANK;
import static main.java.properties.Constants.ERROR_TOO_LONG;
import static main.java.properties.Constants.PATH_ERROR;
import static main.java.properties.Context.*;
import static main.java.utils.Generator.getRandomText;

@Feature("Theory Blocks")
public class BulletListTest extends CommonCloudTest {
    private ScreenSteps screenSteps;
    private BulletListSteps bulletListSteps;

    @BeforeClass
    public void prepareSteps(){
        bulletListSteps = new BulletListSteps();
        screenSteps = new ScreenSteps();
    }

    @Test(enabled = false)
    @Story("Create BulletList")
    @Description("Just create bulletList")
    public void createBulletList() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        bulletListSteps.createBulletList(getRandomText(1024), screenSteps.testScreen.id);
        bulletListSteps.checkStatusCode(201);
    }

    @Test(enabled = false)
    @Story("Create BulletList")
    @Description("Check, that you cannot create empty bulletList")
    public void createEmptyBulletList() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        bulletListSteps.createBulletList("", screenSteps.testScreen.id);
        bulletListSteps.checkStatusCode(400);
        bulletListSteps.checkThatJsonContains(ERROR_IS_BLANK, PATH_ERROR);
    }

    @Test(enabled = false)
    @Story("Create BulletList")
    @Description("Check, that you cannot create bulletList with more then 1024 symbols")
    public void createBulletListWith16001Symbols() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        bulletListSteps.createBulletList(getRandomText(1025), screenSteps.testScreen.id);
        bulletListSteps.checkStatusCode(400);
        bulletListSteps.checkThatJsonContains(ERROR_TOO_LONG,PATH_ERROR);
    }

    @Test(enabled = false)
    @Description("Check, that you cannot more then 50 BulletLists in a screen")
    public void create50BulletLists() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);

        for (int count=0; count<49; count++){
            bulletListSteps.createBulletList(getRandomText(10), screenSteps.testScreen.id);
        }

        bulletListSteps.checkStatusCode(201);
        bulletListSteps.createBulletList(getRandomText(10), screenSteps.testScreen.id);
        bulletListSteps.checkStatusCode(400);
    }

    @Test(enabled = false)
    public void editBulletList() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        bulletListSteps.createBulletList(getRandomText(123), screenSteps.testScreen.id);
        bulletListSteps.testBulletList.bulletList = "Changed" + bulletListSteps.testBulletList.bulletList;
        bulletListSteps.editBulletList(bulletListSteps.testBulletList.id);
        bulletListSteps.checkStatusCode(201);
        bulletListSteps.checkThatBodyHasValue("Changed");
    }

    @Test(enabled = false)
    public void deleteBulletList() {
        screenSteps.createScreen(FOLDER_FOR_TESTS);
        bulletListSteps.createBulletList(getRandomText(123), screenSteps.testScreen.id);
        bulletListSteps.deleteBulletList(bulletListSteps.testBulletList.id);
        bulletListSteps.checkStatusCode(204);
        bulletListSteps.getBulletList(bulletListSteps.testBulletList.id);
        bulletListSteps.checkStatusCode(404);
    }
}