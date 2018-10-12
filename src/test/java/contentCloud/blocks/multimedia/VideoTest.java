package test.java.contentCloud.blocks.multimedia;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.multimedia.Video;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_VIDEO;

@Epic("Content Cloud")
@Feature("Editor adds multimedia blocks to the screen")
@Story("Editor adds Video block")
public class VideoTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private Video testVideo;
    private String pathToFile = "src/main/resources/files/video_validation/";

    @DataProvider
    public static Object[][] testVideos(){
        return new Object[][]{
                {"mov.mov", 201},
                {"mp4.mp4", 201},
                {"mpeg.mpeg", 201},
                {"m1v.m1v", 201},
                {"mpg.mpg", 201}
        };
    }

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
        testVideo.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_VIDEO, context));

        testVideo = steps.createEntity(testVideo);
        steps.checkStatusCode(201);

        steps.getEntity(testVideo);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test
    public void createVideoBlockWithoutCaption(){
        testVideo.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_VIDEO, context));
        testVideo.captionText = null;
        testVideo = steps.createEntity(testVideo);
        steps.checkStatusCode(201);

        steps.getEntity(testVideo);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test(dataProvider = "testVideos")
    public void videoBlockValidation(String file, int statusCode){
        testVideo.files.add(filesSteps.uploadFileWithLicense(pathToFile + file, context));

        testVideo = steps.createEntity(testVideo);
        steps.checkStatusCode(statusCode);
    }

}
