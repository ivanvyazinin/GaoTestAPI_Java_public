package test.java.contentCloud.blocks.multimedia;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.multimedia.CropSettings;
import main.java.entities.contentCloud.blocks.multimedia.Image;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_IMAGE;

@Epic("Content Cloud")
@Feature("Editor adds multimedia blocks to the screen")
@Story("Editor adds Image block")
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
        testImage.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));

        testImage = steps.createEntity(testImage);
        steps.checkStatusCode(201);

        steps.getEntity(testImage);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test
    public void createImageBlockWithoutCaption(){
        testImage.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
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

    @Test
    @Description("Create image block with cropped image and delete crop")
    public void deleteCrop(){
        String file = filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context);
        filesSteps.cropFile(new CropSettings(300, 300, 15, 15), file);
        filesSteps.checkStatusCode(201);
        filesSteps.checkThatBodyHasValue("\"width\":300");

        testImage.files.add(file);
        testImage = steps.createEntity(testImage);
        filesSteps.checkThatBodyHasValue("\"width\":300");

        filesSteps.removeCrop(file);
        filesSteps.checkStatusCode(204);

        steps.getEntity(testImage);
        steps.checkThatBodyHasValue(file);
        filesSteps.checkThatBodyHasNotValue("\"width\":300");
    }
}
