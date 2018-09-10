package test.java.contentCloud.blocks.practice;

import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.practice.JoinSentencePicture;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Constants.PATH_ID;
import static main.java.properties.Context.FILE_PATH_IMAGE;
import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class JoinSentencePictureTest extends CommonCloudTest {
    private CommonBlocsAPI JoinSentencePictureAPI;
    private FilesSteps filesSteps;
    private JoinSentencePicture testBlock;

    @BeforeClass
    public void prepareSteps(){
        JoinSentencePictureAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_PRACTICE, ENDPOINT_JOIN_SENTENCE_PICTURE);
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
        newResponse = JoinSentencePictureAPI.post(testBlock);
        testBlock.id = newResponse.jsonPath().getString(PATH_ID);

        checkStatusCode(201);
        newResponse = JoinSentencePictureAPI.getById(testBlock.id);
        checkThatBodyHasValue(",\"status\":5");
    }

}
