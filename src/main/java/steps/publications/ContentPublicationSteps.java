package main.java.steps.publications;

import io.qameta.allure.Step;
import main.java.api.publications.PublicationsAPI;
import main.java.entities.publications.ContentPublication;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class ContentPublicationSteps extends CommonSteps {
    public ContentPublication testContentPublication;
    PublicationsAPI publicationsAPI = new PublicationsAPI();

    @Step("Create publication")
    public void createPublication(){
        response = publicationsAPI.post(testContentPublication);
        testContentPublication.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Edit publication")
    public void editPublication(String publicationId){
        response = publicationsAPI.put(publicationId, testContentPublication);
    }

    @Step("Get publications")
    public void getPublications(){
        response = publicationsAPI.get();
    }

}
