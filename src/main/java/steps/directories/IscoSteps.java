package main.java.steps.directories;

import io.qameta.allure.Step;
import main.java.api.directories.IscoAPI;
import main.java.entities.directories.Isco;

import static main.java.properties.Constants.PATH_ID;

public class IscoSteps extends CommonDirectorySteps {
    public Isco testIsco;
    public IscoAPI iscosAPI = new IscoAPI();

    @Step("Creating isco with name '{name}'")
    public void createIsco(String name, String code){
        testIsco = new Isco(name, code);
        response = iscosAPI.post(testIsco);
        testIsco.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating isco with random name")
    public void createIsco(){
        testIsco = new Isco();
        response = iscosAPI.post(testIsco);
        testIsco.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Edit isco")
    public void editIsco(String iscoId){
        response=iscosAPI.put(iscoId,testIsco);
    }

    @Step("Delete isco")
    public void deleteIsco(String iscoId){
        response=iscosAPI.delete(iscoId);
    }

    @Step("Get isco")
    public void getIsco(String iscoId){
        response=iscosAPI.getById(iscoId);
    }

}
