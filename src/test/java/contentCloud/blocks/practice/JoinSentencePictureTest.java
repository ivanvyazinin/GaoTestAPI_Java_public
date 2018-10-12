package test.java.contentCloud.blocks.practice;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.practice.JoinSentencePicture;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_IMAGE;
import static main.java.utils.Generator.getRandomTextRandomLength;

@Epic("Content Cloud")
@Feature("Editor adds Practice blocks to the screen")
@Story("Editor adds JoinSentencePicture block")
public class JoinSentencePictureTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private JoinSentencePicture testBlock;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testBlock = new JoinSentencePicture(getRandomTextRandomLength(512));
    }

    @Test
    public void createJoinSentencePictureBlock(){
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock = steps.createEntity(testBlock);

        steps.checkStatusCode(201);
        steps.getEntity(testBlock);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test
    @Description("Cannot create Join sentences and picture block with one pair picture - sentence")
    public void createJoinSentencePictureBlockWithOnePair(){
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock = steps.createEntity(testBlock);

        steps.checkStatusCode(400);
    }

    @Test
    @Description("Cannot create Join sentences and picture block with seven pairs picture - sentence")
    public void createJoinSentencePictureBlockWithSevenPairs(){
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock = steps.createEntity(testBlock);

        steps.checkStatusCode(400);
    }

}
