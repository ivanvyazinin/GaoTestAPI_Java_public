package test.java.contentCloud.blocks.multimedia;

import main.java.entities.contentCloud.blocks.multimedia.Video;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Context.FILE_PATH_VIDEO;

public class VideoTest extends CommonCloudTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private Video testVideo;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testVideo = new Video();
    }

    @Test
    public void createVideoBlock(){
        testVideo.files.add(filesSteps.uploadFile(FILE_PATH_VIDEO));

        testVideo = steps.createEntity(testVideo);
        steps.checkStatusCode(201);

        steps.getEntity(testVideo);
        steps.checkThatBodyHasValue(",\"status\":5");
    }
}
