package test.java.contentCloud.blocks.multimedia;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.multimedia.Audio;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_AUDIO;

@Epic("Content Cloud")
@Feature("Editor adds multimedia blocks to the screen")
@Story("Editor adds Audio block")
public class AudioTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private Audio testAudio;
    private String pathToFile = "src/main/resources/files/audio_validation/";

    @DataProvider
    public static Object[][] testAudios(){
        return new Object[][]{
                {"mp3.mp3", 201},
                {"aac.aac", 201},
                {"m4a.m4a", 400}
        };
    }

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
        testAudio.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_AUDIO, context));

        testAudio = steps.createEntity(testAudio);
        steps.checkStatusCode(201);

        steps.getEntity(testAudio);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test
    public void createAudioBlockWithoutCaption(){
        testAudio.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_AUDIO, context));
        testAudio.captionText = null;
        testAudio = steps.createEntity(testAudio);
        steps.checkStatusCode(201);

        steps.getEntity(testAudio);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test(dataProvider = "testAudios")
    public void audioBlockValidation(String file, int statusCode){
        testAudio.files.add(filesSteps.uploadFileWithLicense(pathToFile + file, context));

        testAudio = steps.createEntity(testAudio);
        steps.checkStatusCode(statusCode);
    }
}
