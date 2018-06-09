package main.java.steps.directories;

import io.qameta.allure.Step;
import main.java.api.directories.ZoneAPI;
import main.java.entities.directories.Zone;

import static main.java.properties.Constants.PATH_ID;

public class ZonesSteps extends CommonDirectorySteps {
    public Zone testZone;
    public ZoneAPI zonesAPI = new ZoneAPI();

    @Step("Creating functional zone with name '{name}'")
    public void createZone(String name){
        testZone = new Zone(name);
        response = zonesAPI.post(testZone);
        testZone.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating functional zone with random name")
    public void createZone(){
        testZone = new Zone();
        response = zonesAPI.post(testZone);
        testZone.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Edit zone")
    public void editZone(String zoneId){
        response=zonesAPI.put(zoneId,testZone);
    }

    @Step("Delete zone")
    public void deleteZone(String zoneId){
        response=zonesAPI.delete(zoneId);
    }

    @Step("Get zone")
    public void getZone(String zoneId){
        response=zonesAPI.getById(zoneId);
    }

}
