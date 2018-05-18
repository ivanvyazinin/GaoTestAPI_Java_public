package main.java.api.contentCloud;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.*;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_SCREENS;

public class ScreenAPI extends CommonAPI {

    public ScreenAPI(){
        setURL(API_PREFIX, ENDPOINT_SCREENS);
        parameters = "?embed[]=" + EMBED_FOLDER + "&embed[]=" + EMBED_TAG + "&embed[]=" + EMBED_CONSTRUCTOR;
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
                get(getURL() + "/" + id + "/blocks");
    }

}
