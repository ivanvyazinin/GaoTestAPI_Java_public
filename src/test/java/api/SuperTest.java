package test.java.api;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import org.testng.annotations.BeforeClass;

import static main.java.properties.Context.AUTH_TOKEN;
import static main.java.properties.Context.HEADERS;

public class SuperTest {

    @BeforeClass(alwaysRun=true)
    public void setUp() {

        if (AUTH_TOKEN.equals("")){
            AuthAPI auth = new AuthAPI();
            AUTH_TOKEN = auth.getToken();
            HEADERS.put("authorization", AUTH_TOKEN );
        }
    }
}
