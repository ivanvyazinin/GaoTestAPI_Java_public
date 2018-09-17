package main.java.api.contentCloud;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.*;
import static main.java.core.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_CONTENT_ITEMS;

public class ContentItemAPI extends CommonAPI {

    public ContentItemAPI(){
        setURL(API_PREFIX, ENDPOINT_CONTENT_ITEMS);
    }

    @Override
    public Response copy(String folderId, String contentItemId) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                put(getURL() + "/" + contentItemId + "/folders/" + folderId + "/copy");
    }

    public Response getConstructor(String id) {
        return given().
                filter(new AllureRestAssured()).
                headers(HEADERS).
                when().
                get(getURL() + "/" + id + "/constructor" + getRequestParameters());
    }

    public Response validateConstructor(String id) {
        return given().
                filter(new AllureRestAssured()).
                headers(HEADERS).
                when().
                get(getURL() + "/" + id + "/constructor/validate");
    }

    public <T> Response updateConstructor(String id, T bodyData){
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                body(bodyData).
                headers(HEADERS).
                when().
                put(getURL() + "/" + id + "/constructor?force=1");
    }
}
