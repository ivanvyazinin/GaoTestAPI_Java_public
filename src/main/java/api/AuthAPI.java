package main.java.api;

import io.qameta.allure.Step;

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
                        .formParam("grant_type", GRANT_TYPE)
                        .formParam("username", STAGE_USER)
                        .formParam("password", STAGE_PASSWORD)
                        .formParam("client_id", STAGE_CLIENT_SECRET)
                        .when()
                        .post(getURL())
                        .then()
                        .extract().path("access_token");
    }
}
