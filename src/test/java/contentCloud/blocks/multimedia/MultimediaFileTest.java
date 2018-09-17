package test.java.contentCloud.blocks.multimedia;

import main.java.entities.contentCloud.blocks.multimedia.MultimediaFile;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_FILE_PDF;

public class MultimediaFileTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private MultimediaFile testMultimediaFile;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testMultimediaFile = new MultimediaFile();
    }

    @Test
    public void createFileBlock(){
        testMultimediaFile.files.add(filesSteps.uploadFile(FILE_PATH_FILE_PDF));

        testMultimediaFile = steps.createEntity(testMultimediaFile);
        steps.checkStatusCode(201);

        steps.getEntity(testMultimediaFile);
        steps.checkThatBodyHasValue(",\"status\":5");
    }
}
