package main.java.steps.publications;

import io.qameta.allure.Step;
import main.java.api.publications.MediaPublicationsAPI;
import main.java.entities.publications.MediaPublication;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class MediaPublicationSteps extends CommonSteps {
    public MediaPublication testMediaPublication;
    MediaPublicationsAPI mediaPublicationsAPI = new MediaPublicationsAPI();

    @Step("Create publication")
    public void createPublication(){
        response = mediaPublicationsAPI.post(testMediaPublication);
        testMediaPublication.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Edit publication")
    public void editPublication(String publicationId){
        response = mediaPublicationsAPI.put(publicationId, testMediaPublication);
    }

    @Step("Delete publication")
    public void deletePublication(String publicationId){
        response = mediaPublicationsAPI.delete(publicationId);
    }

    @Step("Get publication")
    public void getPublication(String publicationId){
        response = mediaPublicationsAPI.getById(publicationId);
    }
}
