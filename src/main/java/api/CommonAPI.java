package main.java.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.contentCloud.RequestParameters;
import main.java.core.ServerConfig;

import static io.restassured.RestAssured.given;
import static main.java.core.Configuration.getServerConfig;
import static main.java.properties.Constants.*;
import static main.java.core.Context.HEADERS;

public class CommonAPI extends RequestParameters {
    private String URL;
    private ServerConfig config;

    public void setURL(String prefix, String endpointName) {
        URL = "http://" + config.getHost() + prefix + endpointName;
    }

    protected String getURL() {
        return URL;
    }

    public CommonAPI(){
        config = getServerConfig();
    }

    public <T> Response post(T bodyData) {

        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                body(bodyData).
                when().
                post(getURL());
    }

    public <T> Response put(String id, T bodyData) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                body(bodyData).
                when().
                put(getURL() + "/" + id);
    }


    public Response get() {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                get(getURL() + getRequestParameters());
    }

    public Response getById(String id) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                get(getURL() + "/" + id + getRequestParameters());
    }

    public Response delete(String id) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                delete(getURL() + "/" + id);
    }

    public Response copy(String folderId, String entityId) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                put(getURL() + "/" + entityId + "/folders/" + folderId + "/copy");
    }
}
