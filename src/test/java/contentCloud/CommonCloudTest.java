package test.java.contentCloud;

import main.java.api.contentCloud.FoldersAPI;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.directories.AbstractDirectory;
import main.java.entities.directories.AbstractDirectoryResponse;
import main.java.steps.directories.CommonDirectorySteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.java.SuperTest;

import java.util.ArrayList;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.FOLDER_FOR_TESTS;
import static main.java.properties.Endpoints.*;

public class CommonCloudTest extends SuperTest {
    private FoldersAPI foldersAPI;
    public static ArrayList<AbstractDirectory> level;

    @BeforeSuite(alwaysRun=true)
    public void createFolderForTests() {
        foldersAPI = new FoldersAPI();
        FOLDER_FOR_TESTS = foldersAPI.post(new Folder(ROOT_FOLDER)).jsonPath().getObject("data",Folder.class).id;

        CommonDirectorySteps directorySteps = new CommonDirectorySteps();
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LEVEL);
        level = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;
    }

    @AfterSuite
    public void clean(){foldersAPI.delete(FOLDER_FOR_TESTS);}
}

