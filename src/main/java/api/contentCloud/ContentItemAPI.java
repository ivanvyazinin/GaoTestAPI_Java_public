package main.java.api.contentCloud;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.*;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_CONTENT_ITEMS;

public class ContentItemAPI extends CommonAPI {

    public ContentItemAPI(){
        setURL(API_PREFIX, ENDPOINT_CONTENT_ITEMS);
        parameters = "?embed[]=" + EMBED_FOLDER + "&embed[]=" + EMBED_TAG + "&embed[]=" + EMBED_CONSTRUCTOR;
    }

    public Response getConstructor(String id) {
        return given().
                filter(new AllureRestAssured()).
                headers(HEADERS).
                when().
                get(getURL() + "/" + id + "/constructor" + getRequestParameters());
    }

    public <T> Response updateConstructor(String id, T bodyData){
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                body(bodyData).
                headers(HEADERS).
                when().
                put(getURL() + "/" + id + "/constructor");
    }
}
