package main.java.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import main.java.api.CommonAPI;
import main.java.entities.AbstractEntity;
import java.util.List;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class CommonSteps {
    public Response response;
    public CommonAPI api = new CommonAPI();

    @Step("Making POST '{entity.url}' request to crete entity")
    public  <T extends AbstractEntity> T createEntity(T entity) {
        api.setURL(API_PREFIX, entity.getUrl());
        response = api.post(entity);

        return (T) response.jsonPath().getObject("data", entity.getClass());
    }

    @Step("Making PUT '{entity.url}' request to edit entity")
    public  <T extends AbstractEntity> T editEntity(T entity) {
        api.setURL(API_PREFIX, entity.getUrl());
        response = api.put(entity.id, entity);

        return (T) response.jsonPath().getObject("data", entity.getClass());
    }

/*
    @Step("Making POST '{entity.url}' request to crete entity")
    public  <T extends AbstractEntity> T createEntityNew(T entity) {
        api.setURL(API_PREFIX, entity.getUrl());
        response = api.post(entity);
        Function <Response, T> extractor = x -> x.jsonPath().getObject("data", entity.getClass());

        return extractor.apply(response);
    }
*/

    @Step("Making DELETE request to delete '{entity.url}'")
    public  <T extends AbstractEntity> void deleteEntity(T entity) {
        api.setURL(API_PREFIX, entity.getUrl());
        response = api.delete(entity.id);
    }

    @Step("Making GET request to get one {'entity'} entity")
    public  <T extends AbstractEntity> T getEntity(T entity) {
        api.setURL(API_PREFIX, entity.getUrl());
        response = api.getById(entity.id);
        return (T) response.jsonPath().getObject("data", entity.getClass());
    }

    @Step("Making GET request to get {'entity'} entities")
    public  <T extends AbstractEntity> List <T> getEntites(Class entity, String url) {
        api.setURL(API_PREFIX, url);
        response = api.get();
        return (List <T>) response.jsonPath().getList("data.items", entity);
    }

    @Step("Copy entity")
    public <T extends AbstractEntity> T copyEntity(T entity, String destinationFolderId) {
        api.setURL(API_PREFIX, entity.getUrl());
        //TODO bad boi
        if (entity.getUrl().contains("blocks")) {api.setURL(API_PREFIX, ENDPOINT_BLOCKS);}
        response = api.copy(destinationFolderId, entity.id);
        return (T) response.jsonPath().getObject("data", entity.getClass());
    }

    @Step("Comparing status code. Expected: '{statusCodeExpected}'")
    public void checkStatusCode(int statusCodeExpected) {
        assertEquals(response.statusCode(), statusCodeExpected);
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
        assertEquals(response.jsonPath().get(jsonPath), expectedValue);
    }

    @Step("Check, that response contains certain number of items: '{expectedValue}'")
    public void checkItemsNumberInResponse(Object expectedValue){
        assertEquals(response.jsonPath().getList("data.items").size(), expectedValue);
    }

    @Step("Check, that response contains certain number of items: '{expectedValue}'")
    public void checkSizeOfList(Object expectedValue, String jsonPath){
        assertEquals(response.jsonPath().get(jsonPath), expectedValue);
    }
}
