package test.java;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import main.java.core.CommonObjects;
import main.java.core.Context;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static main.java.core.Context.HEADERS;

public abstract class SuperTest {
    public Context context;
    public CommonObjects commonObjects;

    @BeforeSuite(alwaysRun=true)
    public void setUp() {
        AuthAPI auth = new AuthAPI();
        HEADERS.put("authorization", "Bearer " + auth.getToken());
    }

    @BeforeClass
    public void setContext(){
        commonObjects = CommonObjects.getInstance();
        context = Context.getInstance();
    }

    @AfterSuite
    public void clean() {
        context.clean();
    }
}