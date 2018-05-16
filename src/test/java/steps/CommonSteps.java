package test.java.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CommonSteps {
    public Response response;

    @Step("Comparing status code. Expected: '{statusCodeExpected}'")
    public void checkStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, response.statusCode());
    }

    @Step("Check, that response body contains value: '{expectedValue}'")
    public void checkThatBodyHasValue(String expectedValue) {
        assertTrue(response.asString().contains(expectedValue));
    }

    @Step("Check, that response body doesn't contains value: '{expectedValue}'")
    public void checkThatBodyHasNotValue(String expectedValue) {
        assertTrue(!response.asString().contains(expectedValue));
    }

    @Step("Check, that response body contains value: '{expectedValue}' by path: {jsonPath}")
    public void checkThatJsonContains(Object expectedValue, String jsonPath){
        assertEquals(expectedValue, response.jsonPath().get(jsonPath));
    }

    @Step("Check, that response contains certain number of items: '{expectedValue}'")
    public void checkItemsNumberInResponse(Object expectedValue){
        assertEquals(expectedValue,response.jsonPath().getList("data.items").size());
    }
}
