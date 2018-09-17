package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.CommonAPI;
import main.java.entities.AbstractEntity;

import java.util.List;

import static main.java.properties.Endpoints.API_PREFIX;

public class DirectorySteps extends CommonSteps {
    @Step("Making GET request to get {'entity'} entities with level two")
    public static  <T extends AbstractEntity> List<T> getDirectoryLevelTwo(Class entity, String url) {
        CommonAPI api = new CommonAPI();
        api.setRequestParameters(new String[][]{
                {"level","2"}
        });

        api.setURL(API_PREFIX, url);
        return (List <T>) api.get().jsonPath().getList("data.items", entity);
    }
}
