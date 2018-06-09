package test.java;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import org.testng.annotations.BeforeSuite;

import static main.java.properties.Context.HEADERS;

public class SuperTest {
    @BeforeSuite(alwaysRun=true)
    public void setUp() {
        RestAssured.proxy("10.10.0.110",8888);

        AuthAPI auth = new AuthAPI();
        HEADERS.put("authorization", auth.getToken());
    }
}