package main.java.api.contentCloud;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.*;
import static main.java.core.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_SCREENS;

public class ScreenAPI extends CommonAPI {

    public ScreenAPI(){
        setURL(API_PREFIX, ENDPOINT_SCREENS);
    }

    public Response copy(String folderId, String screenId) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                put(getURL() + "/" + screenId + "/folders/" + folderId + "/copy");
    }

    public Response getBlocks(String id) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                get(getURL() + "/" + id + "/blocks" + getRequestParameters());
    }

    public <T> Response updateBlocks(String id, T bodyData) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                body(bodyData).
                when().
                put(getURL() + "/" + id + "/blocks/update");
    }

}
