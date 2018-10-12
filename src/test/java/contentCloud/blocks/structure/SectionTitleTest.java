package test.java.contentCloud.blocks.structure;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.contentCloud.blocks.structure.SectionTitle;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.FILE_PATH_IMAGE;

@Epic("Content Cloud")
@Feature("Editor adds Structure blocks to the screen")
@Story("Editor adds Section Title block")
public class SectionTitleTest extends SuperTest {
    private CommonSteps steps;
    private SectionTitle sectionTitle;
    private FilesSteps filesSteps;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        filesSteps = new FilesSteps();
    }

    @BeforeMethod
    public void prepareEntity(){
        sectionTitle = new SectionTitle();
    }

    @Test
    @Story("Create SectionTitle Block")
    @Description("Just create SectionTitle Block")
    public void createSectionTitleBlock() {
        sectionTitle.files.add(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        steps.createEntity(sectionTitle);
        steps.checkStatusCode(201);
    }
}
