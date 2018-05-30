package test.java;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import org.testng.annotations.AfterSuite;
import main.java.steps.FolderSteps;
import org.testng.annotations.BeforeSuite;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Context.FOLDER_FOR_TESTS;

public class SuperTest {
    private FolderSteps steps;
    private AuthAPI auth;

    @BeforeSuite(alwaysRun=true)
    public void setUp() {
        RestAssured.proxy("10.10.0.110",8888);

        auth = new AuthAPI();
        HEADERS.put("authorization", auth.getToken());

        steps = new FolderSteps();
        steps.createFolder(ROOT_FOLDER);
        FOLDER_FOR_TESTS = steps.testFolder.id;
    }

    @AfterSuite
    public void clean(){
        steps.deleteFolder();
    }
}