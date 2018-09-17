package main.java.api.contentCloud;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.core.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_TAGS;

public class TagsAPI extends CommonAPI {

    public TagsAPI(){
        setURL(API_PREFIX, ENDPOINT_TAGS);
    }

    public <T> Response addTag(String resource, String resourceId, T bodyData) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                body(bodyData).
                when().
                post(getURL() + "/" + resource + "/" + resourceId);
    }

    public Response deleteTag(String resource, String resourceId, String tagId) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                delete(getURL() + "/" + resource + "/" + resourceId + "/" + tagId);
    }

}