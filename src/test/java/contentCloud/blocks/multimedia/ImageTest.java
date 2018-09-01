package test.java.contentCloud.blocks.multimedia;

import main.java.api.FilesAPI;
import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.multimedia.Image;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.contentCloud.CommonCloudTest;

import static main.java.properties.Endpoints.*;

public class ImageTest extends CommonCloudTest {
    private CommonBlocsAPI imageBlocksAPI;
    private FilesAPI filesAPI;
    private Image testImage;

    @BeforeClass
    public void prepareSteps(){
        imageBlocksAPI = new CommonBlocsAPI(ENDPOINT_BLOCKS_MULTIMEDIA, ENDPOINT_COURSE_IMAGE);
        filesAPI = new FilesAPI();
    }

    @BeforeMethod
    public void prepareEntity(){
        testImage = new Image();
    }

    @Test
    public void createImageBlock(){

    }
}
