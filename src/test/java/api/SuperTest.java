package test.java.api;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.java.steps.FolderSteps;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.AUTH_TOKEN;
import static main.java.properties.Context.HEADERS;

public class SuperTest {
    public String folderForTests;
    private FolderSteps steps;

    @BeforeClass(alwaysRun=true)
    public void setUp() {
        RestAssured.proxy("10.10.0.110",8888);

        if (AUTH_TOKEN.equals("")){
            AuthAPI auth = new AuthAPI();
            AUTH_TOKEN = auth.getToken();
            HEADERS.put("authorization", AUTH_TOKEN );
        }

        steps = new FolderSteps();
        steps.createFolder(ROOT_FOLDER);
        folderForTests = steps.testFolderId;
    }

    @AfterClass
    public void clean(){
        steps.deleteFolder();
    }
}