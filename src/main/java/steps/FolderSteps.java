package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.FoldersAPI;
import main.java.entities.contentCloud.Folder;

import static main.java.properties.Constants.PATH_ID;

public class FolderSteps extends CommonSteps {
    public Folder testFolder;
    public FoldersAPI api = new FoldersAPI();

    @Step("Creating folder with random name into folder '{parentFolder}'")
    public void createFolder(String parentFolder){
        testFolder = new Folder(parentFolder);
        response = api.post(testFolder);
        testFolder.id = response.jsonPath().getString(PATH_ID);
    }

    public void createFolder(String name, String parentFolder){
        testFolder = new Folder(name, parentFolder);
        response = api.post(testFolder);
        testFolder.id = response.jsonPath().getString(PATH_ID);
    }

    public void editFolder(){
        response = api.put(testFolder.id, testFolder);
        testFolder.id = response.jsonPath().getString(PATH_ID);
    }

    public void moveFolder(String parent){
        testFolder.parentFolder = parent;
        response = api.put(testFolder.id, testFolder);
        testFolder.id = response.jsonPath().getString(PATH_ID);
    }

    //TODO
    public void copyFolder(String parent){
        testFolder.parentFolder = parent;
        response = api.put(testFolder.id, testFolder);
        testFolder.id = response.jsonPath().getString(PATH_ID);
    }

    public void deleteFolder(){
        response = api.delete(testFolder.id);
    }

    public void deleteFolder(String folderId){
        response = api.delete(folderId);
    }

    public void getFolder(){
        response = api.getById(testFolder.id);
    }

    public void getAllFolder(){
        response = api.get();
    }

    public void getFolderItems(String folderId){
        response = api.getFolderItems(folderId);
    }

}
