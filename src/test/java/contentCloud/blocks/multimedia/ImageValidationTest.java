package test.java.contentCloud.blocks.multimedia;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.multimedia.Image;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.SuperTest;

@Epic("Content Cloud")
@Feature("Editor adds multimedia blocks to the screen")
@Story("Editor adds Image block")
public class ImageValidationTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private Image testImage;
    private String pathToFile = "src/main/resources/files/image_validation/";

    @DataProvider
    public static Object[][] testImages(){
        return new Object[][]{
                {"800 x 500.jpg", 400},
                {"808 x 455.jpg", 201},
                {"810 x 450.jpg", 400},
                {"810 x 500.jpg", 201},
                {"8400 x 6300.jpg", 201},
                {"8400 x 6400.png", 400},
                {"8500 x 6000.jpg", 400},
                {"10mb.jpg", 400},
                {"gif.gif", 400}
        };
    }

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        testImage = new Image();
    }

    @Test(dataProvider = "testImages")
    public void createImageBlock(String file, int statusCode){
        testImage.files.add(filesSteps.uploadFileWithLicense(pathToFile + file, context));

        testImage = steps.createEntity(testImage);
        steps.checkStatusCode(statusCode);
    }

}
