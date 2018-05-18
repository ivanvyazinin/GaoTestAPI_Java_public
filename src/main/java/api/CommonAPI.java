package main.java.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import main.java.api.contentCloud.RequestParameters;
import main.java.common.ConfigurationSetup;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;

public class CommonAPI extends RequestParameters {
    final ConfigurationSetup cs;
    private String URL;
    protected String parameters= "";

    protected void setURL(String prefix, String endpointName) {
        URL = cs.environment.getAPIAddress() + prefix + endpointName;
    }

    protected String getURL() {
        return URL;
    }

    public CommonAPI(){
        cs = new ConfigurationSetup();
        URL = cs.environment.getAPIAddress() + API_PREFIX;
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
                get(getURL() + "/" + id + parameters);
    }

    public Response delete(String id) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                delete(getURL() + "/" + id);
    }

}
