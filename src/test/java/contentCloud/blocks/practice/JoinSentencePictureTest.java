package test.java.contentCloud.blocks.practice;

import main.java.entities.contentCloud.blocks.practice.JoinSentencePicture;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_IMAGE;
import static main.java.utils.Generator.getRandomTextRandomLength;

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
        testBlock.addItem(filesSteps.uploadFile(FILE_PATH_IMAGE));
        testBlock.addItem(filesSteps.uploadFile(FILE_PATH_IMAGE));
        testBlock = steps.createEntity(testBlock);

        steps.checkStatusCode(201);
        steps.getEntity(testBlock);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

}
