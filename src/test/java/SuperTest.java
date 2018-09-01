package test.java;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import main.java.api.AuthAPI;
import org.testng.annotations.BeforeSuite;

import static main.java.properties.Context.HEADERS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SuperTest {
    public Response newResponse;

    @BeforeSuite(alwaysRun=true)
    public void setUp() {

        AuthAPI auth = new AuthAPI();
        HEADERS.put("authorization", "Bearer " + auth.getToken());
    }

    @Step("Comparing status code. Expected: '{statusCodeExpected}'")
    public void checkStatusCode(int statusCodeExpected) {
        assertEquals(newResponse.statusCode(), statusCodeExpected);
    }

    @Step("Check, that newResponse body contains value: '{expectedValue}'")
    public void checkThatBodyHasValue(String expectedValue) {
        assertTrue(newResponse.asString().contains(expectedValue));
    }

    @Step("Check, that newResponse body doesn't contains value: '{expectedValue}'")
    public void checkThatBodyHasNotValue(String expectedValue) {
        assertTrue(!newResponse.asString().contains(expectedValue));
    }

    @Step("Check, that newResponse body contains value: '{expectedValue}' by path: {jsonPath}")
    public void checkThatJsonContains(Object expectedValue, String jsonPath){
        assertEquals(newResponse.jsonPath().get(jsonPath), expectedValue);
    }

    @Step("Check, that newResponse contains certain number of items: '{expectedValue}'")
    public void checkItemsNumberInResponse(Object expectedValue){
        assertEquals(newResponse.jsonPath().getList("data.items").size(), expectedValue);
    }

}