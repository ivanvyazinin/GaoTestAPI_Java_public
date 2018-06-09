package test.java.contentCloud;

import main.java.steps.FolderSteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.java.SuperTest;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.FOLDER_FOR_TESTS;

public class CommonCloudTest extends SuperTest {
    private FolderSteps steps;

    @BeforeSuite(alwaysRun=true)
    public void createFolderForTests() {
        steps = new FolderSteps();
        steps.createFolder(ROOT_FOLDER);
        FOLDER_FOR_TESTS = steps.testFolder.id;
    }


    @AfterSuite
    public void clean(){steps.deleteFolder();}
}

