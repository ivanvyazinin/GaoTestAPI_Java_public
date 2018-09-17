package test.java.contentCloud.blocks.multimedia;

import main.java.entities.contentCloud.blocks.multimedia.Image;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_IMAGE;

public class ImageTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private Image testImage;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testImage = new Image();
    }

    @Test
    public void createImageBlock(){
        testImage.files.add(filesSteps.uploadFile(FILE_PATH_IMAGE));

        testImage = steps.createEntity(testImage);
        steps.checkStatusCode(201);

        steps.getEntity(testImage);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test
    public void createImageBlockWithoutCaption(){
        testImage.files.add(filesSteps.uploadFile(FILE_PATH_IMAGE));
        testImage.captionText = null;
        testImage = steps.createEntity(testImage);
        steps.checkStatusCode(201);

        steps.getEntity(testImage);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test
    public void createImageBlockWithoutFile(){
        testImage = steps.createEntity(testImage);
        steps.checkStatusCode(400);
    }
}
