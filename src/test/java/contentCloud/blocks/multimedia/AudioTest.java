package test.java.contentCloud.blocks.multimedia;

import main.java.entities.contentCloud.blocks.multimedia.Audio;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Context.FILE_PATH_AUDIO;

public class AudioTest extends CommonCloudTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private Audio testAudio;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testAudio = new Audio();
    }

    @Test
    public void createAudioBlock(){
        testAudio.files.add(filesSteps.uploadFile(FILE_PATH_AUDIO));

        testAudio = steps.createEntity(testAudio);
        steps.checkStatusCode(201);

        steps.getEntity(testAudio);
        steps.checkThatBodyHasValue(",\"status\":5");
    }
}
