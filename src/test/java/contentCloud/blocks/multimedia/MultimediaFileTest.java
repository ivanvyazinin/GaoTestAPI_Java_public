package test.java.contentCloud.blocks.multimedia;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.multimedia.MultimediaFile;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_FILE_PDF;

@Epic("Content Cloud")
@Feature("Editor adds multimedia blocks to the screen")
@Story("Editor adds File block")
public class MultimediaFileTest extends SuperTest {
    private CommonSteps steps;
    private FilesSteps filesSteps;
    private MultimediaFile testMultimediaFile;
    private String pathToFile = "src/main/resources/files/file_validation/";

    @DataProvider
    public static Object[][] testFiles(){
        return new Object[][]{
                {"file fb2.fb2", 201},
                {"file doc.doc", 201},
                {"file docx.docx", 201},
                {"file odp.odp", 201},
                {"file ods.ods", 201},
                {"file odt.odt", 201},
                {"file pdf.pdf", 201},
                {"file ppt.ppt", 201},
                {"file xls.xls", 201},
                {"file xlsx.xlsx", 201},
        };
    }

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
        testMultimediaFile.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_FILE_PDF, context));

        testMultimediaFile = steps.createEntity(testMultimediaFile);
        steps.checkStatusCode(201);

        steps.getEntity(testMultimediaFile);
        steps.checkThatBodyHasValue(",\"status\":5");
    }

    @Test(dataProvider = "testFiles")
    public void fileBlockValidation(String file, int statusCode){
        testMultimediaFile.files.add(filesSteps.uploadFileWithLicense(pathToFile + file, context));

        testMultimediaFile = steps.createEntity(testMultimediaFile);
        steps.checkStatusCode(statusCode);
    }
}
