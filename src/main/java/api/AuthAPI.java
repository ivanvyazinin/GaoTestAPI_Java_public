package main.java.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;
import static main.java.properties.Context.*;
import static main.java.properties.Endpoints.API_AUTH_PREFIX;
import static main.java.properties.Endpoints.API_PREFIX;

public class AuthAPI extends CommonAPI{

    public AuthAPI(){
        setURL(API_AUTH_PREFIX + API_PREFIX, "token");
    }

    @Step("Retrieving token")
    public String getToken(){
        return
                given()
                        .filter(new AllureRestAssured())
                        .formParam("grant_type", GRANT_TYPE)
                        .formParam("username", STAGE_USER)
                        .formParam("password", STAGE_PASSWORD)
                        .formParam("client_id", STAGE_CLIENT_ID)
                        .formParam("client_secret", STAGE_CLIENT_SECRET)
                        .when()
                        .post(getURL())
                        .then()
                        .extract().path("access_token");
    }
}
